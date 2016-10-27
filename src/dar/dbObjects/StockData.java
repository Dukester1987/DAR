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
public class StockData {
    private int ProductID;
    private String ProductName;
    private double Stock;

    public StockData(int ProductID, String ProductName, double Stock) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.Stock = Stock;
    }

    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getStock() {
        return Stock;
    }
                    
    
}
