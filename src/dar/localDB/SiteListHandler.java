/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.dbObjects.SiteList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author ldulka
 */
public class SiteListHandler {

    private final LocalWraper db;
    private ArrayList<SiteList> siteList;
    
    public SiteListHandler(LocalWraper db){
        this.db = db;
        setList();
    }
    
    public ArrayList<SiteList> getSiteList(){
        String query = "SELECT ID, SiteName, SiteDesc FROM SiteList";
        ResultSet rs = db.runQuery(query);
        ArrayList<SiteList> retList = new ArrayList<SiteList>();
        try {
            while(rs.next()){
                SiteList list = new SiteList(rs.getInt("ID"), rs.getString("SiteName"), rs.getString("SiteDesc"));
                retList.add(list);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.getStackTrace());
        }  
        return retList;
    }
    
    public void setList(){
        this.siteList = getSiteList();
    }
    
    public void fillComboBoxWithSites(JComboBox box){
        DefaultComboBoxModel model = (DefaultComboBoxModel) box.getModel();
        model.removeAllElements(); // cleaning
        for (SiteList site : siteList) {
            for(Long userSites : db.userData.getSiteIDs(db.userData.getRights())) {
                if((long) site.getSiteID()== userSites){
                    model.addElement(site);
                    if(site.getSiteID()==db.userData.getSiteID()){
                        model.setSelectedItem(site);
                    }      
                }
            }
        }
    }
    
    
}
