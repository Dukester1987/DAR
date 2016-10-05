/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ldulka
 */
public class dataCleaner {

    private final LocalWraper lw;

    public dataCleaner(LocalWraper lw) {
        this.lw = lw;    
        clean();
    }
    
    private void clean(){
        cleanPlants();        
    }
    
    private void cleanPlants(){
        String Query = "SELECT ID FROM PlantAllocation WHERE EndDate<StartDate";
        ResultSet rs = lw.runQuery(Query);
        try {
            while(rs.next()){
                String q1 = String.format("DELETE FROM PlantUtilization WHERE PlantAllocationID='%s'",rs.getInt("ID"));
                lw.executeQuery(q1, q1, false);
            }
            lw.executeQuery("DELETE FROM PlantAllocation WHERE EndDate<StartDate", Query, false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
    }
    
    
}
