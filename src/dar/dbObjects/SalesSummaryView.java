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
public class SalesSummaryView {
    private int productID;
    private String productName;
    private double tonage;
    private double priceIncGST;
    private double priceExtGST;

    public SalesSummaryView(int productID, String productName, double tonage, double priceIncGST, double priceExtGST) {
        this.productID = productID;
        this.productName = productName;
        this.tonage = tonage;
        this.priceIncGST = priceIncGST;
        this.priceExtGST = priceExtGST;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
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
    
    
    
}
