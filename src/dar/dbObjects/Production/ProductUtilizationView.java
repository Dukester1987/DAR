/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects.Production;

import java.sql.Date;

/**
 *
 * @author ldulka
 */
public class ProductUtilizationView {
    private int utilizationID;
    private int allocationID;
    private String ProductName;
    private double qty;
    private String notes;
    private int Type;
    private Date dateFor;

    public ProductUtilizationView(int utilizationID, int allocationID, String ProductName, double qty, String notes, int Type, Date dateFor) {
        this.utilizationID = utilizationID;
        this.allocationID = allocationID;
        this.ProductName = ProductName;
        this.qty = qty;
        this.notes = notes;
        this.Type = Type;
        this.dateFor = dateFor;
    }

    public int getUtilizationID() {
        return utilizationID;
    }

    public int getType() {
        return Type;
    }

    public int getAllocationID() {
        return allocationID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getQty() {
        return qty;
    }

    public String getNotes() {
        return notes;
    }

    public Date getDateFor() {
        return dateFor;
    }
    
    
    
}
