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
public class LaborStatus {
    private int ID;
    private String Status;

    public LaborStatus(int ID, String Status) {
        this.ID = ID;
        this.Status = Status;
    }

    public int getID() {
        return ID;
    }

    public String getStatus() {
        return Status;
    }

    @Override
    public String toString() {
        return Status;
    }
    
    
    
}
