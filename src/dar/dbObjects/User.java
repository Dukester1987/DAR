package dar.dbObjects;

import dar.Functions.FileLogger;
import dar.localDB.LocalWraper;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class User {
    private int id;
    private String loginName;
    private String password;
    private String rights;
    private int status;
    private Timestamp lastUpload;
    private Timestamp lastDownload;
    private static long siteID = 0;
    private String siteName;    
    private static Date date;

    public User(int id, String loginName, String password, String rights, int status, Timestamp lastUpload, Timestamp lastDownload) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.rights = rights;
        this.status = status;
        this.lastUpload = lastUpload;
        this.lastDownload = lastDownload;
    }
    
    public long getUserPermissions(){
        long permission = 1;
        try {
            JSONObject jString = (JSONObject) new JSONParser().parse(rights);
                if(jString.get("Rights")!=null){
                    permission = (long) jString.get("Rights");
                }
                
        } catch (ParseException ex) {
            ex.printStackTrace();
            new FileLogger(ex.getStackTrace());
        }
        return permission;        
    }
    
    public long getSiteID(){
        try {
            JSONObject jString = (JSONObject) new JSONParser().parse(rights);
            if(siteID==0){
                if(jString.get("SiteID")!=null){
                    siteID = (long) jString.get("SiteID");
                } else {
                    siteID = getSiteID(rights);
                }
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            new FileLogger(ex.getStackTrace());
        }
        return siteID;
    } 
    
    public void setSiteID(int ID){
        siteID = ID;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public Date getDate(){
        return date;
    }
    
    public long getSiteID(String JSONString){ 
        System.out.println(JSONString);
        return getSiteIDs(JSONString).get(0);
    }  
    
    public int getAmountOfSites(){
        return (int) getSiteIDs(rights).stream().count();
    }    
    
    public ArrayList<Long> getSiteIDs(String JSONString){
        ArrayList list = new ArrayList<String>();
        Long siteID = 0L;
        try {
            JSONObject jString = (JSONObject) new JSONParser().parse(JSONString);
            if(jString.get("Sites")!=null){
                JSONArray msg = (JSONArray) jString.get("Sites");
                Iterator<String> iterator = msg.iterator();
                while (iterator.hasNext()) {
                        list.add(iterator.next());
                }
            } else {
                list.add(siteID);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }        
        return list;
    }    
    
    public String getSiteName(LocalWraper db){
        System.out.println(getSiteID());
        String query = String.format("SELECT SiteName FROM SiteList WHERE ID = %s",getSiteID());
        String toReturn = getLoginName();
        ResultSet rs = db.runQuery(query);
        if(db.getRowCount(rs)>0){
            try {
                rs.next();
                toReturn = rs.getString("SiteName");
            } catch (SQLException ex) {
                ex.printStackTrace();
                new FileLogger(ex.toString());
            }
        }
        return toReturn;
    }

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getPassword() {
        return password;
    }

    public String getRights() {
        return rights;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getLastUpload() {
        if(lastUpload == null){
           return new Timestamp(1L);
        } else {
           return lastUpload;            
        }

    }

    public Timestamp getLastDownload() {
        if(lastDownload == null){
           return new Timestamp(1L);
        } else {
           return lastDownload;            
        }
    }
    
    
    
    
    
}
