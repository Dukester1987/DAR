/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.dbObjects.User;
import dar.hash.hash;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author ldulka
 */
public class LocalWraper {
    //During dev DATABASE temporary moved to remote one
    //private static final String DB_DRIVER = "org.h2.Driver";
//    private static final String DB_LOGIN = "Dukester";
//    private static final String DB_PASS = "Chapadlo";
//    private static final String DB_CONN_STRING = "jdbc:h2:~DAR";
    private static final String DB_LOGIN = "sopsioco_duke";
    private static final String DB_PASS = "chapadlo";
    private static final String DB_CONN_STRING = "jdbc:mysql://192.185.128.23:3306/sopsioco_DAR?autoReconnect=true";    
    
    private String loginname;
    private String password;
    private JLabel output;
    private Connection con;
    private JLabel label;
    private JTable table;
    private JButton addPlant;
    private JButton removePlant;
    private JButton refreshb;
    private String MySiteID;
    
    public User userData;
    
    public LocalWraper() {
      getConnection();
    }

    public LocalWraper(JTable table) {
        getConnection();
        this.table = table;

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
    
    private void getConnection(){
        try {
//          Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_CONN_STRING,DB_LOGIN,DB_PASS);
            System.out.println("connected");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
        
    }
    
    
    
    public boolean login(String login, String pass, JLabel output){
        this.loginname = login;
        this.password = pass;
        this.output = output;
        boolean result = false;
        
        if(loginname.isEmpty() || password.isEmpty()){
            result = false;
        } else {
            hash h = new hash();
            String passMD5 = h.md5(password);
            String query = "SELECT * FROM Login WHERE LoginName = '"+loginname+"' AND Password = '"+passMD5+"'";            
            ResultSet rs = runQuery(query);
            try {
                if(rs.first()){
                    userData = new User(rs.getInt("ID"), rs.getString("LoginName"), rs.getString("Password"), rs.getString("Rights"), rs.getInt("Status"), rs.getTimestamp("LastUpload"), rs.getTimestamp("LastDownload"));
                    result = true;
                } else {
                    result = false;                        
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public void closeConnection() {
        try { 
            con.close();
            System.out.println("Connection closed");
        } catch (SQLException ex) {
            Logger.getLogger(LocalWraper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet dbSelect(Object[] what, String table, Object[][] where){
        String query = "SELECT ";
        for (int i = 0; i < what.length; i++) {
            String wh = (String) what[i];
            query += wh+" ";
                if(i<what.length-1){
                    query += ", ";
                }
        }
        query += "FROM " +table+ " WHERE ";
        Object[] question = where[0];
        Object[] operand = where[1];
        Object[] answer = where[2];
        Object[] delimiter = where[3];
        for (int i = 0; i < question.length; i++) {
            query += question[i] + " " + operand[i] + " '" + answer[i] + "' ";  
            
            if(i<question.length-1){
                query += delimiter[i] + " ";
            }            
        }        
        
        return runQuery(query);       
    }
    
    public ResultSet dbSelect(String table, Object[][] where){
        String query = "SELECT * FROM " +table+ " WHERE ";
        Object[] question = where[0];
        Object[] operand = where[1];
        Object[] answer = where[2];
        Object[] delimiter = where[3];
        for (int i = 0; i < question.length; i++) {
            Object objComa = isSurrounded(answer[i]);
            query += question[i] + " " + operand[i] + " "+ objComa +" ";  
            
            if(i<question.length-1){
                query += delimiter[i] + " ";
            }            
        }        
        System.out.println(query);
        return runQuery(query);          
    }
    
    public ResultSet dbSelect(String table){
        String query = "SELECT * FROM " +table;
        return runQuery(query);
    }

    public boolean hasDuplicity(ResultSet rs) {
        boolean result = false;
            if(numRows(rs)>0){
                result = true;
            } else {
                result = false;
            }
        return result;                
    }
    

    public ResultSet runQuery(String query) {
        ResultSet rs = null;
        try { 
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("SQL ERROR runQuery");
        }
        return rs;        
    }

    private int numRows(ResultSet rs) {
        int num = 0;
        
        try {
            while(rs.next()){
                num++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalWraper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;                              
    }

    public void dbInsert(String table, Object[][] dataset) {
        Object[] names = dataset[0];
        Object[] values = dataset[1];
        
        String columns="";
        String inputs="";
        
        for (int i = 0; i < names.length; i++) {
            Object name = names[i];
            columns += "`"+name+"`";
            if(i<names.length-1)
                columns += ", ";                      
        }
        
        for (int i = 0; i < values.length; i++) {
            Object value = values[i];
            Object coma = isSurrounded(value);
            inputs += coma;
            if(i<values.length-1)
                inputs += ", ";
            
        }

        String query = String.format("INSERT INTO %s (%s) VALUES (%s)",table,columns,inputs);
        System.out.println(query);
        executeQuery(query, "inserted", false);
    }

    private Object isSurrounded(Object object) {
        String returnComa;
        Object result;
        //System.out.println(object.getClass().getName());
        if(object != null){
            if(object.equals("NULL")){
                returnComa = "";
            } else {
                returnComa = "'";
            }
            result = returnComa+object+returnComa;
        } else {
            result = "''";
        }          

        return result;
    }

    public void dbUpdate(String table, Object[][] what, Object[][] where) {
        String query = "UPDATE "+table+" SET ";
        for (int i = 0; i < what[0].length; i++) {
            String wh = (String) what[0][i];
            query += what[0][i]+" = '" +what[1][i]+ "' ";
                if(i<what[0].length-1){
                    query += ", ";
                }
        }
        query += "WHERE ";
        Object[] question = where[0];
        Object[] operand = where[1];
        Object[] answer = where[2];
        Object[] delimiter = where[3];
        for (int i = 0; i < question.length; i++) {
            Object coma = isSurrounded(answer[i]);
            query += question[i] + " " + operand[i] + " " + coma + " ";  
            
            if(i<question.length-1){
                query += delimiter[i] + " ";
            }    
        }
        System.out.println(query);
        executeQuery(query, "updated", false);
    }
    
}
