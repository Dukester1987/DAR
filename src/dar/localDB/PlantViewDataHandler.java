/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Functions.TimeWrapper;
import dar.Gui.Gui;
import dar.dbObjects.PlantView;
import dar.dbObjects.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ldulka
 */
public class PlantViewDataHandler {
    
    private LocalWraper con;
    private User user;
    public ArrayList<PlantView> plantView;
    private Date dateFor;
    private JTable table;
    private JLabel utilPerc;
    private TimeWrapper ti;
    private JProgressBar utilProgressBar;
    private static ArrayList<Integer> rows;

    public PlantViewDataHandler(LocalWraper con, User user,JTable table,JLabel utilPerc,JProgressBar utilProgressBar) {
        this.con = con;
        this.user = user;
        this.table = table;
        this.utilPerc = utilPerc;
        this.utilProgressBar = utilProgressBar;
        hideID(table); //hide ID in JTable
        changeHeaders();
        this.ti = new TimeWrapper();
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
"                            WHERE StartDate <= '%s' AND EndDate >= '%s' AND SiteID = '%s' ORDER BY PlantID",dateFor,dateFor,dateFor,user.getSiteID());
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
                new FileLogger(ex.toString());
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
            boolean write = true;
            for(int k = model.getRowCount()-1;k>=0;k--){
                if((int) model.getValueAt(k, 1)==list.get(i).getAllocationID()){
                    write = false;
                    //System.out.printf("Leaving plant no: %s desc: %s\n",list.get(i).getPlantID(),list.get(i).getPlantDesc());
                }
            } 
            if(write){
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
            new FileLogger(ex.toString());
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
            new FileLogger(ex.toString());
        }
        return null;        
    }

    private void hideID(JTable table) { 
        //DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        //rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        //table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("UtilizationID"));
        table.removeColumn(table.getColumn("AllocationID"));        
    }
    
    private void changeHeaders() {
        // plant utilization
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
    }    

    public void updateTable(Date date, Gui g) {
        g.actionListenerGo=false;
        utilPercChange();
        int viewRow = table.getEditingRow();
        //System.out.println(viewRow);
        if(viewRow>-1){
            int k = table.convertRowIndexToView(viewRow);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int PlantUtilizationID = (int) model.getValueAt(k, 0);
            int PlantAllocationID = (int) model.getValueAt(k, 1);
            String PlantNo = (String) model.getValueAt(k, 2);
            String PlantDesc = (String) model.getValueAt(k, 3);
            int StartHours = (int) model.getValueAt(k, 4);
            int EndHours = (int) model.getValueAt(k, 5);
            double Fuel = (double) model.getValueAt(k, 6);
            String Notes = (String) model.getValueAt(k, 7);
            
            model.setValueAt(EndHours-StartHours, k, 8);
            
            if(EndHours<StartHours && EndHours!=0){
                JOptionPane.showMessageDialog(null,"End hours can not be lower than start hours!", "Error", JOptionPane.ERROR_MESSAGE);
                displayPlantViewInTable(table, date);
            } else {
                if(PlantUtilizationID==0){
                    EndHours = EndHours==0?StartHours:EndHours;
                    System.out.println("INSERTING INTO PLANTALLOCATION");
                    Object[][] query = {{"PlantAllocationID","StartHours","EndHours","DateFor","Fuel","Notes"},{PlantAllocationID,StartHours,EndHours,date,Fuel,Notes}};
                    int plantUtil = con.dbInsert("PlantUtilization", query);
                    model.setValueAt(plantUtil, k, 0);
                    model.setValueAt(EndHours, k, 5);
                    //displayPlantViewInTable(table, date); // refresh table
                } else {
                    // update operation
                    System.out.println("UPDATING INTO PLANTALLOCATION");
                    Object[][] query = {{"StartHours","EndHours","Fuel","Notes"},{StartHours,EndHours,Fuel,Notes==null?"":Notes}};
                    Object[][] where = {{"ID"},{"="},{PlantUtilizationID},{}};
                    if(con.getRowCount(con.dbSelect("PlantUtilization", new Object[][] {
                        {"StartHours","EndHours","Fuel","Notes","ID"},
                        {"=","=","=","=","="},
                        {StartHours,EndHours,Fuel,Notes,PlantUtilizationID},
                        {"AND","AND","AND","AND","AND"}                            
                    }))==0){
                        con.dbUpdate("PlantUtilization", query, where);   
                        //displayPlantViewInTable(table, date);
                    }
                }     
            }           
        }
        //System.out.println(k);
        g.actionListenerGo=true; 
    }
    
    public void addPlant(Date date) {
//        if(date.toString().equals(ti.today().toString())){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            String message = JOptionPane.showInputDialog(null, "Insert plant Number");
            if(message!=null){
                String Query = String.format("SELECT ID FROM `PlantAllocation` WHERE `PlantID` = '%s' AND `SiteID` = %s AND ((`StartDate` BETWEEN '%s' AND '%s' OR `EndDate` BETWEEN '%s' AND '%s') OR (`StartDate`<= '%s' AND `EndDate`>= '%s')) AND `StartDate`<=`EndDate`",message,con.userData.getSiteID(),date,ti.nextDate(),date,ti.nextDate(),date,ti.nextDate());
                if(isPlantDescription(message)){
                    ResultSet rs = con.runQuery(Query);
                    if(con.getRowCount(rs)>0){
                        if(date.before(ti.today()) && !date.toString().equals(ti.today().toString())){
                            int answer = JOptionPane.showConfirmDialog(null, "Plant is already in the list,\ndo you wanna extend it's starting date to selected one?");
                            if(answer==0){
                                try {
                                    rs.next();
                                    con.dbUpdate("PlantAllocation", new Object[][]{{"StartDate"},{date}}, new Object[][]{{"ID"},{"="},{rs.getInt("ID")},{}});
                                    displayPlantViewInTable(table,date);
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    new FileLogger(ex.toString());
                                }                                
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Plant is already in the list","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Object[][] dataset = new Object[][]{{"PlantID","SiteID","StartDate","EndDate"},{message,con.userData.getSiteID(),date,ti.nextDate()}};
                        con.dbInsert("PlantAllocation",dataset);
                        displayPlantViewInTable(table,date);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selected Plant No doesn't exists in the database","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
//        } else {
//            JOptionPane.showMessageDialog(null, "To add plant for previous days please contact your administrator!");
//        } 
    }    

    public void deleteSelectedRows(Date date, JTable PlantUtil) {    
        if(PlantUtil.getSelectedRowCount()>0){
            for (int rows : PlantUtil.getSelectedRows()) {
                int rowNo = PlantUtil.convertRowIndexToModel(rows);
                DefaultTableModel model = (DefaultTableModel) PlantUtil.getModel();
                int allocationID = (int) model.getValueAt(rowNo, 1);
                con.dbUpdate("PlantAllocation", new Object[][]{{"EndDate"},{ti.previousDay(date)}}, new Object[][]{{"ID"},{"="},{allocationID},{}});
                displayPlantViewInTable(table, date);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No rows selected", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static class rowColorer extends DefaultTableCellRenderer {
        public rowColorer(){
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.            
            for (int wo : rows) {
                if(row==wo){
                    //JOptionPane.showMessageDialog(null, row);
                    setBackground(new java.awt.Color(255,117,131));
                    if(isSelected){
                        setBackground(new java.awt.Color(226,31,38));
                    }   
                    if(hasFocus){
                        setBackground(new java.awt.Color(226,31,38));
                    }
                } else {
                    setBackground(Color.WHITE);
                    if(isSelected){
                        setBackground(new java.awt.Color(184,207,229));
                    }                    
                }
            }

            //setBackground(new java.awt.Color(255,117,131));
            return this;
        }
        
                
    }
            
    
    public void utilPercChange(){
        TableModel model = table.getModel();
        rows = new ArrayList<Integer>();
        rows.clear();
        int hoursTotal = 0;
        int optimum = model.getRowCount()*8;
        long percentage;
        for(int i=0;i<model.getRowCount(); i++){
            if(model.getValueAt(i, 5)!=null && (int) model.getValueAt(i, 5) != 0){
                int hours = (int) model.getValueAt(i, 5) - (int) model.getValueAt(i, 4);  
                hoursTotal += hours;   
                if(hours>16){
                    rows.add(i);
                }
            }   
        }
        table.setDefaultRenderer(Object.class, new rowColorer());
        table.setDefaultRenderer(Double.class, new rowColorer());        
        table.setDefaultRenderer(Integer.class, new rowColorer());
        
        if(hoursTotal<0 || optimum<=0){
           // apply percentage
           
        } else {
           // apply percentage
           percentage = Math.round((double) hoursTotal / (double) optimum*100);
           if(percentage<25){
               utilPerc.setForeground(Color.red);
           } else if(percentage>75 && percentage<120) {
               utilPerc.setForeground(Color.green);
           } else if(percentage>120) {
               utilPerc.setForeground(Color.red);
               JOptionPane.showMessageDialog(null,"Check your hours!","Error",JOptionPane.ERROR_MESSAGE);
           } else {
                utilPerc.setForeground(Color.BLACK);
           }
           utilPerc.setText(String.format("Utilization"));
           utilProgressBar.setValue((int) percentage);
        }        
    }     
    
}
