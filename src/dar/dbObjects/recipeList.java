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
public class recipeList {
    private int recipeID;
    private String RecipeName;

    public recipeList(int recipeID, String RecipeName) {
        this.recipeID = recipeID;
        this.RecipeName = RecipeName;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public String getRecipeName() {
        return RecipeName;
    }
    
    
    
}
