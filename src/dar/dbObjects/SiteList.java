/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects;

/**
 *
 * @author ldulka
 */
public class SiteList {
    private String siteName;
    private int siteID;
    private String siteDesc;

    public SiteList(int siteID, String siteName, String siteDesc) {
        this.siteName = siteName;
        this.siteID = siteID;
        this.siteDesc = siteDesc;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public int getSiteID() {
        return siteID;
    }

    public String getSiteName() {
        return siteName;
    }
    
    @Override
    public String toString(){
        return siteName;
    }
}
