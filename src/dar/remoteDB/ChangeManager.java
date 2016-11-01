/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

import dar.Functions.DBFunctions;
import dar.Functions.FileLogger;
import dar.Functions.TimeWrapper;
import dar.dbObjects.ChangeLogView;
import dar.localDB.LocalWraper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JLabel;

/**
 *
 * @author ldulka
 */
public class ChangeManager {
    
    private LocalWraper db;
    private TimeWrapper date;
    private DBFunctions ServerCon;
    private final DBFunctions LocalCon;
    private ArrayList<ChangeLogView> changeList;
    private int[] updateID = {0,0};
    private HashMap<Integer, Timestamp> lastUpdate;

    public ChangeManager(LocalWraper db, DBWrapper server) {
        this.db = db;
        this.date = new TimeWrapper();
        this.ServerCon = new DBFunctions(server.con);
        this.LocalCon = new DBFunctions(db.con);
        this.lastUpdate = new HashMap<>();        
        getListOfChanges();
    }
    
    private ArrayList<ChangeLogView> getListOfChanges(){
        //initialize the variables
        changeList = new ArrayList<>();
        getLastUpdate();
        String SQLString = "SELECT ID, AffectedTable, RowID, Operation, NewValue, LoginID, Time, UID FROM ChangeLog WHERE Time>'%s' ORDER BY Time";
        
        //get things to download        
        String query = String.format(SQLString,lastUpdate.get(updateID[0]));                
        addResultIntoList(ServerCon,changeList,query,0);
        
        //get things to upload
        query = String.format(SQLString,lastUpdate.get(updateID[1]));        
        addResultIntoList(LocalCon,changeList, query, 1);
                
        return changeList;
    }
    
    public int getAmountOfChanges(int type){
        int counter = 0;         
        removeDuplicities(type);        
        for (int i = changeList.size()-1;i>-1;i--) {            
            counter += (changeList.get(i).getType()==type)?1:0;
        }
        return counter;
    }
    
    public int getTotalChanges(){
        return getAmountOfChanges(0)+getAmountOfChanges(1);
    }
    
    public String getResultText(){
        String returntext = "";
        for (ChangeLogView changeLogView : changeList) {
            returntext += changeLogView.toString()+"ya";
        }
        return returntext;
    }

