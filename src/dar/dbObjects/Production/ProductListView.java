/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects.Production;

/**
 *
 * @author ldulka
 */
public class ProductListView {
    private final int allocationID;
    private final int productID;
    private final int siteID;
    private final int EPAID;
    private final int GroupID;
    private final String ProductName;
    private final double productVal;
    private final int typeID;
    private final String UOM;

    public ProductListView(int allocationID, int productID, int siteID, int EPAID, int GroupID, String ProductName, double productVal, int typeID, String UOM) {
        this.allocationID = allocationID;
        this.productID = productID;
        this.siteID = siteID;
        this.EPAID = EPAID;
        this.GroupID = GroupID;
        this.ProductName = ProductName;
        this.productVal = productVal;
        this.typeID = typeID;
        this.UOM = UOM;
    }

    public int getAllocationID() {
        return allocationID;
    }

    public int getProductID() {
        return productID;
    }

    public int getSiteID() {
        return siteID;
    }

    public int getEPAID() {
        return EPAID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getProductVal() {
        return productVal;
    }

    public int getTypeID() {
        return typeID;
    }

    public String getUOM() {
        return UOM;
    }

    @Override
    public String toString() {
        return ProductName;
    }
    
    
    
    
}
