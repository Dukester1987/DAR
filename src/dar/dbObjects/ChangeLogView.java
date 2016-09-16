/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.dbObjects;

import java.sql.Timestamp;

/**
 *
 * @author ldulka
 */
public class ChangeLogView {
    private final int ID;
    private final String AffectedTable;
    private final String rowID;
    private final String Operation;
    private final String SQLString;
    private final int loginID;
    private final Timestamp timeChanged;
    private final int type;

    public ChangeLogView(int ID, String AffectedTable, String rowID, String Operation, String SQLString, int loginID, Timestamp timeChanged, int type) {
        this.ID = ID;
        this.AffectedTable = AffectedTable;
        this.rowID = rowID;
        this.Operation = Operation;
        this.SQLString = SQLString;
        this.loginID = loginID;
        this.timeChanged = timeChanged;
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public String getAffectedTable() {
        return AffectedTable;
    }

    public String getRowID() {
        return rowID;
    }

    public String getOperation() {
        return Operation;
    }

    public String getSQLString() {
        return SQLString;
    }

    public int getLoginID() {
        return loginID;
    }

    public Timestamp getTimeChanged() {
        return timeChanged;
    }

    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("ID: %s | AffectedTable: %s| RowID: %s | Operation: %s | SQL: %s | LoginID: %s | Time: %s | Type: %s\n", ID,AffectedTable,rowID,Operation,SQLString,loginID,timeChanged,type);
    }
    
    
           
}
