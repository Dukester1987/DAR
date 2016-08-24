/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.remoteDB;

/**
 *
 * @author ldulka
 */
public class PlantUtilization {
    int ID;
    String plantNo;
    String plantDesc;
    int startHours;
    int endHours;
    double fuel;
    String Notes;

    public PlantUtilization(int ID, String plantNo, String plantDesc, int startHours, int endHours, double fuel, String Notes) {
        this.ID = ID;
        this.plantNo = plantNo;
        this.plantDesc = plantDesc;
        this.startHours = startHours;
        this.endHours = endHours;
        this.fuel = fuel;
        this.Notes = Notes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPlantNo() {
        return plantNo;
    }

    public void setPlantNo(String plantNo) {
        this.plantNo = plantNo;
    }

    public String getPlantDesc() {
        return plantDesc;
    }

    public void setPlantDesc(String plantDesc) {
        this.plantDesc = plantDesc;
    }

    public int getStartHours() {
        return startHours;
    }

    public void setStartHours(int startHours) {
        this.startHours = startHours;
    }

    public int getEndHours() {
        return endHours;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }
    
    
    
    
}
