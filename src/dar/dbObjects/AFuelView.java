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
public class AFuelView {
    private final int allocationID;
    private final int siteID;
    private final String plantNo;
    private final String rego;
    private final String description;
    private final double amount;
    private final int utilID;

    public AFuelView(int allocationID, int utilID, int siteID, String plantNo, String rego, String description, double amount) {
        this.allocationID = allocationID;
        this.utilID = utilID;
        this.siteID = siteID;
        this.plantNo = plantNo;
        this.rego = rego;
        this.description = description;
        this.amount = amount;
    }

    public int getUtilID() {
        return utilID;
    }

    public int getAllocationID() {
        return allocationID;
    }

    public int getSiteID() {
        return siteID;
    }

    public String getPlantNo() {
        return plantNo;
    }

    public String getRego() {
        return rego;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    
    
    
}