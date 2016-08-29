/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.dbObjects.AFuelView;
import dar.dbObjects.User;
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
        DefaultTableModel model = (DefaultTableModel) table.getModel();
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
    
    private void hideID(JTable table) { 
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
        table.removeColumn(table.getColumn("allocationID"));
        table.removeColumn(table.getColumn("utilID"));        
    }    

}
