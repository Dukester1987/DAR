/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import dar.dbObjects.LaborList;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ldulka
 */
public class JControlers {    
    
    public void filterModel(DefaultListModel model, ArrayList<LaborList> values, String filter) {
        for (LaborList s : values) {
            if (!s.getName().contains(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }
    
    public void fillListWithData(ArrayList<LaborList> data, DefaultListModel model){
        for (LaborList name : data) {
            model.addElement(name);
        }
    }
    
    public void jTableSelectAll(JTable table, String[] colNames){
        JTextField txt = new JTextField();
        txt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {txt.selectAll();}
            @Override
            public void focusLost(FocusEvent fe) {}
        });
        
        DefaultTableModel m = (DefaultTableModel) table.getModel();
        
        for (int i = 0; i < m.getColumnCount(); i++) {            
            TableColumn col = table.getColumnModel().getColumn(i);
            for (String colName : colNames) {
                if(col.getHeaderValue().equals(colName)){
                    col.setCellEditor(new DefaultCellEditor(txt));
                }                
            }                    
        }
    }    

    public void refreshList(ArrayList<LaborList> data, DefaultListModel model) {
        model.clear();
        for (LaborList d : data) {
            model.addElement(d);
        }
    }

    public void moveDataFromListToList(JList SourceList, JList destinationList, ArrayList<LaborList> sourceListData, ArrayList<LaborList> destinationListData) {
        List<LaborList> list = SourceList.getSelectedValuesList();
                
        for (LaborList s : list) {
            destinationListData.add(s);
            sourceListData.remove(s);
        }       
        
        refreshList(sourceListData, (DefaultListModel) SourceList.getModel());
        refreshList(destinationListData, (DefaultListModel) destinationList.getModel());
        
    }


    
}
