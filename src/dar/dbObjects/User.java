package dar.dbObjects;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private long siteID = 0;

    public User(int id, String loginName, String password, String rights, int status, Timestamp lastUpload, Timestamp lastDownload) {
        this.id = id;
        this.loginName = loginName;
        this.password = password;
        this.rights = rights;
        this.status = status;
        this.lastUpload = lastUpload;
        this.lastDownload = lastDownload;
    }
    
    public long getSiteID(){
        try {
            JSONObject jString = (JSONObject) new JSONParser().parse(rights);
            siteID = (long) jString.get("SiteID");
        } catch (ParseException ex) {
            System.out.println("cant parse JSON");
        }
        return siteID;
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
        return lastUpload;
    }

    public Timestamp getLastDownload() {
        return lastDownload;
    }
    
    
    
    
    
}
