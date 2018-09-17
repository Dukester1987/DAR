/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects;

import java.util.Date;

/**
 *
 * @author ldulka
 */
public class PlantView {
    private int allocationID;
    private int siteID;
    private int utilizationID;
    private String plantID;
    private String plantDesc;
    private double rate;
    private int startHours;
    private int endHours;
    private Date dateFor;
    private double fuel;
    private Date startDate;
    private Date endDate;
    private String notes;

    public PlantView(int allocationID, int siteID, int utilizationID, String plantID, String plantDesc, double rate, int startHours, int endHours, Date dateFor, double fuel, Date startDate, Date endDate, String notes) {
        this.allocationID = allocationID;
        this.siteID = siteID;
        this.utilizationID = utilizationID;
        this.plantID = plantID;
        this.plantDesc = plantDesc;
        this.rate = rate;
        this.startHours = startHours;
        this.endHours = endHours;
        this.dateFor = dateFor;
        this.fuel = fuel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    public int getAllocationID() {
        return allocationID;
    }

    public int getSiteID() {
        return siteID;
    }

    public int getUtilizationID() {
        return utilizationID;
    }

    public String getPlantID() {
        return plantID;
    }

    public String getPlantDesc() {
        return plantDesc;
    }

    public double getRate() {
        return rate;
    }

    public int getStartHours() {
        return startHours;
    }

    public void setEndHours(int endHours) {
        this.endHours = endHours;
    }

    public int getEndHours() {
        return endHours;
    }

    public Date getDateFor() {
        return dateFor;
    }

    public double getFuel() {
        return fuel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getNotes() {
        return notes;
    }

   
    
}
