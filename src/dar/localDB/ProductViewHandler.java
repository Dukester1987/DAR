/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.TimeWrapper;
import dar.Gui.Production.SubProducts;
import dar.dbObjects.Production.ProductListView;
import dar.dbObjects.Production.RecipeIngredients;
import dar.dbObjects.Production.RecipeList;
import dar.dbObjects.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class ProductViewHandler{
    
    private ArrayList<ProductListView> productsOnSite;
    private ArrayList<ProductListView> products;
    private Date dateFor;
    private LocalWraper con;
    private User user;  
    private TimeWrapper ti;
    private ArrayList<ProductListView> allProducts;
    private ArrayList<RecipeList> recipeList;
    private ArrayList<RecipeIngredients> recipeIng;
    
    public ProductViewHandler(LocalWraper con, User user,Date date) {
        this.con = con;
        this.ti = new TimeWrapper();
        this.user = user;
        this.dateFor = date;
    }
    
    public ArrayList<RecipeList> createRecipeList(){
        recipeList = new ArrayList<RecipeList>();
        String query = String.format("SELECT recipe.ID, ProductAllocationID, Products.ID as ProductID, Products.productName, recipe.SiteID, RecName, Status FROM Recipe \n" +
        "LEFT JOIN  ProductAllocation on Recipe.ProductAllocationID = ProductAllocation.ID\n" +
        "LEFT JOIN Products on ProductAllocation.productID = Products.ID\n" +
        "WHERE status = 1 and recipe.SiteID = %s", con.userData.getSiteID());
        //System.out.println(query);
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                RecipeList list = new RecipeList(rs.getInt("ID"), rs.getInt("ProductAllocationID"), rs.getInt("SiteID"),rs.getInt("ProductID"), rs.getString("ProductName"), rs.getString("RecName"), rs.getBoolean("Status"));
                recipeList.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recipeList;
    }
    
    public void displayRecipesInTable(JTable table){
        ArrayList<RecipeList> list = createRecipeList();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        refreshTable(model);
        
        for (RecipeList recipes : list) {
            model.addRow(new Object[]{
                recipes.getID(),
                recipes.getRecName(),
                recipes.getMainProductName()
            });
        }
        
    }
    
    private void refreshTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }
    
    public ArrayList<ProductListView> createProductList(Date dateFor){
        this.dateFor = dateFor;        
        products = new ArrayList<ProductListView>();
        allProducts = new ArrayList<ProductListView>();
        String query = String.format("SELECT\n" +
        "	ProductAllocation.ID as AllocationID,\n" +
        "	Products.ID as ProductID,\n" +
        "	ProductAllocation.SiteID as SiteID,\n" +
        "	Products.EPA as EPAID,\n" +
        "	Products.GroupID as GroupID,\n" +
        "	Products.ProductName as ProductName,\n" +
        "	Products.ProductVal as ProductVal,\n" +
        "	Products.Type as TypeID,\n" +
        "	Products.UOM as UOM\n" +
        "FROM Products \n" +
        "LEFT JOIN ProductAllocation ON Products.ID = ProductAllocation.ProductID AND ProductAllocation.StartDate<= '%s'\n" +
        "AND ProductAllocation.EndDate >= '%s'\n" +
        "WHERE ProductAllocation.SiteID is null or ProductAllocation.SiteID = %s", dateFor,dateFor,con.userData.getSiteID());
        System.out.println(query);
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                ProductListView product = new  ProductListView(rs.getInt("AllocationID"), rs.getInt("ProductID"), rs.getInt("SiteID"), rs.getInt("EPAID"), rs.getInt("GroupID"), rs.getString("ProductName"), rs.getDouble("ProductVal"), rs.getInt("TypeID"), rs.getString("UOM"));
                products.add(product);
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    
    public ArrayList<Integer> getIdsToDelete() {
        ArrayList<Integer> list = new ArrayList<>();
        for (ProductListView lw : allProducts) {
            boolean del = true;
            for (ProductListView ls : productsOnSite) {
                if(ls.getProductID()==lw.getProductID() && lw.getSiteID() == user.getSiteID())
                    del = false;
            }
            
            if(del){
                list.add(lw.getAllocationID());
            }
        }
        return list;
    }    
    

    public void relocateProducts(Date date) {
        //delete removed labors
        ArrayList<Integer> delIds = getIdsToDelete();
        
        for (Integer delId : delIds) {
            Object[][] w = {{"EndDate"},{ti.yesterday()}};
            Object[][] wh = {{"ID"},{"="},{delId},{}};   
            con.dbUpdate("ProductAllocation", w, wh);
        }
        
        //add new labors
        ArrayList<Integer> addIds = getIdsToAdd();
        for (Integer addId : addIds) {
            System.out.println(addId);
            allocateProducts(addId,user.getSiteID(),date);
        }
                
        
    }    

    public ArrayList<ProductListView> getProductsOnSiteList() {
        productsOnSite = new ArrayList<ProductListView>();
        for (ProductListView l : products) {
            if(l.getSiteID() == (int) user.getSiteID()){
                productsOnSite.add(l);
            }                
        }      
        for (int i = products.size()-1; i >= 0; i--) {
            if(products.get(i).getSiteID() == (int) user.getSiteID())
                products.remove(i);
        }
        return productsOnSite;
    }    

    private ArrayList<Integer> getIdsToAdd() {
        ArrayList<Integer> list = new ArrayList<>();
        for (ProductListView ls : productsOnSite) {
            boolean del = true;
            for (ProductListView lw : allProducts) {
                if(ls.getProductID()==lw.getProductID() && lw.getSiteID() == user.getSiteID())
                    del = false;
            }
            
            if(del){
                System.out.println(ls.getProductID());
                list.add(ls.getProductID());
            }
        }
        return list;        
    }

    private void allocateProducts(Integer addId, long siteID, Date date) {
        Object[][] data = {{"ProductID","SiteID","StartDate","EndDate"},{addId,user.getSiteID(),date,ti.nextDate()}};
        con.dbInsert("ProductAllocation", data);        
    }

    public void insertRecipe(JComboBox<String> itemBox, ArrayList<SubProducts> SubProd, JTextField RecipeName) {
        String s = "";        
        boolean write = true;
        DefaultComboBoxModel im = (DefaultComboBoxModel) itemBox.getModel();
        ProductListView ob = (ProductListView) im.getSelectedItem();
        
        //check if all ingredients was ok
        int counter = 0;
        for (SubProducts sb : SubProd) {
            if(sb.isVisible()){
                counter++;
                if(!sb.isNumber)
                    write = false;
            }
        }
        if(write){
            if(ob.getAllocationID() != 0){
                if(!RecipeName.getText().isEmpty()){
                    //create recipe
                    int lastID = con.dbInsert("Recipe", new Object[][]{{"ProductAllocationID","SiteID","RecName","Status"},{ob.getAllocationID(),con.userData.getSiteID(),RecipeName.getText(),1}});
                    if(lastID > 0){
                        for (SubProducts sp : SubProd) {
                            if(sp.isVisible()){
                                DefaultComboBoxModel model = (DefaultComboBoxModel) sp.selectedProduct.getModel();
                                ProductListView obj = (ProductListView) model.getSelectedItem();
                                con.dbInsert("RecipeRel", new Object[][]{{"RecID","ProductAllocationID","Used"},{lastID,obj.getAllocationID(),Double.parseDouble(sp.amount.getText())}});
                                System.out.println("done!");                         
                            }                  
                        }
                        JOptionPane.showMessageDialog(null,"Recipe sucesfully added");   
                    } else {
                        JOptionPane.showMessageDialog(null, "Unexpected database exception contact your administrator!" , "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have to set Recipe name" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No item has been selected" , "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {            
            JOptionPane.showMessageDialog(null, "Amount must be always numeric value" , "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }

    public ArrayList<RecipeIngredients> getRecipeIngredientsByID(int recMainProdID) {
        recipeIng = new ArrayList<RecipeIngredients>();
        String query = String.format("SELECT \n" +
        "RecipeREL.ID as ID,\n" +
        "RecipeREL.Used,\n" +
        "ProductAllocation.ProductID as ProdID\n" +
        "FROM RecipeREL\n" +
        "LEFT JOIN ProductAllocation on recipeREL.ProductAllocationID = ProductAllocation.ID\n" +
        "WHERE recID = %s ", recMainProdID);
        
        System.out.println(query);
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                RecipeIngredients list = new RecipeIngredients(rs.getInt("ID"), rs.getDouble("Used"), rs.getInt("ProdID"));
                recipeIng.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return recipeIng;
    }

    public void fillComboBoxWithProducts(JComboBox<String> box, int prodID) {
        DefaultComboBoxModel cModel = (DefaultComboBoxModel) box.getModel();
        cModel.removeAllElements();        
        for (ProductListView product : productsOnSite) {
            cModel.addElement(product);
            if(product.getProductID()==prodID){
                cModel.setSelectedItem(product);
            }
        }        
    }
    public void fillComboBoxWithProducts(JComboBox box){
        DefaultComboBoxModel cModel = (DefaultComboBoxModel) box.getModel();
        cModel.removeAllElements();        
        for (ProductListView product : productsOnSite) {
            cModel.addElement(product);            
        }
    }        

    public void updateRecipe(JComboBox<String> itemBox, ArrayList<SubProducts> subProd, JTextField RecipeName, int recID) {
        
    }
    
    
}
