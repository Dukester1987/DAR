/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ldulka
 */
public class MyCellEditor extends AbstractCellEditor implements TableCellEditor {

  JComponent component = new JTextField();

  @Override
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
      int rowIndex, int vColIndex) {
      
      if(isSelected){
          ((JTextField) component).selectAll();
          JOptionPane.showMessageDialog(null,"selected");
      } else {
          ((JTextField) component).setText((String) value);
      }
    

    return component;
  }

  @Override
  public Object getCellEditorValue() {
    return ((JTextField) component).getText();
  }
   
}
