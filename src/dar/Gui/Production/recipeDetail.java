/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui.Production;

/**
 *
 * @author ldulka
 */
public class recipeDetail extends javax.swing.JPanel {

    public final int recipeID;
    public final String recipeName;

    /**
     * Creates new form recipeDetail
     */
    public recipeDetail(int recipeID,String recipeName) {
        this.recipeName = recipeName;
        this.recipeID = recipeID;    
        initComponents();
        
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myRadio = new javax.swing.JRadioButton();

        myRadio.setText(recipeName);
        myRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myRadioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(myRadio)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myRadio)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_myRadioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JRadioButton myRadio;
    // End of variables declaration//GEN-END:variables
}