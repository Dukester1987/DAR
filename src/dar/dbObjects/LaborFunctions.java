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
public class LaborFunctions {
    private int ID;
    private String FuncName;

    public LaborFunctions(int ID, String FuncName) {
        this.ID = ID;
        this.FuncName = FuncName;
    }

    public int getID() {
        return ID;
    }

    public String getFuncName() {
        return FuncName;
    }

    @Override
    public String toString() {
        return FuncName;
    }
    
    
    
}
