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
public class SalesDetailView {
    private int salesID;
    private String productName;
    private String direction;
    private double tonage;
    private double priceIncGST;
    private double priceExtGST;
    private double EPA;

    public SalesDetailView(int salesID, String productName, String direction, double tonage, double priceIncGST, double priceExtGST, double EPA) {
        this.salesID = salesID;
        this.productName = productName;
        this.direction = direction;
        this.tonage = tonage;
        this.priceIncGST = priceIncGST;
        this.priceExtGST = priceExtGST;
        this.EPA = EPA;
    }

    public int getSalesID() {
        return salesID;
    }

    public double getEPA() {
        return EPA;
    }        

    public String getProductName() {
        return productName;
    }

    public String getDirection() {
        return direction;
    }

    public double getTonage() {
        return tonage;
    }

    public double getPriceIncGST() {
        return priceIncGST;
    }

    public double getPriceExtGST() {
        return priceExtGST;
    }

    public Object getPricePerTon() {
        return priceExtGST/tonage;
    }
    
    
    
}
