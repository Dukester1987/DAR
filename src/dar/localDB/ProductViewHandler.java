/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.TimeWrapper;
import dar.dbObjects.Production.ProductListView;
import dar.dbObjects.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

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
    
    public ProductViewHandler(LocalWraper con, User user,Date date) {
        this.con = con;
        this.ti = new TimeWrapper();
        this.user = user;
        this.dateFor = date;
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
    
    public void fillComboBoxWithProducts(JComboBox box){
        DefaultComboBoxModel cModel = (DefaultComboBoxModel) box.getModel();
        cModel.removeAllElements();        
        for (ProductListView product : productsOnSite) {
            cModel.addElement(product);
        }
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
    
    
}
