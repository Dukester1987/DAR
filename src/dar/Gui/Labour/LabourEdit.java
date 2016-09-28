/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui.Labour;

import dar.Gui.GuiIcon;
import dar.dbObjects.LaborFunctions;
import dar.localDB.LaborViewDataHandler;
import java.awt.event.KeyEvent;
import java.sql.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

/**
 *
 * @author ldulka
 */
public class LabourEdit extends javax.swing.JFrame {

    private final LaborViewDataHandler lw;
    private final JTable table;
    private final Date date;
    private final String originalName;

    public LabourEdit(LaborViewDataHandler lw, String LabourName, JTable laborUtil, Date date) {
        initComponents();
        new GuiIcon(this, "Application");
        this.originalName = LabourName;
        this.lw = lw;
        this.table = laborUtil;
        this.date = date;
        lName.setText(LabourName.trim());
        
               

        //make magic happend
        lw.fillComboBoxWithFunctions(lFunc);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        lName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lFunc = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Labour edit");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        jLabel6.setText("Labour name:");

        jLabel7.setText("Function:");

        lFunc.setModel(new DefaultComboBoxModel());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Modify.png"))); // NOI18N
        jButton4.setText("Edit Labour");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lFunc, javax.swing.GroupLayout.Alignment.LEADING, 0, 157, Short.MAX_VALUE)
                        .addComponent(lName, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LaborFunctions function = (LaborFunctions) lFunc.getSelectedItem();
        lw.updateLabour(originalName, lName.getText(),function.getID(),table,date); 
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
        System.out.println(evt.getKeyCode());
    }//GEN-LAST:event_formKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> lFunc;
    private javax.swing.JTextField lName;
    // End of variables declaration//GEN-END:variables
}
