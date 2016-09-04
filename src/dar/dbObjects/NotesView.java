/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects;

import java.sql.Date;

/**
 *
 * @author ldulka
 */
public class NotesView {
    private int ID;
    private int SiteID;
    private String Notes;
    private Date DateFor;

    public NotesView(int ID, int SiteID, String Notes, Date DateFor) {
        this.ID = ID;
        this.SiteID = SiteID;
        this.Notes = Notes;
        this.DateFor = DateFor;
    }

    public int getID() {
        return ID;
    }

    public int getSiteID() {
        return SiteID;
    }

    public String getNotes() {
        return Notes;
    }

    public Date getDateFor() {
        return DateFor;
    }
    
    
    
}
