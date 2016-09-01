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
public class LaborView {
    private int SiteId;
    private int AllocationID;
    private int UtilizationID;
    private int LabourID;
    private String LaborName;
    private String Function;
    private double Hours;
    private String Status;
    private int statusID;
    private String Notes;
    private String PlantId;

    public LaborView(int SiteId, int AllocationID, int UtilizationID, int LabourID, String LaborName, String Function, double Hours, String Status, int StatusID, String Notes, String PlantId) {
        this.SiteId = SiteId;
        this.AllocationID = AllocationID;
        this.UtilizationID = UtilizationID;
        this.LabourID = LabourID;
        this.LaborName = LaborName;
        this.Function = Function;
        this.Hours = Hours;
        this.Status = Status;
        this.Notes = Notes;
        this.PlantId = PlantId;
        this.statusID = statusID;
    }

    public int getStatusID() {
        return statusID;
    }

    public int getSiteId() {
        return SiteId;
    }

    public int getAllocationID() {
        return AllocationID;
    }

    public int getUtilizationID() {
        return UtilizationID;
    }

    public int getLabourID() {
        return LabourID;
    }

    public String getLaborName() {
        return LaborName;
    }

    public String getFunction() {
        return Function;
    }

    public double getHours() {
        return Hours;
    }

    public String getStatus() {
        return Status;
    }

    public String getNotes() {
        return Notes;
    }

    public String getPlantId() {
        return PlantId;
    }
    
    
}
