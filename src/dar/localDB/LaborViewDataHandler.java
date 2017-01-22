/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Functions.JControlers;
import dar.Functions.TimeWrapper;
import dar.dbObjects.LaborFunctions;
import dar.dbObjects.LaborList;
import dar.dbObjects.LaborStatus;
import dar.dbObjects.LaborView;
import dar.dbObjects.User;
import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
    private ArrayList<LaborFunctions> laborFunctions;
    private ArrayList<LaborStatus> statusList;
    private final JControlers fieldController;

    public LaborViewDataHandler(LocalWraper con, User user, JTable table, Date date) {
        super(con, user, table);
        this.ti = new TimeWrapper();
        this.model = (DefaultTableModel) table.getModel();
        this.dateFor = date;
        fieldController = new JControlers();
        hideID(table);
        changeHeaders();
        //createLaborList();
        //createFunctionsList();
    }

    @Override
    public void displayViewInTable(JTable table, Date dateFor) {
        this.dateFor = dateFor;
        ArrayList<LaborView> list = getView();
        refreshTable(model);        
        for (int i = 0; i < list.size(); i++) {
            boolean write = true;
            for(int k = model.getRowCount()-1;k>=0;k--){
                if((int) model.getValueAt(k, 1)==list.get(i).getAllocationID()){
                    write = false;
                }
            }
            if(write){
                LaborStatus status = new LaborStatus(list.get(i).getStatusID(), list.get(i).getStatus());
                //System.out.println(list.get(i).getStatusID()+" "+list.get(i).getStatus()); // HERE!!!
                model.addRow(new Object[]{
                    list.get(i).getUtilizationID(),
                    list.get(i).getAllocationID(),
                    list.get(i).getLaborName(),
                    list.get(i).getFunction(),
                    list.get(i).getHours(),
                    status,
                    list.get(i).getNotes()
                }); 
            }
        }        
        
    }
    

    
    private void hideID(JTable table) { 
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        JComboBox laborStatus = new JComboBox(new DefaultComboBoxModel());
        fillComboBoxBySatus(laborStatus);       
        
        // change cell editor to JTextField
        fieldController.jTableSelectAll(table,new String[]{"Notes"});
       
        
        // add combobox
        TableColumn col = table.getColumnModel().getColumn(5);
        col.setCellEditor(new DefaultCellEditor(laborStatus)); 
        
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("UtilizationID"));
        table.removeColumn(table.getColumn("AllocationID"));        
    }    
    
    private void changeHeaders() {
        // plant utilization
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
    }   
    
    public ArrayList<LaborFunctions> createFunctionsList(){
        laborFunctions = new ArrayList<LaborFunctions>();
        String query = ("SELECT * FROM `LaborFunctions`");
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                LaborFunctions fn = new LaborFunctions(rs.getInt("ID"), rs.getString("Function"));
                laborFunctions.add(fn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return laborFunctions;
    }
    
    public void fillComboBoxWithFunctions(JComboBox box){
        DefaultComboBoxModel cModel = (DefaultComboBoxModel) box.getModel();
        cModel.removeAllElements();
        for (LaborFunctions function : laborFunctions) {
            cModel.addElement(function);
        }
    }
    
    
    
    public ArrayList<LaborList> createLaborList(Date dateFor){
        this.dateFor = dateFor;
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
                        "left join LaborAllocation on LaborList.ID = LaborAllocation.LaborID AND LaborAllocation.StartDate<='%s' AND LaborAllocation.EndDate>='%s' AND SiteID = '%s'",dateFor,dateFor,user.getSiteID());
        ResultSet rs = con.runQuery(query);
//        System.out.println(query);
        try {
            while(rs.next()){
                LaborList list = new LaborList(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));
                laborList.add(list);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return laborList;
    }

  
    public ArrayList<LaborStatus> getStatusList(){
        statusList = new ArrayList<LaborStatus>();
        String query = ("SELECT * FROM LaborStatus");
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                LaborStatus list = new LaborStatus(rs.getInt("ID"), rs.getString("Status"));
                statusList.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return statusList;
    }
    
    public void fillComboBoxBySatus(JComboBox box){
        DefaultComboBoxModel cMod = (DefaultComboBoxModel) box.getModel();
        cMod.removeAllElements();
        ArrayList<LaborStatus> list = statusList;
        if(statusList == null){
            list = getStatusList();
        }
        for (LaborStatus sList : list) {
            box.addItem(sList);
        }
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
                                    "LaborStatus.Status,\n" +
                                    "LaborStatus.ID StatusID,\n" +
                                    "LaborUtilization.Notes,\n" +
                                    "LaborUtilization.PlantID\n" +
                                    "FROM `LaborAllocation`\n" +
                                    "LEFT JOIN LaborList on LaborAllocation.LaborID = LaborList.ID\n" +
                                    "LEFT JOIN LaborUtilization ON LaborUtilization.LaborAllocationID = LaborAllocation.ID AND LaborUtilization.DateFor = '%s'\n" +
                                    "LEFT JOIN LaborFunctions on LaborList.LaborFunction = LaborFunctions.ID\n" +
                                    "LEFT JOIN LaborStatus on LaborStatus.ID = LaborUtilization.Status\n" +
                                    "WHERE LaborAllocation.StartDate <= '%s' AND LaborAllocation.EndDate >= '%s' and SiteID = %s ORDER BY LaborName"
                ,dateFor, dateFor, dateFor,user.getSiteID());
        //System.out.println(query);
        ResultSet rs = con.runQuery(query);
//        System.out.println(query);
        try {
            while(rs.next()){
                LaborView lw = new LaborView(rs.getInt("SiteID"), rs.getInt("AllocationID"), rs.getInt("UtilizationID"), rs.getInt("LaborID"), rs.getString("LaborName"), rs.getString("Function"), rs.getDouble("Hours"), rs.getString("Status"),rs.getInt("StatusID"), rs.getString("Notes"), rs.getString("PlantID"));
                laborView.add(lw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
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
            Object[][] w = {{"EndDate"},{ti.previousDay(date)}};
            Object[][] wh = {{"ID"},{"="},{delId},{}};   
            con.dbUpdate("LaborAllocation", w, wh);
            deleteUtilForLabor(delId,ti.previousDay(date));
        }
        
        //add new labors
        ArrayList<Integer> addIds = getIdsToAdd();
        for (Integer addId : addIds) {
            allocateLabours(addId,user.getSiteID(),date);
        }
                
        
    }

    public void addNewLabor(JTextField lName, JComboBox<String> lFunc) {
        //get information from user inputs
        DefaultComboBoxModel cModel = (DefaultComboBoxModel) lFunc.getModel();
        LaborFunctions selectedFunc = (LaborFunctions) cModel.getSelectedItem();
        String dbTable = "LaborList";
        
        //check if labour already exists
        
        if(con.hasDuplicity(con.dbSelect(dbTable, new Object[][]{{"LaborName"},{"="},{lName.getText()},{}}))){
            JOptionPane.showMessageDialog(null,"Labour already exists!", "Duplicity error", JOptionPane.ERROR_MESSAGE);
        } else if(lName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Name has not been filled", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // add labour into a database
            int newID = con.dbInsert(dbTable, new Object[][]{{"LaborName","LaborFunction","LaborRate"},{lName.getText(),selectedFunc.getID(),"25"}});
            
            // allocate labour to the site
            if(newID>0){
                allocateLabours(newID, user.getSiteID(), dateFor);
            } else {
              JOptionPane.showMessageDialog(null,"Can't allocate Labour", "Database error", JOptionPane.ERROR_MESSAGE);  
            }
                        
        }
    }

    private void allocateLabours(Integer addId, long siteID, Date date) {
        Object[][] data = {{"LaborID","SiteID","StartDate","EndDate"},{addId,user.getSiteID(),date,ti.nextDate()}};
        con.dbInsert("LaborAllocation", data);
    }

    public void updateTable(Date date) {
        int viewRow = table.getEditingRow();
        if(viewRow>-1){ //do we edited something?
            int k = table.convertRowIndexToModel(viewRow);
            String dbTable = "LaborUtilization";          
            
            //add data into variables
            int utilizationID = (int) model.getValueAt(k, 0);
            int allocationID = (int) model.getValueAt(k, 1);
            
            double hours = (double) model.getValueAt(k, 4);
            LaborStatus status = (LaborStatus) model.getValueAt(k, 5);
            //System.out.println(status);
            String notes = (String) model.getValueAt(k, 6);
            
            int StatusID = (status==null)?1:status.getID();
            
            if(utilizationID==0){
                // add new dataset
                int utilID = con.dbInsert(dbTable, new Object[][]{{"LaborAllocationID","PlantID","Hours","Status","Notes","DateFor","ApprovalID"},{allocationID,"",hours,StatusID,notes,date,"NULL"}});
                System.out.println("Utilization ID is "+utilID);
                model.setValueAt(utilID, k, 0);
            } else {
                // edit actual dataset
                if(con.getRowCount(con.dbSelect(dbTable, new Object[][] {
                    {"ID","Hours","Status","Notes"},
                    {"=","=","=","="},
                    {utilizationID,hours,StatusID,notes},
                    {"AND","AND","AND","AND"}
                }))==0){
                    System.out.printf("Hours: %s, Status: %s, Notes: %s, UtilID: %s\n",hours,StatusID,notes,utilizationID);
                    con.dbUpdate(dbTable, new Object[][]{{"Hours","Status","Notes"},{hours,StatusID,notes==null?"":notes}}, new Object[][]{{"ID","LaborAllocationID"},{"=","="},{utilizationID,allocationID},{"AND"}});             
                }
            }
        }
    }


    public void updateLabour(String originalName, String newName, int id, JTable table, Date date) {
        //run update
        con.dbUpdate("LaborList", new Object[][]{{"LaborName","LaborFunction"},{newName,id}}, new Object[][]{{"LaborName"},{"="},{originalName},{}});
        displayViewInTable(table, date);
    }

    private void deleteUtilForLabor(Integer delId, Date previousDay) {        
        con.dbDelete("LaborUtilization", new Object[][]{
            {"LaborAllocationID","DateFor"},
            {"=",">"},
            {delId,previousDay},
            {"AND"}
        }, "LaborAllocationID");
    }
    
    
    
}
