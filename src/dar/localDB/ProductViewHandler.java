/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Functions.JControlers;
import dar.Functions.TimeWrapper;
import dar.Functions.tableRenderers.NumberTableCellRenderer;
import dar.Gui.Gui;
import dar.Gui.Production.AddProduct;
import dar.Gui.Production.SubProducts;
import dar.Gui.Production.moreRecipes;
import dar.Gui.Production.recipeDetail;
import dar.dbObjects.Production.ProductListView;
import dar.dbObjects.Production.ProductUtilizationView;
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
import javax.swing.JTextArea;
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
    private ArrayList<ProductUtilizationView> prodUtilView;
    private ArrayList<recipeDetail> rcpList;
    private JControlers controller;
    
    public ProductViewHandler(LocalWraper con, User user,Date date) {
        this.con = con;
        this.ti = new TimeWrapper();
        this.user = user;
        this.dateFor = date;

        
        prodUtilView = getProductUtilizationView(date);
        
    }
    
    public void hideID(JTable table) { 
        controller = new JControlers();
        controller.setTableCellRenderer(table, 3, new NumberTableCellRenderer());        
        controller.hideColumn(table, "UtilizationID");
        controller.hideColumn(table, "AllocationID");     
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
            new FileLogger(ex.toString());
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
        "AND ProductAllocation.EndDate >= '%s' AND ProductAllocation.SiteID = %s ORDER BY ProductName", dateFor,dateFor,con.userData.getSiteID());
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                ProductListView product = new  ProductListView(rs.getInt("AllocationID"), rs.getInt("ProductID"), rs.getInt("SiteID"), rs.getInt("EPAID"), rs.getInt("GroupID"), rs.getString("ProductName"), rs.getDouble("ProductVal"), rs.getInt("TypeID"), rs.getString("UOM"));
                products.add(product);
                allProducts.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return products;
    }
    
    public ArrayList<Integer> getIdsToDelete() {
        ArrayList<Integer> list = new ArrayList<>();
        for (ProductListView lw : allProducts) {
            boolean del = true;
            for (ProductListView ls : productsOnSite) {
                if(ls.getProductID()==lw.getProductID() && lw.getSiteID() == user.getSiteID()){
                    //JOptionPane.showMessageDialog(null,String.format("Allprod ID: %s allocatedID: %s",lw.getProductID(),ls.getProductID()));
                    del = false;
                }
            }
            
            if(del && lw.getAllocationID()>0){                                
                list.add(lw.getAllocationID());
            }
        }
        return list;
    }    
    

    public void relocateProducts(Date date) {
        //delete removed products
        ArrayList<Integer> delIds = getIdsToDelete();
        
        //JOptionPane.showMessageDialog(null,delIds.size() + " IDs to delete");
        
        for (Integer delId : delIds) {
            Object[][] w = {{"EndDate"},{ti.yesterday()}};
            Object[][] wh = {{"ID"},{"="},{delId},{}};   
            con.dbUpdate("ProductAllocation", w, wh);
            deleteUtilForProducts(delId,ti.yesterday());
        }
        
        //add new products
        ArrayList<Integer> addIds = getIdsToAdd();
        //JOptionPane.showMessageDialog(null,addIds.size() + " Ids to add");
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
                //System.out.println(ls.getProductID());
                list.add(ls.getProductID());
            }
        }
        return list;        
    }

    private void allocateProducts(Integer addId, long siteID, Date date) {
        Object[][] data = {{"ProductID","SiteID","StartDate","EndDate"},{addId,user.getSiteID(),date,ti.nextDate()}};
        con.dbInsert("ProductAllocation", data);        
    }

    public boolean insertRecipe(JComboBox<String> itemBox, ArrayList<SubProducts> SubProd, JTextField RecipeName) {
        String s = "";        
        boolean write = true;
        DefaultComboBoxModel im = (DefaultComboBoxModel) itemBox.getModel();
        ProductListView ob = (ProductListView) im.getSelectedItem();
        
        //check if all ingredients was o

        if(checkSubProdAmount(SubProd) && isNameEmpty(RecipeName)){
            if(ob.getAllocationID() != 0){
                    //create recipe
                    int lastID = con.dbInsert("Recipe", new Object[][]{{"ProductAllocationID","SiteID","RecName","Status"},{ob.getAllocationID(),con.userData.getSiteID(),RecipeName.getText(),1}});
                    if(lastID > 0){
                        for (SubProducts sp : SubProd) {
                            if(sp.isVisible()){
                                DefaultComboBoxModel model = (DefaultComboBoxModel) sp.selectedProduct.getModel();
                                ProductListView obj = (ProductListView) model.getSelectedItem();
                                con.dbInsert("RecipeRel", new Object[][]{{"RecID","ProductAllocationID","Used"},{lastID,obj.getAllocationID(),Double.parseDouble(sp.amount.getText())}});
                                //System.out.println("done!");                         
                            }                  
                        }
                        JOptionPane.showMessageDialog(null,"Recipe sucesfully added");   
                    } else {
                        write = false;
                        JOptionPane.showMessageDialog(null, "Unexpected database exception contact your administrator!" , "Error", JOptionPane.ERROR_MESSAGE); 
                    }
            } else {
                write = false;
                JOptionPane.showMessageDialog(null, "No item has been selected" , "Error", JOptionPane.ERROR_MESSAGE);
            } 
        } else {
            write = false;
        }
        return write;
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
        
        //System.out.println(query);
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                RecipeIngredients list = new RecipeIngredients(rs.getInt("ID"), rs.getDouble("Used"), rs.getInt("ProdID"));
                recipeIng.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
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

    public boolean updateRecipe(JComboBox<String> itemBox, ArrayList<SubProducts> subProd, JTextField RecipeName, int recID) {
       boolean write = true;
       if(checkSubProdAmount(subProd) && isNameEmpty(RecipeName)){
           ProductListView ob = (ProductListView) itemBox.getSelectedItem();
           if(ob.getAllocationID()!=0){
               //all checks done, start updating
               //update name and recipe main item
               Object[][] o = new Object[][]{{"ProductAllocationID","RecName"},{ob.getAllocationID(),RecipeName.getText()}};
               Object[][] w = new Object[][]{{"ID"},{"="},{recID},{}};
               con.dbUpdate("Recipe", o, w);
              
               //delete all previous ingredients
               String idName = "RecID";
               ResultSet dbSelect = con.dbSelect(new Object[]{"ID"}, "RecipeRel", new Object[][]{{idName},{"="},{recID},{}});
               try {
                   //con.dbDelete("RecipeRel",new Object[][]{{idName},{"="},{recID},{}}, idName);
                   while(dbSelect.next()){
                       con.dbDelete("RecipeRel",new Object[][]{{"ID"},{"="},{dbSelect.getInt("ID")},{}}, "ID");
                   }
               } catch (SQLException ex) {
                   new FileLogger(ex.getStackTrace());
               }
               
               //Insert new ingredients
                for (SubProducts sp : subProd) {
                    if(sp.isVisible()){
                        DefaultComboBoxModel model = (DefaultComboBoxModel) sp.selectedProduct.getModel();
                        ProductListView obj = (ProductListView) model.getSelectedItem();
                        con.dbInsert("RecipeRel", new Object[][]{{"RecID","ProductAllocationID","Used"},{recID,obj.getAllocationID(),Double.parseDouble(sp.amount.getText())}});                                              
                    }                  
                }
                JOptionPane.showMessageDialog(null,"Recipe sucesfully updated");
               
           } else {
               write = false;
               JOptionPane.showMessageDialog(null, "No item has been selected" , "Error", JOptionPane.ERROR_MESSAGE);
           }
       } else {
           write = false;
       }
        
       return write;
    }

    private boolean checkSubProdAmount(ArrayList<SubProducts> SubProd) {
        boolean write = true;
        int counter = 0;
        for (SubProducts sb : SubProd) {
            if(sb.isVisible()){
                counter++;
                if(!sb.isNumber)
                    write = false;
            }
        }
        if(!write)
            JOptionPane.showMessageDialog(null, "All ingredient's has to be set and contain numeric values!","Error", JOptionPane.ERROR_MESSAGE);
        return write;
    }

    private boolean isNameEmpty(JTextField RecipeName) {
        boolean write = true;
        if(RecipeName.getText().isEmpty()){
            write = false;
            JOptionPane.showMessageDialog(null, "Recipe name can not be empty!","Error", JOptionPane.ERROR_MESSAGE);
        }
        return write;
    }

        public boolean utilizeProducts(ProductListView item, JTextField produced, JTextArea Notes,Date date,int TrType,Gui g,AddProduct p) {
        boolean write = true;
        boolean success = false;
        String msg = "";
        String msgType = "Error";
        int iconType = JOptionPane.ERROR_MESSAGE;
        
        Double amount = 0D;
        // runn all checks
        
        //check for numeric value
        try{
            amount = Double.parseDouble(produced.getText());            
        }catch(Exception e){
            write = false;
            msg = "Amount has to be numeric value!";
        }
        
        //check if product is already utilized for today
//        if(!prodUtilCheck(date, item.getAllocationID(),TrType)){
//            write = false;
//            msg = "Product is already utilised";
//        }
        boolean displayMessage = true;
        if(write){
            
            // check if there are any recipes for selected products
            ResultSet rs = getRecipesForAllocationID(item.getAllocationID());
            int count = con.getRowCount(rs);
            
            if(count>0 && count<=1 && TrType==3){
                try {
                    //add also used in production based on recipe
                    rs.next();
                    //get ingredients from recipe
                    
                    
                    ResultSet rr = getIngredientsByRecipeID(rs.getInt("ID"));
                    msg = String.format("Recipe %s found\nAdding to used in production:\n", rs.getString("RecName"));
                    while(rr.next()){
                        msg += String.format("%s: %s\n", rr.getString("ProductName"),rr.getDouble("Used")*amount);
                        //decide wheter product is already utilised or not if yes update if not add                        
                        insertProduction(rr.getInt("PRODUCTALLOCATIONID"), rr.getDouble("Used")*amount, Notes.getText(), date, 4);
                    }
                    insertProduction(item.getAllocationID(),amount,Notes.getText(),date,TrType);
                    msgType = "Info";
                    iconType = JOptionPane.INFORMATION_MESSAGE;  
                    success = true;                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    new FileLogger(ex.toString());
                }
            } else if (count>1){
                rcpList = new ArrayList<recipeDetail>();
                try {
                    //msg = "There are more recipes for selected product\nDelete all other recipes and try it again!";
                    //give possibility to select what recipe will be used
                    displayMessage = false;
                    while(rs.next()){
                        recipeDetail rcl = new recipeDetail(rs.getInt("ID"), rs.getString("RecName"));
                        rcpList.add(rcl);
                    }
                    moreRecipes rcp = new moreRecipes(rcpList, this, amount, Notes.getText(),item.getAllocationID(),date,TrType,p,g);
                    rcp.setLocationRelativeTo(null);
                    rcp.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    new FileLogger(ex.toString());
                }
            } else {
                //msg = "Production updated!";
                displayMessage = false;
                msgType = "Info";
                iconType = JOptionPane.INFORMATION_MESSAGE;
                //add just production
                insertProduction(item.getAllocationID(),amount,Notes.getText(),date,TrType);
                success = true;
            }
            
        }
        if(displayMessage)
            JOptionPane.showMessageDialog(null,msg,msgType,iconType);
        return success;
    }

    private boolean prodUtilCheck(Date date, int allocationID, int TransType) {
        boolean ret = true;
        String query = String.format("SELECT * FROM ProductUtilization WHERE ProductAllocationID = %s AND DATEFOR = '%s' AND TransactionType = %s", allocationID,date,TransType);
        //System.out.println(query);
        if(con.hasDuplicity(con.runQuery(query))){
            ret = false;
        }
        return ret;
    }

    private ResultSet getRecipesForAllocationID(int allocationID) {
        String query = String.format("SELECT ID, RecName FROM RECIPE WHERE STATUS = TRUE AND SiteID = %s AND ProductAllocationID = %s", con.userData.getSiteID(),allocationID);
        //System.out.println(query);
        return con.runQuery(query);                     
    }

    public int insertProduction(int allocationID, Double amount, String text, Date date, int TrType) {
        String query = String.format("SELECT ID FROM ProductUtilization WHERE ProductAllocationID = %s AND DateFor = '%s' AND TransactionType = %s", allocationID,date, TrType);
        //System.out.println(query);
        ResultSet rs = con.runQuery(query);
        if(con.getRowCount(rs)>0){
            try {
                rs.next();
                int id = rs.getInt("ID");
                con.dbUpdate("ProductUtilization", new Object[][] {{"Qty"},{"--Qty+"+amount}}, new Object[][]{{"ID"},{"="},{id},{}});
                return id;
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
                return -1;
            }
        } else {
            return con.dbInsert("ProductUtilization", new Object[][] {{"ProductAllocationID","Qty","Notes","TransactionType","DateFor"},{allocationID,amount,text,TrType,date}});
        }        
    }

    public ResultSet getIngredientsByRecipeID(int aInt) {
        return con.runQuery(String.format("SELECT PRODUCTALLOCATIONID,ProductName, USED FROM RECIPEREL\n" +
        "LEFT JOIN PRODUCTALLOCATION ON PRODUCTALLOCATION.ID = RECIPEREL.PRODUCTALLOCATIONID \n" +
        "LEFT JOIN PRODUCTS ON PRODUCTS.ID = PRODUCTALLOCATION.PRODUCTID\n" +
        " WHERE RECID = %s", aInt));
    }
       
    private ArrayList<ProductUtilizationView> getProductUtilizationView(Date date){
        dateFor = date;
        prodUtilView = new ArrayList<ProductUtilizationView>();
        
        String query = String.format("SELECT \n" +
        "PRODUCTUTILIZATION.ID as UtilizationID,\n" +
        "PRODUCTALLOCATION.ID as AllocationID,\n" +
        "PRODUCTS.PRODUCTNAME,\n" +
        "PRODUCTUTILIZATION.QTY as QTY,\n" +
        "PRODUCTUTILIZATION.NOTES as Notes,\n" +
        "PRODUCTUTILIZATION.TRANSACTIONTYPE as Type,\n" +
        "PRODUCTUTILIZATION.DateFor as  DateFor\n" +
        "FROM PRODUCTUTILIZATION \n" +
        "LEFT JOIN PRODUCTALLOCATION on PRODUCTALLOCATION.ID = PRODUCTUTILIZATION.PRODUCTALLOCATIONID \n" +
        "LEFT JOIN PRODUCTS ON PRODUCTALLOCATION.PRODUCTID = PRODUCTS.ID\n"
        + "WHERE DateFor = '%s' AND PRODUCTALLOCATION.SITEID = %s", dateFor,user.getSiteID());
        ResultSet rs = con.runQuery(query);
        
        if(con.getRowCount(rs)>0){
            try {
                while(rs.next()){
                    ProductUtilizationView pView = new ProductUtilizationView(rs.getInt("UtilizationID"), rs.getInt("AllocationID"), rs.getString("ProductName"), rs.getDouble("qty"), rs.getString("Notes"), rs.getInt("Type"), rs.getDate("DateFor"));
                    prodUtilView.add(pView);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }
        } else {
            //System.out.println(query);
        }
        return prodUtilView;
    }
    
    public void displayUtilizationInTable(JTable table, int Type, Date date){          
        prodUtilView = getProductUtilizationView(date);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        refreshTable((DefaultTableModel) table.getModel());
        for (ProductUtilizationView pView : prodUtilView) {  
            boolean write = true;
            for(int i = model.getRowCount()-1;i>=0;i--){
                if((int) model.getValueAt(i, 1)==pView.getAllocationID())
                    write = false;
            }
            if(write){
                if(pView.getType()==Type){
                    model.addRow(new Object[]{
                        pView.getUtilizationID(),
                        pView.getAllocationID(),
                        pView.getProductName(),
                        pView.getQty(),
                        pView.getNotes()
                    });
                }
            }
        }

    }

    public void updateProduct(DefaultTableModel model, int row) {
        int ID = (int) model.getValueAt(row,0);
        int AllocationID = (int) model.getValueAt(row,1);
        double amount = (double) model.getValueAt(row, 3);
        String notes = (String) model.getValueAt(row, 4);                    
        con.dbUpdate("ProductUtilization", new Object[][]{{"Qty","Notes","ApprovalID"},{amount,notes,"NULL"}}, new Object[][]{{"ID","ProductAllocationID"},{"=","="},{ID,AllocationID},{"AND"}});                            
    }

    private void deleteUtilForProducts(Integer delId, Date previousDay) {
        con.dbDelete("ProductUtilization", new Object[][]{
            {"ProductAllocationID","DateFor"},
            {"=",">"},
            {delId,previousDay},
            {"AND"}
        }, "ProductAllocationID");
    }
    
}
