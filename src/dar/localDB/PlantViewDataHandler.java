/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.dbObjects.PlantView;
import dar.dbObjects.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class PlantViewDataHandler {
    
    private LocalWraper con;
    private User user;
    public ArrayList<PlantView> plantView;
    private Date dateFor;

    public PlantViewDataHandler(LocalWraper con, User user,JTable table) {
        this.con = con;
        this.user = user;
        hideID(table); //hide ID in JTable
    }   
        
    public ArrayList<PlantView> getPlantView(){
        
        if(user.getSiteID()!=0){
            plantView = new ArrayList<PlantView>();
            String query = String.format("SELECT PlantAllocation.ID AS AllocationID,\n" +
"                            `SiteList`.`ID` AS `SiteID`,\n" +
"                            `PlantUtilization`.`ID` AS `UtilizationID`,\n" +
"                            `PlantList`.`ID` AS `PlantID`,\n" +
"                            `PlantList`.`PlantDesc` AS `PlantDesc`,\n" +
"                            `PlantList`.`Rate` AS `Rate`,\n" +
"                            CASE WHEN PlantUtilization.StartHours IS NULL THEN \n" +
"(SELECT PlantUtilization.EndHours FROM PlantAllocation\n" +
"LEFT JOIN PlantUtilization on PlantAllocation.ID = PlantUtilization.PlantAllocationID\n" +
"WHERE PlantID = PlantList.ID\n" +
"ORDER BY PlantUtilization.EndHours DESC\n" +
"LIMIT 0,1)                             \n" +
"                             ELSE PlantUtilization.StartHours END as StartHours,\n" +
"                            `PlantUtilization`.EndHours as EndHours,\n" +
"                            `PlantUtilization`.`DateFor` AS `DateFor`,\n" +
"                            `PlantUtilization`.`Fuel` AS `Fuel`,\n" +
"                            `PlantAllocation`.`StartDate` AS `StartDate`,\n" +
"                            `PlantAllocation`.`EndDate` AS `EndDate`,\n" +
"                            `PlantUtilization`.`Notes` AS `Notes` \n" +
"                            from `PlantAllocation` \n" +
"                            left join `PlantUtilization` on`PlantAllocation`.`ID` = `PlantUtilization`.`PlantAllocationID` AND DateFor = '%s'\n" +
"                            left join `PlantList` on `PlantAllocation`.`PlantID` = `PlantList`.`ID`\n" +
"                            left join `SiteList` on `SiteList`.`ID` = `PlantAllocation`.`SiteID`\n" +
"                            WHERE StartDate <= '%s' AND EndDate >= '%s' AND SiteID = '%s'",dateFor,dateFor,dateFor,user.getSiteID());
            ResultSet rs = con.runQuery(query);
            try {
                while(rs.next()){
                    PlantView plantUtil = new PlantView(rs.getInt("AllocationID"),
                            rs.getInt("SiteID"),
                            rs.getInt("UtilizationID"),
                            rs.getString("PlantID"),
                            rs.getString("PlantDesc"),
                            rs.getDouble("rate"),
                            rs.getInt("StartHours"),
                            rs.getInt("EndHours"),
                            rs.getDate("DateFor"),
                            rs.getDouble("Fuel"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            rs.getString("Notes"));
                    plantView.add(plantUtil);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return plantView;
    }
    
    public void displayPlantViewInTable(JTable table, Date dateFor){
        this.dateFor = dateFor;
        ArrayList<PlantView> list = getPlantView();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        refreshTable(model);
        for(int i = 0;i<list.size();i++){
            int hours = list.get(i).getEndHours()-list.get(i).getStartHours();
            int display = hours<0?0:hours;
            model.addRow(new Object[]{list.get(i).getUtilizationID(),
                list.get(i).getAllocationID(),
                list.get(i).getPlantID(),
                list.get(i).getPlantDesc(),
                list.get(i).getStartHours(),
                list.get(i).getEndHours(),
                list.get(i).getFuel(),
                list.get(i).getNotes(),
                display                    
            });
        }
    }

    private void refreshTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }

    public boolean isPlantDescription(String message) {
        Object[][] where = {{"ID"},{"="},{message},{}};
        ResultSet rs = con.dbSelect("PlantList", where);
        try {
            return rs.last();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getPlantDescrition(String message) {
        Object[][] where = {{"ID"},{"="},{message},{}};
        ResultSet rs = con.dbSelect("PlantList", where);
        try {
            rs.first();
            return rs.getString("PlantDesc");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;        
    }

    private void hideID(JTable table) { 
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("UtilizationID"));
        table.removeColumn(table.getColumn("AllocationID"));        
    }
    
}
