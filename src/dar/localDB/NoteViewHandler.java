/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.localDB;

import dar.Functions.FileLogger;
import dar.Functions.Functions;
import dar.dbObjects.NotesView;
import dar.dbObjects.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author ldulka
 */
public class NoteViewHandler {

    private LocalWraper con;
    private User user;
    private JTextArea textarea;
    private Date dateFor;
    private ArrayList<NotesView> notes;

    public NoteViewHandler(LocalWraper con, User user, JTextArea textarea, Date dateFor) {
        this.con = con;
        this.user = user;
        this.textarea = textarea;
        this.dateFor = dateFor;
    }
    
    public ArrayList<NotesView> getView(){
        
        notes = new ArrayList<NotesView>();
        String query = String.format("SELECT * FROM SiteNotes WHERE SiteID = %s AND DateFor = '%s'", user.getSiteID(),dateFor);        
        ResultSet rs = con.runQuery(query);
        
        try {
            while(rs.next()){
                NotesView view = new NotesView(rs.getInt("ID"), rs.getInt("SiteID"), rs.getString("Notes"), rs.getDate("DateFor"));
                notes.add(view);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        
        return notes;
        
    }
    
    public void displayNotesInTextField(JTextArea txt,Date date){
        this.dateFor = date;
        
        ArrayList<NotesView> reports = getView();
        
        if(reports.isEmpty()){
            textarea.setText("");
        }
        for (NotesView report : reports) {
            textarea.setText(report.getNotes());
        }
    }

    public void saveDate(JTextArea MyComents, Date date) {
        String text = Functions.forHTML(MyComents.getText());
        if(con.hasDuplicity(con.dbSelect("SiteNotes",new Object[][]{{"SiteID","DateFor"},{"=","="},{con.userData.getSiteID(),date},{"AND"}}))){
            // update
            
            if(isNoteChanged(date, MyComents.getText()) || !MyComents.getText().isEmpty()){
                System.err.println("note has changed");
                int noteID = getNoteID(date);
                con.dbUpdate("SiteNotes", new Object[][]{{"Notes"},{text}}, new Object[][]{{"ID","SiteID"},{"=","="},{noteID,con.userData.getSiteID()},{"AND"}});                
            } else {
                System.err.println("Note has not changed");
            }
            //get Note ID
        } else {
            // insert
            con.dbInsert("SiteNotes", new Object[][]{{"SiteID","Notes","DateFor"},{con.userData.getSiteID(),text,date}});
        }           
    }

    private int getNoteID(Date date) {
        int NoteID = 0;
        ResultSet rs = con.dbSelect(new Object[]{"ID"}, "SiteNotes", new Object[][]{{"DateFor","SiteID"},{"=","="},{date,con.userData.getSiteID()},{"AND"}});
        try {
            rs.next();
            NoteID = rs.getInt("ID");
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }        
        return NoteID;
    }
    
    private boolean isNoteChanged(Date date, String note){
        int noteID = getNoteID(date);
        ResultSet rs = con.dbSelect(new Object[]{"ID"},"SiteNotes", new Object[][]{{"Notes","ID"},{"=","="},{note,noteID},{"AND"}});
        return con.getRowCount(rs)>0?false:true;
    }
    
}
