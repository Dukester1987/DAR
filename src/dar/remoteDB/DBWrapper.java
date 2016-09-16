/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

import dar.Gui.Gui;
import dar.localDB.LocalWraper;
import java.awt.Color;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class DBWrapper implements Runnable{
    private static final String USER = "sopsioco_duke";
    private static final String PASSWORD = "chapadlo";
    private static final String CONN_STRING = "jdbc:mysql://192.185.128.23:3306/sopsioco_DAR?autoReconnect=true";
    public Connection con;
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
    private final LocalWraper db;
    private ChangeManager mgr;
    private Gui g;

    public DBWrapper(JLabel label, LocalWraper db, Gui g) {
        this.label = label;
        this.db = db;
        this.g = g;
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
       while(keepAlive){
           if(!isConnected()){
            try {
                con = connect(CONN_STRING,USER,PASSWORD);
                checkConnection(label);      
            } catch (SQLException ex) {
                tryReconnect(label);
            }   
           } else {
               checkConnection(label);
           }
       }
    }

    private Connection connect(String CONN_STRING, String USER, String PASSWORD) throws SQLException {
        return DriverManager.getConnection(CONN_STRING,USER,PASSWORD);
    }

    private boolean putSleep(long i) {
        try {
            Thread.sleep(i);
            return true;
        } catch (InterruptedException ex) {
            return false;
        }
    }

    private void tryReconnect(JLabel label) {
        if(Counter%6<5){
            label.setText("Connection Failed");
            label.setForeground(Color.RED);
            keepAlive = putSleep(2000);
            label.setText("Trying to Recconect");
        } else {
                label.setText("Connection failed for "+Counter+" times.\n next atempt in 60 seconds");
                keepAlive = putSleep(failThreadSleep);
                label.setText("Trying to Recconect");                   
        }
        Counter++;        
    }

    private void checkConnection(JLabel label) {
        label.setForeground(Color.BLACK);
        label.setText("Connection to the remote database established");
        startSync();
        keepAlive = putSleep(15000);
        label.setText("Checking connection");        
    }

    private void startSync() {
        mgr = new ChangeManager(db, this);
        System.out.printf("rows to download: %s\nrows to upload: %s\n", mgr.getAmountOfChanges(0),mgr.getAmountOfChanges(1));
        mgr.runSync(0,label); //see what do we need to download
        mgr.runSync(1,label);
        g.refreshLists();
    }
    
    
}
