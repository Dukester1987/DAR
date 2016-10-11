/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

import dar.Functions.FileLogger;
import dar.Functions.downloadManager;
import dar.Functions.readXMLFile;
import dar.Gui.Gui;
import dar.localDB.LocalWraper;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DBWrapper implements Runnable{
    private static String USER;
    private static String PASSWORD;
    private static String CONN_STRING;
    private static String ACTUAL_SW_VERSION;
    private static String SERVER_ADDRESS;
    private static String CHANGELOG_PATH;
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
    private boolean checkForUpdates = true;

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
            System.out.println("Being inactive for "+i+"s");
            Thread.sleep(1000*i);            
            return true;
        } catch (InterruptedException ex) {
            //ex.printStackTrace();
            //new FileLogger(ex.toString()); 
            System.out.println("interupted from sleep");
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
        if(checkForUpdates){            
            try {
                initproperties();
                downloadManager dwnm = new downloadManager(SERVER_ADDRESS);
                if(dwnm.download(new URL(SERVER_ADDRESS+"/UPDATE/changelog/version_manifest.xml"))){
                    readXMLFile file = new readXMLFile();
                    file.readFile("changelog/version_manifest.xml"); 
                    if(!file.version.equals(ACTUAL_SW_VERSION)){
                        //start updating
                        int j = JOptionPane.showConfirmDialog(null, "There is a new version of Software available\nDo you want to update to version "+file.version+" now?", "new version "+file.version+"",JOptionPane.YES_NO_OPTION);
                        if(j==0){ // yes update
                            Runtime.getRuntime().exec("java -jar DAR_Updater.jar -runupdate");
                            System.out.println("updating");                            
                            System.exit(0);
                        }
                    }             
                } 
                checkForUpdates=false;
            } catch (IOException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }
        }
        startSync();        
        //keepAlive = false;
        keepAlive = putSleep(CHECKCONNECTION);
        label.setText("Checking connection");        
    }

    private static void initproperties() {
        FileInputStream input = null;
        try {
            input = new FileInputStream("./changelog/version_manifest.properties");        
            Properties props = new Properties();
            props.load(input);
            ACTUAL_SW_VERSION = props.getProperty("app.version");
            SERVER_ADDRESS = props.getProperty("app.address");
            CHANGELOG_PATH = props.getProperty("app.changelog");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
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
