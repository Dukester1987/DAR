/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.Functions;
import dar.dbObjects.User;
import dar.hash.hash;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
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
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_LOGIN = "Dukester";
    private static final String DB_PASS = "Chapadlo";
    private static final String DB_CONN_STRING = "jdbc:h2:~DAR";
//    private static final String DB_LOGIN = "sopsioco_duke";
//    private static final String DB_PASS = "chapadlo";
//    private static final String DB_CONN_STRING = "jdbc:mysql://192.185.128.23:3306/sopsioco_DAR?autoReconnect=true";    
    
    private String loginname;
    private String password;
    private JLabel output;
    public Connection con;
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
   
    
    public int executeQuery(String query, String message, boolean displaymsg){
        Statement st;
        int lastID = 0;
        try {
            st = con.createStatement();  
            int result = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            if(displaymsg){
                if(result == 1){
                    JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                    
                } else if(result == 0 && message.equals("deleted")) {
                    JOptionPane.showMessageDialog(null, "Data "+message+" Succesfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Data not "+message);
                }
            }
            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                lastID = rs.getInt(1);
                System.out.println("ID changed: "+lastID);
            }
                
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lastID;
    }
    
    private void getConnection(){
        try {
            Class.forName(DB_DRIVER);
            con = DriverManager.getConnection(DB_CONN_STRING,DB_LOGIN,DB_PASS);
            System.out.println("connected");
        } catch (SQLException ex) {
            if(ex.getErrorCode() == 90020){
                JOptionPane.showMessageDialog(null,"Application is already in use!\nPlease restart application and try it again.", "Already in use",JOptionPane.ERROR_MESSAGE);
            } else {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
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
    
    public void refreshUserData(int ID){
        String query = String.format("SELECT * FROM Login WHERE ID = %s",ID);   
        ResultSet rs = runQuery(query);
        try {
            if(rs.first()){
                userData = new User(rs.getInt("ID"), rs.getString("LoginName"), rs.getString("Password"), rs.getString("Rights"), rs.getInt("Status"), rs.getTimestamp("LastUpload"), rs.getTimestamp("LastDownload"));
            } else {                      
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
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
        //System.out.println(query);
        return runQuery(query);          
    }
    
    public ResultSet dbSelect(String table){
        String query = "SELECT * FROM " +table;
        return runQuery(query);
    }

    public boolean hasDuplicity(ResultSet rs) {
        boolean result = false;
            if(getRowCount(rs)>0){
                result = true;
            } else {
                result = false;
            }
        return result;                
    }
    

    public ResultSet runQuery(String query) {
        ResultSet rs = null;
        try {         
            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("SQL ERROR runQuery");
        }
        return rs;        
    }
    
    public int getRowCount(ResultSet resultSet) {
        if (resultSet == null) {
            return 0;
        }
        try {
            resultSet.last();
            return resultSet.getRow();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException exp) {
                exp.printStackTrace();
            }
        }
        return 0;
    }
    
    public int dbInsert(String table, Object[][] dataset) {
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
        int updatedID = executeQuery(query, "inserted", false);
        query = String.format("INSERT INTO %s (ID, %s) VALUES (%s, %s)", table,columns,updatedID,inputs);
        changeLog(table,updatedID,"insert",query,userData.getId());
        return updatedID;
    }

    private static Object isSurrounded(Object object) {
        String returnComa;
        Object result;
        if(object != null){
            //System.out.println(object.getClass().getName());
            String objName = object.getClass().getName();            
            if(object.equals("NULL")){
                returnComa = "";
            } else {
                if(objName.endsWith("Double")){
                    returnComa = "";
                } else if(objName.endsWith("Integer")){
                    returnComa = "";
                } else if(objName.endsWith("String")){
                    returnComa = "'";
                } else if(objName.endsWith("Long")){
                    returnComa = "";      
                } else if(objName.endsWith("Boolean")){
                    returnComa = "";                        
                } else {
                    returnComa = "'";
                }
            }
            result = returnComa+object+returnComa;
        } else {
            result = "''";
        }          

        return result;
    }

    public void dbUpdate(String table, Object[][] what, Object[][] where) {
        String whatToUpdate = "";
        String conditions = "";
        
        int updatedID = 0;
        for (int i = 0; i < what[0].length; i++) {
            Object coma = "";
            String wh = (String) what[0][i];
            if(what[1][i].toString().startsWith("--")){
                coma = what[1][i].toString().substring(2);
            } else {
                coma = isSurrounded(what[1][i]);
            }
            whatToUpdate += what[0][i]+" = " +coma+ " ";
                if(i<what[0].length-1){
                    whatToUpdate += ", ";
                }
        }
        Object[] question = where[0];
        Object[] operand = where[1];
        Object[] answer = where[2];
        Object[] delimiter = where[3];
        for (int i = 0; i < question.length; i++) {
            Object coma = isSurrounded(answer[i]);
            conditions += question[i] + " " + operand[i] + " " + coma + " ";  
            if(i<question.length-1){
                conditions += delimiter[i] + " ";
            }    
            if(question[i].equals("ID")){
                updatedID = (int) answer[i];
            }            
        }
        String query = String.format("UPDATE %s SET %s WHERE %s", table, whatToUpdate, conditions);
        System.out.println(query);
        executeQuery(query, "updated", false);
        if(updatedID != 0){
            changeLog(table, updatedID, "update", query, userData.getId());
        }
            
    }

    private void changeLog(String tbl, int ID, String insert, String inputs, int loginId) {
        try {
            Functions fn = new Functions();
            String fixInputs = fn.forHTML(inputs);
            UUID uuid = UUID.randomUUID();
            String query = String.format("INSERT INTO ChangeLog (AffectedTable, RowID, Operation, NewValue, LoginID, UID) VALUES ('%s','%s','%s','%s','%s','%s')",tbl,ID,insert,fixInputs,loginId,uuid);
            Statement st;

            System.out.println(query);
            st = con.createStatement();            
            st.executeUpdate(query);            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dbDelete(String recipeRel, Object[][] where, String LogID) {
        String conditions = "";
        int updatedID = 0;
        Object[] question = where[0];
        Object[] operand = where[1];
        Object[] answer = where[2];
        Object[] delimiter = where[3];
        for (int i = 0; i < question.length; i++) {
            Object coma = isSurrounded(answer[i]);
            conditions += question[i] + " " + operand[i] + " " + coma + " ";  
            if(i<question.length-1){
                conditions += delimiter[i] + " ";
            }    
            if(question[i].equals(LogID)){
                updatedID = (int) answer[i];
            }            
        }

        String query = String.format("DELETE FROM %s WHERE %s", recipeRel,conditions);
        executeQuery(query, "Deleted", false);
        changeLog(recipeRel, updatedID, "delete", query, userData.getId());                
        System.out.println(query);
    }
    
}
