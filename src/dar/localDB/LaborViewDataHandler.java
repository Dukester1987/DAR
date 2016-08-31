/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.TimeWrapper;
import dar.dbObjects.LaborList;
import dar.dbObjects.LaborView;
import dar.dbObjects.User;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class LaborViewDataHandler extends DataHandler {
    
    private ArrayList<LaborView> laborView;
    private TimeWrapper ti;
    private Date dateFor;
    private DefaultTableModel model;
    private ArrayList<LaborList> laborList;
    private ArrayList<LaborList> laborOnSite;

    public LaborViewDataHandler(LocalWraper con, User user, JTable table, Date date) {
        super(con, user, table);
        this.ti = new TimeWrapper();
        this.model = (DefaultTableModel) table.getModel();
        this.dateFor = date;
        hideID(table);
        changeHeaders();
        createLaborList();
    }

    @Override
    public void displayViewInTable(JTable table, Date dateFor) {
        this.dateFor = dateFor;
        ArrayList<LaborView> list = getView();
        refreshTable(model);
        
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getUtilizationID(),
                list.get(i).getAllocationID(),
                list.get(i).getLaborName(),
                list.get(i).getFunction(),
                list.get(i).getHours(),
                list.get(i).getStatus(),
                list.get(i).getNotes()
            });            
        }        
        
    }
    
    private void hideID(JTable table) { 
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("UtilizationID"));
        table.removeColumn(table.getColumn("AllocationID"));        
    }    
    
    private void changeHeaders() {
        // plant utilization
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
    }   
    
    public ArrayList<LaborList> createLaborList(){
        laborList = new ArrayList<LaborList>();
        String query = String.format("SELECT\n" +
                        "LaborList.ID as ID,\n" +
                        "LaborAllocation.SiteID as SiteID,\n" +
                        "LaborList.LaborName as LaborName,\n" +
                        "LaborList.LaborFunction as FunctionID,\n" +
                        "LaborFunctions.Function as Function,\n" +
                        "LaborList.LaborRate as Rate\n" +
                        "FROM LaborList\n" +
                        "left join LaborFunctions on LaborFunction = LaborFunctions.ID\n" +
                        "left join LaborAllocation on LaborList.ID = LaborAllocation.LaborID AND LaborAllocation.StartDate<='%s' AND LaborAllocation.EndDate>='%s'",dateFor,dateFor);
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                LaborList list = new LaborList(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
                laborList.add(list);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return laborList;
    }

  
    
    
    
    
    public ArrayList<LaborView> getView(){
        laborView = new ArrayList<LaborView>();
        String query = String.format("SELECT \n" +
                                    "LaborAllocation.SiteID as SiteID,\n" +
                                    "LaborAllocation.ID as AllocationID,\n" +
                                    "LaborUtilization.ID as UtilizationID,\n" +
                                    "LaborList.ID as laborID,\n" +
                                    "LaborList.LaborName,\n" +
                                    "LaborFunctions.Function,\n" +
                                    "LaborUtilization.Hours,\n" +
                                    "WorkStatusList.Status,\n" +
                                    "LaborUtilization.Notes,\n" +
                                    "LaborUtilization.PlantID\n" +
                                    "FROM `LaborAllocation`\n" +
                                    "LEFT JOIN LaborList on LaborAllocation.LaborID = LaborList.ID\n" +
                                    "LEFT JOIN LaborUtilization ON LaborUtilization.LaborAllocationID = LaborAllocation.ID AND LaborUtilization.DateFor = '%s'\n" +
                                    "LEFT JOIN LaborFunctions on LaborList.LaborFunction = LaborFunctions.ID\n" +
                                    "LEFT JOIN WorkStatusList on WorkStatusList.ID = LaborUtilization.Status\n" +
                                    "WHERE LaborAllocation.StartDate <= '%s' AND LaborAllocation.EndDate >= '%s' and SiteID = %s"
                ,dateFor, dateFor, dateFor,user.getSiteID());
        
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                LaborView lw = new LaborView(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getString(9), rs.getString(10));
                laborView.add(lw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return laborView;
        
    }     

    public ArrayList<LaborView> getLaborView() {
        return laborView;
    }        

    public ArrayList<LaborList> getLaborList() {
        return laborList;
    }    
    
    public ArrayList<LaborList> getLaborsOnSiteList() {
        laborOnSite = new ArrayList<LaborList>();
        for (LaborList l : laborList) {
            if(l.getSiteID() == (int) user.getSiteID()){
                laborOnSite.add(l);
            }                
        }      
        for (int i = laborList.size()-1; i >= 0; i--) {
            if(laborList.get(i).getSiteID() == (int) user.getSiteID())
                laborList.remove(i);
        }
        return laborOnSite;
    }

    public ArrayList<Integer> getIdsToDelete() {
        ArrayList<Integer> list = new ArrayList<>();
        for (LaborView lw : laborView) {
            boolean del = true;
            for (LaborList ls : laborOnSite) {
                if(ls.getID()==lw.getLabourID())
                    del = false;
            }
            
            if(del){
                list.add(lw.getAllocationID());
            }
        }
        return list;
    }

    public ArrayList<Integer> getIdsToAdd() {
        ArrayList<Integer> list = new ArrayList<>();
        for (LaborList ls : laborOnSite) {
            boolean del = true;
            for (LaborView lw : laborView) {
                if(ls.getID()==lw.getLabourID())
                    del = false;
            }
            
            if(del){
                list.add(ls.getID());
            }
        }
        return list;        
    }

    public void realocateLabors(Date date) {
        //delete removed labors
        ArrayList<Integer> delIds = getIdsToDelete();
        
        for (Integer delId : delIds) {
            Object[][] w = {{"EndDate"},{ti.yesterday()}};
            Object[][] wh = {{"ID"},{"="},{delId},{}};   
            con.dbUpdate("LaborAllocation", w, wh);
        }
        
        //add new labors
        ArrayList<Integer> addIds = getIdsToAdd();
        for (Integer addId : addIds) {
            Object[][] data = {{"LaborID","SiteID","StartDate","EndDate"},{addId,user.getSiteID(),date,ti.nextDate()}};
            con.dbInsert("LaborAllocation", data);
        }
                
        
    }
    
}
