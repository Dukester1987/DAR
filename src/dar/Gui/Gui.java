/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.remoteDB.DBWrapper;
import dar.localDB.LocalWraper;
import dar.localDB.PlantViewDataHandler;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ldulka
 */
public class Gui extends javax.swing.JFrame {
    private LocalWraper db;
    private DBWrapper db1;
    private PlantViewDataHandler pw;
    private Date date;
    private boolean actionListenerGo = false;

    public Gui(LocalWraper db) {      
        date = today();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("hqlogo.png")));
        
        this.db = db;
        
        pw = new PlantViewDataHandler(this.db, db.userData,PlantUtil);
        pw.displayPlantViewInTable(PlantUtil, date);
        actionListenerGo = true;
        setName(title);
        utilPercChange();
        
        db1 = new DBWrapper(label);
        Thread t = new Thread(db1);
        t.start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        addp = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlantUtil = new javax.swing.JTable();
        Filter = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        utilPerc = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        refreshP = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        datePicker = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        About = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1080, 720));

        addp.setMnemonic('A');
        addp.setText("Add Plant");
        addp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpActionPerformed(evt);
            }
        });

        PlantUtil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Plant No", "Plant Desc", "Start Hours", "End hours", "Fuel", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PlantUtil.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                PlantUtilPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(PlantUtil);
        if (PlantUtil.getColumnModel().getColumnCount() > 0) {
            PlantUtil.getColumnModel().getColumn(0).setResizable(false);
            PlantUtil.getColumnModel().getColumn(0).setPreferredWidth(0);
            PlantUtil.getColumnModel().getColumn(1).setResizable(false);
            PlantUtil.getColumnModel().getColumn(1).setPreferredWidth(0);
            PlantUtil.getColumnModel().getColumn(2).setPreferredWidth(50);
            PlantUtil.getColumnModel().getColumn(3).setPreferredWidth(400);
        }

        Filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Not Finished", "Finished" }));

        jLabel2.setText("Select Filter");

        utilPerc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        utilPerc.setForeground(new java.awt.Color(255, 51, 51));
        utilPerc.setText("Utilization 32%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addp)
                        .addGap(385, 385, 385)
                        .addComponent(utilPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addp)
                    .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(utilPerc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Plant Utilization", jPanel1);

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setText("Menangle");

        refreshP.setText("Refresh data");
        refreshP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshPActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        label.setText("Trying to connect...");

        datePicker.setDate(date);
        datePicker.setMaxSelectableDate(today());
        datePicker.setMinSelectableDate(firstDate());
        datePicker.setName("dateFor"); // NOI18N
        datePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickerPropertyChange(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem2.setText("Logout");
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem4.setText("Exit");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setText("Copy");
        jMenu2.add(jMenuItem5);

        jMenuItem7.setText("Cut");
        jMenu2.add(jMenuItem7);

        jMenuItem6.setText("Paste");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        About.setText("About");
        jMenuBar1.add(About);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(refreshP, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(refreshP)
                    .addComponent(label))
                .addGap(4, 4, 4)
                .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshPActionPerformed
        pw.displayPlantViewInTable(PlantUtil,today());
        utilPercChange();
        JOptionPane.showMessageDialog(null, "Utilization data has been sucessfully loaded!");
    }//GEN-LAST:event_refreshPActionPerformed

    private void PlantUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_PlantUtilPropertyChange
        updateTable();
    }//GEN-LAST:event_PlantUtilPropertyChange

    private void addpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpActionPerformed
        addPlant();
    }//GEN-LAST:event_addpActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        changeDate();
    }//GEN-LAST:event_datePickerPropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JComboBox<String> Filter;
    public javax.swing.JTable PlantUtil;
    private javax.swing.JButton addp;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label;
    private javax.swing.JButton refreshP;
    private javax.swing.JLabel title;
    private javax.swing.JLabel utilPerc;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("iconframe.png")));
    }

    private Date today() {
        Date date;
        date = new Date(System.currentTimeMillis());
        return date;
    }

    private void setName(JLabel title) {
        String name = db.userData.getLoginName();
        title.setText(name);
    }

    private Date nextDate() {
        long add = 315360000000L;
        return new Date(System.currentTimeMillis()+add);
    }

    private java.util.Date firstDate(){
        java.util.Date d = new java.util.Date();
        d.setTime(System.currentTimeMillis()-432000000);
        return d;
    }
    
    private Date setDate(java.util.Date date) {
        Date sqldate = new Date(date.getTime());
        return sqldate;       
    }
    

    private void utilPercChange(){
        TableModel model = PlantUtil.getModel();
        int hoursTotal = 0;
        int optimum = model.getRowCount()*8;
        long percentage;
        for(int i=0;i<model.getRowCount(); i++){
            if(model.getValueAt(i, 5)!=null && (int) model.getValueAt(i, 5) != 0){
                hoursTotal += (int) model.getValueAt(i, 5) - (int) model.getValueAt(i, 4);                
            }   
        }
        if(hoursTotal<0 || optimum<=0){
           // apply percentage
           
        } else {
           // apply percentage
           percentage = Math.round((double) hoursTotal / (double) optimum*100);
           if(percentage<25){
               utilPerc.setForeground(Color.red);
           } else if(percentage>75 && percentage<120) {
               utilPerc.setForeground(Color.green);
           } else if(percentage>120) {
               utilPerc.setForeground(Color.red);
               JOptionPane.showMessageDialog(null,"Check your hours!");
           } else {
                utilPerc.setForeground(Color.BLACK);
           }
           utilPerc.setText(String.format("Utilization %s %%", percentage));
        }        
    }    

    private void changeDate() {
        if(actionListenerGo){
            date = setDate(datePicker.getDate());
            pw.displayPlantViewInTable(PlantUtil, date); //refresh table
            utilPercChange();
            if(!today().toString().equals(date.toString())){
                addp.setEnabled(false);
            } else {
                addp.setEnabled(true);
            }
        }        
    }

    private void addPlant() {
        if(date.toString().equals(today().toString())){
            DefaultTableModel model = (DefaultTableModel) PlantUtil.getModel();
            String message = JOptionPane.showInputDialog(null, "Insert plant Number");
            if(message!=null){

                Object[][] Query = new Object[][]{{"SiteID", "PlantID", "StartDate", "EndDate"},
                                                  {"=","=","<=",">=","IS"},
                                                  {db.userData.getSiteID(),message,date,date},
                                                  {"AND","AND","AND","OR"}};
                if(pw.isPlantDescription(message)){
                    if(db.hasDuplicity(db.dbSelect("PlantAllocation", Query))){
                        JOptionPane.showMessageDialog(null, "Plant is already in the list");
                    } else {
                        Object[][] dataset = new Object[][]{{"PlantID","SiteID","StartDate","EndDate"},{message,db.userData.getSiteID(),date,nextDate()}};
                        db.dbInsert("PlantAllocation",dataset);
                        pw.displayPlantViewInTable(PlantUtil,date);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selected Plant No doesn't exists in the database");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "To add plant for previous days please contact your administrator!");
        } 
    }

    private void updateTable() {
        utilPercChange();
        int k = PlantUtil.getEditingRow();
        if(k>-1){
            DefaultTableModel model = (DefaultTableModel) PlantUtil.getModel();
            int PlantUtilizationID = (int) model.getValueAt(k, 0);
            int PlantAllocationID = (int) model.getValueAt(k, 1);
            String PlantNo = (String) model.getValueAt(k, 2);
            String PlantDesc = (String) model.getValueAt(k, 3);
            int StartHours = (int) model.getValueAt(k, 4);
            int EndHours = (int) model.getValueAt(k, 5);
            double Fuel = (double) model.getValueAt(k, 6);
            String Notes = (String) model.getValueAt(k, 7);
            
            if(PlantUtilizationID==0){
                Object[][] query = new Object[][]{{"PlantAllocationID","StartHours","EndHours","DateFor","Fuel","Notes"},{PlantAllocationID,StartHours,EndHours,date,Fuel,Notes}};
                db.dbInsert("PlantUtilization", query);
                pw.displayPlantViewInTable(PlantUtil, date); // refresh table
            } else {
                // update operation
                Object[][] query = new Object[][]{{"StartHours","EndHours","Fuel","Notes"},{StartHours,EndHours,Fuel,Notes}};
                Object[][] where = new Object[][]{{"ID"},{"="},{PlantUtilizationID},{}};
                db.dbUpdate("PlantUtilization", query, where);             
            }            
        }
        //System.out.println(k);
    }
    
}