    private void addResultIntoList(DBFunctions conFct,ArrayList<ChangeLogView> changeList, String query, int i) {
        ResultSet rs = conFct.runQuery(query);        
        try {
            while (rs.next()) {
                ChangeLogView lw = new ChangeLogView(
                        rs.getInt("ID"), 
                        rs.getString("AffectedTable"), 
                        rs.getString("RowID"), 
                        rs.getString("Operation"), 
                        rs.getString("NewValue"), 
                        rs.getInt("LoginID"), 
                        rs.getTimestamp("Time"),
                        rs.getString("UID"),
                        i);
                changeList.add(lw);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }        
    }

    public void runSync(int type, JLabel label) {   
        DBFunctions destination = getConType(type);     
        String userColumn = getUserColumn(type);
        String operation = type==0?"Downloading":"Uploading";       
        int s = getAmountOfChanges(type);         
        System.out.println("start "+ operation + " with "+s+" changes");
        if(s>0){ //check if there is anything to download / upload
            updateUserInfo(userColumn); 
            int updateLog = createNewUpdate(type,changeList.get(changeList.size()-1).getTimeChanged());
            System.out.println("creating new update record for type: "+operation);
            int counter = 0;
            if(type == 1){
                destination.executeQuery("START TRANSACTION;");
            }
            for (ChangeLogView clw : changeList) {
                if(clw.getType()==type){
                    counter++;
                    switch(clw.getOperation()){
                        case "insert":
                            insertOperation(destination,clw);
                            break;
                        case "update":
                            updateOperation(destination,clw);
                            break;
                        case "delete":
                            deleteOperation(destination,clw);                            
                            break;
                    }
                } 
                label.setText(String.format("%s changes: %s / %s", operation,counter,s));
            }
            if(type == 1){
                destination.executeQuery("COMMIT;");
            }            
            updateFinished(updateLog);
        }          
        label.setText("All changes up to date"); 
        System.out.println(operation+" finished");
    }

    private void insertOperation(DBFunctions destination, ChangeLogView clw) {
        if(!isExistInDestination(destination,clw)){
            destination.executeQuery(clw.getSQLString());
            logOnDestination(destination,clw);
        } else {
            destination.executeQuery(transInsertToUpdate(clw.getSQLString(), clw.getAffectedTable(), clw.getRowID()));
            logOnDestination(destination, clw);
        }
    }

    private void updateOperation(DBFunctions destination, ChangeLogView clw) {
        if(!isExistInDestination(destination, clw)){
            destination.executeQuery(transUpdateToInsert(clw.getSQLString(), clw.getAffectedTable()));
            logOnDestination(destination, clw);
        } else {
            destination.executeQuery(clw.getSQLString());
            logOnDestination(destination, clw);
        }
    }

    private void deleteOperation(DBFunctions destination, ChangeLogView clw) {
        if(!isExistInDestination(destination, clw)){
            System.out.println("We are good");            
        } else {
            destination.executeQuery(clw.getSQLString());
            logOnDestination(destination, clw);
        }
    }

    private boolean isExistInDestination(DBFunctions destination, ChangeLogView clw) {
        String query = String.format("SELECT * FROM %s WHERE ID = '%s'", clw.getAffectedTable(),clw.getRowID());
        return destination.getRowCount(destination.runQuery(query))>0;
    }

    private void logOnDestination(DBFunctions destination, ChangeLogView clw) {
        destination.changeLog(clw.getAffectedTable(), clw.getRowID(), clw.getOperation(), clw.getSQLString(), clw.getLoginID(),clw.getTimeChanged(),clw.getUid());
    }

    private void updateUserInfo(String userColumn) {
        Timestamp t = new Timestamp(System.currentTimeMillis());
        String query = String.format("UPDATE Login SET %s = '%s' WHERE ID = %s",
                userColumn,
                t,
                db.userData.getId()
                );
        LocalCon.executeQuery(query);
        ServerCon.executeQuery(query);
        db.refreshUserData(db.userData.getId());
    }

    private static String transInsertToUpdate(String query,String table,String ID) {
        String mainSeparator = ") VALUES (";
        String[] what = query.substring(query.indexOf("(")+1,query.indexOf(") VALUES (")).split(",");
        String[] values = query.substring(query.indexOf(mainSeparator)+mainSeparator.length(),query.lastIndexOf(")")).split(",");
        
        String result = "";
        for (int i = 0; i < what.length; i++) {
            result += String.format("%s = %s%s", what[i].trim(),values[i].trim(),i<what.length-1?",":"");            
        }
        return String.format("UPDATE %s SET %s WHERE ID = %s", table,result,ID);
    }
    
    private static String transUpdateToInsert(String query,String table){
        String columns = "";
        String values = "";
        String firstParse = " SET ";
        String[] splices = query.substring(query.indexOf(firstParse)+firstParse.length(),query.lastIndexOf(" WHERE ")).split(",");
        for (int i = 0; i < splices.length; i++) {
            columns += String.format("%s%s", splices[i].substring(0,splices[i].indexOf(" = ")).trim(),i <splices.length-1?", ":"");
            values += String.format("%s%s",splices[i].substring(splices[i].indexOf(" = ")+" = ".length()).trim(),i <splices.length-1?", ":"");            
        }        
        return String.format("INSERT INTO %s (%s) VALUES (%s)", table,columns,values);
    }    

    private void removeDuplicities(int type) {
        DBFunctions destination = getConType(type);   
        String q = String.format("SELECT UID FROM ChangeLog WHERE Time>='%s'", lastUpdate.get(updateID[type]));
        ArrayList<String> UIDs = new ArrayList<String>();
        ResultSet rs = destination.runQuery(q);
        try {
            while(rs.next()){
                UIDs.add(rs.getString("UID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        for (int i = changeList.size()-1; i > -1; i--) {
            if(changeList.get(i).getType()==type){
                if(UIDs.size()>0){
                    for (String UID : UIDs) {                        
                        String checkedID = changeList.get(i).getUid();
                        if(UID.equals(checkedID)){                            
                            changeList.remove(i);   
                            break;
                        }
                    }
                }
//                if(destination.getRowCount(destination.runQuery(String.format("SELECT UID FROM ChangeLog WHERE UID = '%s'",changeList.get(i).getUid())))>0){
//                    System.out.println("removing "+ changeList.get(i).getUid());
//                    changeList.remove(i);
//                }
            } 
        }      
    }

    private DBFunctions getConType(int type) {
        switch(type){
            case 0:
                return LocalCon;
            case 1:
                return ServerCon;
            default: 
                return LocalCon;
        }
    }

    private String getUserColumn(int type) {
        switch(type){
            case 0:
                return "LastDownload";
            case 1:
                return "LastUpload";
            default: 
                return "LastDownload";
        }        
    }

    private void getLastUpdate() {
        for (int type = 0; type<updateID.length;type++) {
            if(updateID[type]==0){
                String query = String.format("SELECT ID, Start FROM UpdateLog where type =%s and (Start is not NULL or End is not null) order by ID desc LIMIT 0,1", type);
                ResultSet rs = LocalCon.runQuery(query);
                if(LocalCon.getRowCount(rs)>0){
                    try {
                        rs.next();
                        lastUpdate.put(rs.getInt("ID"), rs.getTimestamp("Start"));
                        //System.out.printf("Last update for type %s having ID: %s and Timestamp: %s\n",type,rs.getInt("ID"), rs.getTimestamp("Start"));
                        updateID[type] = rs.getInt("ID");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        new FileLogger(ex.toString());
                    }
                } else {
                        lastUpdate.put(0, new Timestamp(1L));                        
                }                    
            }            
        }
    }

    private int createNewUpdate(int type,Timestamp time) {
        return LocalCon.dbInsert("UpdateLog", new Object[][]{
            {"Start","Type"},
            {time,type}
        });
    }

    private void updateFinished(int updateLog) {
       LocalCon.dbUpdate("UpdateLog", new Object[][]{{"End"},{new Timestamp(System.currentTimeMillis())}}, new Object[][]{{"ID"},{"="},{updateLog},{}});
    }
    
}
