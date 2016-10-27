/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JOptionPane;

/**
 *
 * @author ldulka
 */
public class DBFunctions {

    private final Connection con;

    public DBFunctions(Connection con) {
        this.con = con;
    }
    
    public int executeQuery(String query){
        Statement st;
        int lastID = 0;
        try {
            st = con.createStatement();  
            //System.out.println(query);
            int result = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);            
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()){
                lastID = rs.getInt(1);
                //System.out.println("ID changed: "+lastID);
            }
                
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return lastID;
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
        executeQuery(query);
//        changeLog(recipeRel, updatedID, "delete", query, userData.getId());                
        //System.out.println(query);
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
            //System.out.println(query);
            ex.printStackTrace();
            new FileLogger(ex.toString());
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
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        } finally {
            try {
                resultSet.beforeFirst();
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
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
        //System.out.println(query);
        int updatedID = executeQuery(query);
//        changeLog(table,updatedID,"insert",columns+" VALUES = "+inputs,userData.getId());
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
        //System.out.println(query);
        executeQuery(query);
//        changeLog(table, updatedID, "update", whatToUpdate, userData.getId());
    }  
    
    public void changeLog(String tbl, String ID, String insert, String inputs, int loginId, Timestamp time, String UID) {
        try {
            Functions fn = new Functions();
            String fixInputs = fn.forHTML(inputs);
            //Timestamp time = new Timestamp(System.currentTimeMillis());
            String query = String.format("INSERT INTO ChangeLog (AffectedTable, RowID, Operation, NewValue, LoginID, Time, UID) VALUES ('%s','%s','%s','%s','%s','%s','%s')",tbl,ID,insert,fixInputs,loginId,time,UID);
            Statement st;

            //System.out.println(query);
            st = con.createStatement();            
            st.executeUpdate(query);            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
    }    

    
}
