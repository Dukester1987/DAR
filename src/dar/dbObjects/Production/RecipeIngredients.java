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
public class RecipeIngredients {
    private int ID;
    private double used;
    private int prodID;

    public RecipeIngredients(int ID, double used, int prodID) {
        this.ID = ID;
        this.used = used;
        this.prodID = prodID;
    }

    public int getID() {
        return ID;
    }

    public double getUsed() {
        return used;
    }

    public int getProdID() {
        return prodID;
    }
    
    
}
