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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
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

    public PlantViewDataHandler(LocalWraper con, User user) {
        this.con = con;
        this.user = user;
    }   
        
    public ArrayList<PlantView> getPlantView(){
        
        if(user.getSiteID()!=0){
            plantView = new ArrayList<PlantView>();
            String query = "SELECT * FROM `PlantView` WHERE (StartDate <= '"+dateFor+"' OR StartDate IS NULL) AND (EndDate IS NULL OR EndDate>='"+dateFor+"') AND (DateFor IS NULL OR DateFor = '"+dateFor+"') AND SiteID = "+user.getSiteID();            
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
            model.addRow(new Object[]{list.get(i).getSiteID(),
                list.get(i).getPlantID(),
                list.get(i).getPlantDesc(),
                list.get(i).getStartHours(),
                list.get(i).getEndHours(),
                list.get(i).getFuel(),
                list.get(i).getNotes()});
        }
    }

    private void refreshTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }

    public boolean isPlantDescription(String message) {
        Object[][] where = {{"PlantNo"},{"="},{message},{}};
        ResultSet rs = con.dbSelect("PlantList", where);
        try {
            return rs.last();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getPlantDescrition(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
