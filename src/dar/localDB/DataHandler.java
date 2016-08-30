/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.TimeWrapper;
import dar.dbObjects.User;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public abstract class DataHandler {
    
    public LocalWraper con;
    public User user;
    //public ArrayList<Object> view;
    public JTable table;
    public TimeWrapper ti;
    public DefaultTableModel model;

    /**
     *
     * @param con
     * @param user
     * @param table
     */
    public DataHandler(LocalWraper con, User user,JTable table){
        this.con = con;
        this.user = user;
        this.table = table;
        this.ti = new TimeWrapper();
        this.model = (DefaultTableModel) table.getModel();
    } 
        
    /**
     *
     * @return
     */
 
    public abstract void displayViewInTable(JTable table, Date dateFor);


    void refreshTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }

}
