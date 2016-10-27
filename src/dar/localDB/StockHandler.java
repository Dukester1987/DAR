/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Functions.JControlers;
import dar.dbObjects.StockData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class StockHandler {

    private final LocalWraper db;
    private final JControlers controller;

    public StockHandler(LocalWraper db) {
        this.db = db;
        this.controller = new JControlers();
    }
    
    public ArrayList<StockData> getStockData(){
        ArrayList<StockData> list = new ArrayList<StockData>();
        String sql = String.format("SELECT \n" +
            "s.ProductID,\n" +
            "s.ProductName,\n" +
            "s.Production-s.UsedInProduction+s.SalesIN-s.SalesOUT+COALESCE(SUM(sa.Qty),0) as StockPile\n" +
            "FROM `Stock` s\n" +
            "left JOIN StockAdjustments sa on s.ProductID = sa.ProductID and s.SiteID = sa.SiteID\n" +
            "WHERE s.SiteID = %s\n" +
            "GROUP BY s.ProductID",db.userData.getSiteID());
        
        ResultSet rs = db.runQuery(sql);
        if(db.getRowCount(rs)>0){
            try {
                while(rs.next()){
                    StockData data = new StockData(rs.getInt("ProductID"), rs.getString("ProductName"), rs.getDouble("StockPile"));
                    list.add(data);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }
        }   
        return list;
    }
    
    public void displayStockInTable(JTable table){
        ArrayList<StockData> list = getStockData();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        controller.clearTable(model);
        for (StockData stockData : list) {
            model.addRow(new Object[]{
                stockData.getProductID(),
                stockData.getProductName(),
                stockData.getStock()
            });            
        }
    }
}
