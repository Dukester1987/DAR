/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DBWrapper implements Runnable{
    private static final String USER = "sopsioco_duke";
    private static final String PASSWORD = "chapadlo";
    private static final String CONN_STRING = "jdbc:mysql://192.185.128.23:3306/sopsioco_DAR?autoReconnect=true";
    Connection con;
    Thread thread;
    private boolean keepAlive = true;
    private JLabel label;
    private boolean validator;
    private int Counter = 0;
    private long failThreadSleep = 60000;
    private boolean firstOpen = true;
    private JTable table;
    private JButton addPlant;
    private JButton removePlant;
    private JButton refreshb;

    public DBWrapper(JLabel label) {
        this.label = label;

    }


    
    public boolean isPlantDescription(String PlantNo){
        String query = "SELECT * FROM `PlantList` WHERE PlantNo='"+PlantNo+"';";
        Statement st;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            return rs.last();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    public String getPlantDescrition(String PlantNo){
        String query = "SELECT * FROM `PlantList` WHERE PlantNo='"+PlantNo+"';";
        Statement st;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            return rs.getString("PlantDesc");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }        
    }
    
    public ArrayList<PlantUtilization> getPlantUtilization(){
        
        ArrayList<PlantUtilization> plantUtilization = new ArrayList<PlantUtilization>();
        String query = "SELECT * FROM `PlantUtilization`";
        Statement st;
        ResultSet rs;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            PlantUtilization plantUtil;
            while(rs.next()){
                plantUtil = new PlantUtilization(rs.getInt("ID"),rs.getString("PlantNo"),rs.getString("PlantDesc"),rs.getInt("StartHours"),rs.getInt("EndHours"),rs.getDouble("Fuel"),rs.getString("Notes"));
                plantUtilization.add(plantUtil);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return plantUtilization;
    }
    
    public void displayPlantUtilizationInTable(JTable table){
        ArrayList<PlantUtilization> list = getPlantUtilization();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        refreshTable(model);
        for(int i = 0;i<list.size();i++){
            model.addRow(new Object[]{list.get(i).getID(),list.get(i).getPlantNo(),list.get(i).getPlantDesc(),list.get(i).getStartHours(),list.get(i).getEndHours(),list.get(i).getFuel(),list.get(i).getNotes()});
        }
    }

    private void refreshTable(DefaultTableModel model) {
        int rowNum = model.getRowCount();
        for(int i = rowNum-1;i>=0;i--){
            model.removeRow(i);
        }
    }
    
    public void executeQuery(String query, String message, boolean displaymsg){
        Statement st;
        try {
            st = con.createStatement();
            if(displaymsg){
                if(st.executeUpdate(query) == 1){
                    JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                } else if(st.executeUpdate(query) == 0 && message.equals("deleted")) {
                    JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Data not "+message);
                }
            } else {
                st.executeUpdate(query);
            }
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    public boolean isConnected(){
        try {
            validator = con.isValid(100);
        } catch (Exception ex) {            
            validator = false;
        }
        return validator;
    }    
    
    public void run() {
        System.out.println("dar.DBWrapper.run()");
       while(keepAlive){
           label.setForeground(Color.BLACK);
           if(!isConnected()){
            try {
                con = DriverManager.getConnection(CONN_STRING,USER,PASSWORD);
                System.out.println("Connected to the remote database!");
                label.setText("Connection to the remote database established");
                try {
                    Thread.sleep(15000);
                    label.setText("Checking connection");
                } catch (InterruptedException ex) {
                    keepAlive = false;
                }         
            } catch (SQLException ex) {
                if(Counter%6<5){
                    label.setText("Connection Failed"+Counter%6);
                    label.setForeground(Color.RED);
                    try {
                        Thread.sleep(2000);
                        label.setText("Trying to Recconect");
                    } catch (InterruptedException ex1) {
                        keepAlive = false;
                    }
                } else {
                    try {
                        label.setText("Connection failed for "+Counter+" times.\n next atempt in 60 seconds");
                        Thread.sleep(failThreadSleep);
                        label.setText("Trying to Recconect");
                    } catch (InterruptedException ex1) {
                        keepAlive = false;
                    }                    
                }
                Counter++;
            }   
           } else {
               try {
                   label.setText("Connection to the remote database established");
                   System.out.println("done");
                   Thread.sleep(15000);
               } catch (InterruptedException ex) {
                   keepAlive = false;
               }
           }
       }
    }
    
    
}
