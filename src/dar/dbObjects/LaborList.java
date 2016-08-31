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
public class LaborList {
    private int ID;
    private String Name;
    private int FunctionID;
    private String Function;
    private double Rate;
    private final int siteID;

    public LaborList(int ID, int siteID, String Name, int FunctionID, String Function, double Rate) {
        this.ID = ID;
        this.siteID = siteID;
        this.Name = Name;
        this.FunctionID = FunctionID;
        this.Function = Function;
        this.Rate = Rate;
    }

    public int getSiteID() {
        return siteID;
    }
    
    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getFunctionID() {
        return FunctionID;
    }

    public String getFunction() {
        return Function;
    }

    public double getRate() {
        return Rate;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    
    
}
