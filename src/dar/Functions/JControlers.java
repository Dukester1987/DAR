/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import dar.dbObjects.LaborList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

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
