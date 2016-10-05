/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

import dar.Functions.FileLogger;
import dar.Gui.Gui;
import dar.localDB.LocalWraper;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class DBWrapper implements Runnable{
    private static String USER;
    private static String PASSWORD;
    private static String CONN_STRING;
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
    private long FAIL_RECONNECT;
    private long FAIL_TOTALRECONNECT;
    private long CHECKCONNECTION;

    public DBWrapper(JLabel label, LocalWraper db, Gui g) {
        this.label = label;
        this.db = db;
        this.g = g;
        initProperties();
    }
    
    public boolean isConnected(){
        try {
            validator = con.isValid(100);
        } catch (Exception ex) {  
            //new FileLogger(ex.toString());
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
                ex.printStackTrace();
                new FileLogger(ex.toString());
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
            System.out.println("sleeping for "+i);
            return true;
        } catch (InterruptedException ex) {
            //ex.printStackTrace();
            //new FileLogger(ex.toString()); 
            System.out.println("im unterupted now");
            return false;
        }
    }

    private void tryReconnect(JLabel label) {
        if(Counter%6<5){
            label.setText("Connection Failed");
            label.setForeground(Color.RED);
            keepAlive = putSleep(FAIL_RECONNECT);
            label.setText("Trying to Recconect");
        } else {
                label.setText("Connection failed for "+Counter+" times.\n next atempt in 60 seconds");
                keepAlive = putSleep(FAIL_TOTALRECONNECT);
                //keepAlive = false;
                label.setText("Trying to Recconect");                  
        }
        Counter++;        
    }

    private void checkConnection(JLabel label) {
        label.setForeground(Color.BLACK);
        label.setText("Connection to the remote database established");
        System.out.println("start sync");
        startSync();        
        //keepAlive = false;
        keepAlive = putSleep(CHECKCONNECTION);
        label.setText("Checking connection");        
    }

    private void startSync() {
        label.setText("getting list of changes");
        mgr = new ChangeManager(db, this);       
        mgr.runSync(0,label); //see what do we need to download
        mgr.runSync(1,label);
        g.refreshLists();            
        System.out.println("refreshing lists");
//        if(mgr.getTotalChanges()>0){
//            label.setText(String.format("There are changes to exchange!"));            
//            g.saveButton.setEnabled(true);
//        }

    }    

    private void initProperties() {
        try {
            FileInputStream input = new FileInputStream("./inc/dbcon.properties");
            Properties props = new Properties();
            props.load(input);
            CONN_STRING = props.getProperty("jdbc.url");
            USER = props.getProperty("jdbc.username");
            PASSWORD = props.getProperty("jdbc.password");
            FAIL_RECONNECT = new Long(props.getProperty("con.failreconnect")).longValue();
            FAIL_TOTALRECONNECT = new Long(props.getProperty("con.totalfailreconnect")).longValue();
            CHECKCONNECTION = new Long(props.getProperty("con.checkconnection")).longValue();
        } catch (FileNotFoundException ex) {
            new FileLogger(ex.toString());
            ex.printStackTrace();
        } catch (IOException ex) {
            new FileLogger(ex.toString());
            ex.printStackTrace();
        }
    }
    
}
