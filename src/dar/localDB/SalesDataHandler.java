/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Gui.Sales.SalesGui;
import dar.dbObjects.SalesDetailView;
import dar.dbObjects.SalesSummaryView;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class SalesDataHandler {
    private LocalWraper db;
    private static Date dateFor;
    public ArrayList<SalesSummaryView> salesSummaryList;
    public ArrayList<SalesDetailView> salesDetailList;
    private final double GST = 10;

    public SalesDataHandler(LocalWraper db,Date dateFor){        
        this.db = db;
        this.dateFor = dateFor;
    }    
    
    public ArrayList<SalesSummaryView> getSalesSummary(Date dateFor){
        ArrayList<SalesSummaryView> list = new ArrayList<SalesSummaryView>();
        String sql = String.format("SELECT\n" +
                                    "p.ID as ProductID,\n" +
                                    "p.ProductName as  ProductName,\n" +
                                    "sum(s.Amount) as Amount,\n" +
                                    "sum(s.PriceIncGST) as PriceIncGST,\n" +
                                    "sum(s.PriceExGST) as PriceExGST\n" +
                                    "FROM `Sales` s\n" +
                                    "LEFT JOIN Products p on p.ID = s.ProductID\n" +
                                    "WHERE\n" +
                                    "DateFor='%s' AND SiteID = %s\n" +
                                    "GROUP BY p.ProductName",dateFor,db.userData.getSiteID());
        ResultSet rs = db.runQuery(sql);
        if(db.getRowCount(rs)>0){
            try {
                //write down into arrayList
                while(rs.next()){
                    SalesSummaryView view = new SalesSummaryView(rs.getInt("ProductID"),
                            rs.getString("ProductName"),
                            rs.getDouble("Amount"),
                            rs.getDouble("PriceIncGST"),
                            rs.getDouble("PriceExGST"));
                    list.add(view);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }            
        }
        return list;
    }
    
    public void displaySummaryInTable(JTable table, Date dateFor){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        clearTable(model);
        salesSummaryList = getSalesSummary(dateFor);
        for (SalesSummaryView salesSummaryView : salesSummaryList) {
            model.addRow(new Object[]{
                salesSummaryView.getProductID(),
                salesSummaryView.getProductName(),
                salesSummaryView.getTonage(),
                salesSummaryView.getPriceIncGST(),
                salesSummaryView.getPriceExtGST(),
                salesSummaryView.getPricePerTon()
            });
        }        
    }
    
    public ArrayList<SalesDetailView> getSalesDetail(Date dateFor,int ProductID){
        ArrayList<SalesDetailView> list = new ArrayList<SalesDetailView>();
        String subQuery = "";
        if(ProductID>0){
            subQuery = String.format("AND ProductID = %s", ProductID);
        }     
        String sql = String.format("SELECT \n" +
                                    "s.ID as ID,\n" +
                                    "p.ProductName as ProductName,\n" +
                                    "s.Direction as Direction,\n" +
                                    "s.Amount as Tonage,\n" +
                                    "s.`PriceIncGST` as `PriceIncGST`,\n" +
                                    "s.`PriceExGST` as `PriceExGST`,\n" +
                                    "e.Rate\n" +
                                    "FROM `Sales` s \n" +
                                    "left join Products p on s.`ProductID` = p.ID\n" +
                                    "left join EPA e on e.ID = p.EPA AND e.DateFor<='%s'\n" +
                                    "WHERE s.DateFor = '%s'\n" +
                                    "AND s.SiteID = %s %s", dateFor,dateFor,db.userData.getSiteID(),subQuery);
        ResultSet rs = db.runQuery(sql);
        if(db.getRowCount(rs)>0){
            try {
                while(rs.next()){
                    SalesDetailView view = new SalesDetailView(rs.getInt("ID"),
                            rs.getString("Productname"),
                            rs.getString("Direction"),
                            rs.getDouble("Tonage"),
                            rs.getDouble("PriceIncGST"),
                            rs.getDouble("PriceExGST"),
                            rs.getDouble("Rate"));
                    list.add(view);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }
        }
        return list;
    }
    
    public void displayDetailInTable(JTable table, Date dateFor,int productID){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        clearTable(model);
        salesDetailList = getSalesDetail(dateFor, productID);
        for (SalesDetailView view : salesDetailList) {
            model.addRow(new Object[]{
                view.getSalesID(),
                view.getProductName(),
                view.getDirection(),
                view.getTonage(),
                view.getPriceIncGST(),
                view.getPriceExtGST(),
                view.getPricePerTon(),
                view.getEPAPerTon()
            });
        }         
    }
    
    public void setDate(Date date){
        this.dateFor = date;
    }

    private void clearTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }

    public void UpdateSales(int updatedRow, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int SalesID = (int) model.getValueAt(updatedRow, 0);
        double tonage = (double) model.getValueAt(updatedRow, 3);
        double price = (double) model.getValueAt(updatedRow, 4);
       
        // check whether something really changed in the database
        ResultSet rs = db.dbSelect("Sales", new Object[][] {{"ID","Amount","PriceIncGST"},{"=","=","="},{SalesID,tonage,price},{"AND","AND","AND"}});
        if(!db.hasDuplicity(rs)){ //value changed
            double excGST = price/(GST/100+1D);
            
            SalesGui.actionListenersGo = false; //this change won't triger this method recursively
            model.setValueAt(excGST, updatedRow, 5);
            SalesGui.actionListenersGo = true;
            
            System.out.printf("Updated ID is %s and tonage is %s and price is %s and new price exc GST is %s",SalesID,tonage,price,excGST);
            System.out.println("");     
            
            //update database add new values
            db.dbUpdate("Sales",new Object[][]{{"Amount", "PriceIncGST", "PriceExGST"},{tonage,price,excGST}}, new Object[][]{{"ID","SiteID"},{"=","="},{SalesID,db.userData.getSiteID()},{"AND"}});
            JOptionPane.showMessageDialog(null,"Sales successfuly updated!","Updated",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

}
