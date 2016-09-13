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
public class RecipeList {
    private int ID;
    private int productAllocationID;
    private int siteID;
    private int productID;
    private String mainProductName;
    private String recName;
    private boolean status;

    public RecipeList(int ID, int productAllocationID, int siteID, int productID, String mainProductName, String recName, boolean status) {
        this.ID = ID;
        this.productAllocationID = productAllocationID;
        this.productID = productID;
        this.mainProductName = mainProductName;
        this.siteID = siteID;
        this.recName = recName;
        this.status = status;
    }

    public int getProductID() {
        return productID;
    }

    public String getMainProductName() {
        return mainProductName;
    }

    public int getID() {
        return ID;
    }

    public int getProductAllocationID() {
        return productAllocationID;
    }

    public int getSiteID() {
        return siteID;
    }

    public String getRecName() {
        return recName;
    }

    public boolean isStatus() {
        return status;
    }
    
    
    
}
