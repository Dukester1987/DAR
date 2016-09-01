/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.Functions;
import dar.dbObjects.AFuelView;
import dar.dbObjects.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class AFViewDataHandler extends DataHandler{
    private ArrayList<AFuelView> view;
    public Date dateFor;
   
    public AFViewDataHandler(LocalWraper con, User user, JTable table) {
        super(con, user, table);
        hideID(table);
    }


    
    public ArrayList<AFuelView> getView() {
        if(user.getSiteID()!=0){
            view = new ArrayList<AFuelView>();
            String query = String.format("SELECT\n" +
                            "AFAllocation.ID as ID,\n" +
                            "AFFuel.ID as UtilID,\n" +
                            "AFAllocation.SiteID,\n" +
                            "AFAllocation.PlantNo,\n" +
                            "AFAllocation.Rego,\n" +
                            "AFAllocation.Description,\n" +
                            "AFFuel.Amount\n" +
                            "FROM `AFAllocation`\n" +
                            "LEFT JOIN AFFuel ON AFAllocation.ID = AFFuel.AFAllocationID AND AFFuel.DateFor = '%s'\n" +
                            "WHERE StartDate <= '%s' AND EndDate >= '%s' and SiteID = %s",dateFor, dateFor, dateFor, user.getSiteID());
            ResultSet rs = con.runQuery(query);
            try {
                while(rs.next()){
                    AFuelView nw = new AFuelView(rs.getInt("ID"),rs.getInt("UtilID"), rs.getInt("SiteID"), rs.getString("PlantNo"), rs.getString("Rego"),rs.getString("Description"), rs.getDouble("Amount"));
                    view.add(nw);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }            
        }
        return view;
    }    

    @Override
    public void displayViewInTable(JTable table, Date dateFor) {
        this.dateFor = dateFor;
        ArrayList<AFuelView> list = getView(); 
        refreshTable(model);
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
                list.get(i).getAllocationID(),
                list.get(i).getUtilID(),
                list.get(i).getPlantNo(),
                list.get(i).getRego(),
                list.get(i).getDescription(),
                list.get(i).getAmount()            
            });                    
        }
    }
    
    public void addAditionalFuel(JTextField fUnitNo,JTextField fRego,JTextField fDesc, JTextField fAmount, Date date) {

        String funo = Functions.forHTML(fUnitNo.getText());
        String frego = Functions.forHTML(fRego.getText());
        String fdesc = Functions.forHTML(fDesc.getText());
        try{
            double famount = Double.parseDouble(fAmount.getText());
            if((funo.isEmpty() && frego.isEmpty()) || (fdesc.isEmpty()) ){
                JOptionPane.showMessageDialog(null, "You have to fill all fields");
            } else {
                Object[][] query = {{"SiteID","PlantNo","Rego","StartDate","EndDate"},{"=","=","=","<=",">="},{con.userData.getSiteID(),funo,frego,date,date},{"AND","AND","AND","AND"}};                    
                if(con.hasDuplicity(con.dbSelect("AFAllocation", query))){
                    JOptionPane.showMessageDialog(null, "Already in list");                   
                } else {
                    //add
                    Object[][] dataset = {{"SiteID","PlantNo","Rego","Description","StartDate","EndDate"},{con.userData.getSiteID(),funo,frego,fdesc,date,ti.nextDate()}};
                    int allocID = con.dbInsert("AFAllocation", dataset);
                    Object[][] dataset2 = {{"AFAllocationID","Amount","DateFor"},{allocID,famount,date}};
                    con.dbInsert("AFFuel", dataset2);
                    displayViewInTable(table, date);
                    fAmount.setText("");
                    fDesc.setText("");
                    fRego.setText("");
                    fUnitNo.setText("");
                }
            }            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Amount must be numeric value");
        }
        

    }  
    
    public void removeSelected(Date date) {
        int[] rows = table.getSelectedRows();
        if(table.getSelectedRowCount()>0){
            for (int row : rows) {           
                int alocID =  (int) model.getValueAt(row, 0);
                int utilID =  (int) model.getValueAt(row, 1);

                Object[][] what = {{"amount"},{"0"}};
                Object[][] where = {{"ID"},{"="},{utilID},{}};                                       
                con.dbUpdate("AFFuel", what, where);

                Object[][] w = {{"EndDate"},{ti.yesterday()}};
                Object[][] wh = {{"ID"},{"="},{alocID},{}};
                con.dbUpdate("AFAllocation", w, wh);                               
            }
            displayViewInTable(table, date);            
        } else {
            JOptionPane.showMessageDialog(null,"No rows selected!");
        }

    }    
    
    private void hideID(JTable table) { 
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("allocationID"));
        table.removeColumn(table.getColumn("utilID"));        
    }    

    public void editAditionalFuel(Date date) {
        if(table.getEditingRow()>=0){
            int utilID = (int) model.getValueAt(table.getEditingRow(), 1);
            int allID = (int) model.getValueAt(table.getEditingRow(), 0);
            double fuelAmount = (double) model.getValueAt(table.getEditingRow(), 5);            

            if(utilID!=0){ // update
                Object[][] what = {{"amount"},{fuelAmount}};
                Object[][] where = {{"ID"},{"="},{utilID},{}};                                       
                con.dbUpdate("AFFuel", what, where);
            } else { // insert
                Object[][] data = {{"AFAllocationID","Amount","DateFor"},{allID,fuelAmount,date}};
                con.dbInsert("AFFuel", data);
            }
            displayViewInTable(table, dateFor);             
        }
    }


}