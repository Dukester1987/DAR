/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.Functions.Functions;
import dar.localDB.AFViewDataHandler;
import dar.remoteDB.DBWrapper;
import dar.localDB.LocalWraper;
import dar.localDB.PlantViewDataHandler;
import java.awt.Color;
import java.awt.Font;
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
    private final AFViewDataHandler af;

    public Gui(LocalWraper db) {      
        date = today();
        initComponents();
        //change headers of tables
        changeHeaders();
        setIcon();
        
        this.db = db;
        
        pw = new PlantViewDataHandler(this.db, db.userData,PlantUtil);
        pw.displayPlantViewInTable(PlantUtil, date);
        
        af = new AFViewDataHandler(this.db, db.userData, AditionalFuel);
        af.displayViewInTable(AditionalFuel, date);
        
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        addp = new javax.swing.JButton();
        utilPerc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlantUtil = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        AditionalFuel = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        RemoveAFuel = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        fUnitNo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fRego = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        AddAFuel = new javax.swing.JButton();
        title = new javax.swing.JLabel();
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

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(jTabbedPane1.getFont().deriveFont(jTabbedPane1.getFont().getStyle() | java.awt.Font.BOLD, jTabbedPane1.getFont().getSize()+3));

        addp.setMnemonic('A');
        addp.setText("Add Plant");
        addp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpActionPerformed(evt);
            }
        });

        utilPerc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        utilPerc.setForeground(new java.awt.Color(255, 51, 51));
        utilPerc.setText("Utilization 32%");

        PlantUtil.setAutoCreateRowSorter(true);
        PlantUtil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PlantUtil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Plant No", "Plant Desc", "Start Hours", "End hours", "Fuel", "Notes", "Hours"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PlantUtil.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        PlantUtil.setDoubleBuffered(true);
        PlantUtil.setDragEnabled(true);
        PlantUtil.setIntercellSpacing(new java.awt.Dimension(2, 2));
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
            PlantUtil.getColumnModel().getColumn(8).setResizable(false);
        }

        AditionalFuel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "allocationID", "utilID", "UnitNo", "Rego", "Description", "Fuel Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(AditionalFuel);
        if (AditionalFuel.getColumnModel().getColumnCount() > 0) {
            AditionalFuel.getColumnModel().getColumn(0).setResizable(false);
            AditionalFuel.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setText("Aditional Fuel:");

        RemoveAFuel.setText("Remove");
        RemoveAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAFuelActionPerformed(evt);
            }
        });

        jInternalFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jInternalFrame1.setName(""); // NOI18N
        jInternalFrame1.setOpaque(true);
        jInternalFrame1.setVisible(true);

        jLabel2.setText("UnitNo");

        jLabel3.setText("Rego");

        jLabel4.setText("Description");

        jLabel5.setText("Fuel Amount");

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fUnitNo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fRego, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fUnitNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fRego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        AddAFuel.setText("Add new");
        AddAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAFuelActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(addp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(utilPerc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(RemoveAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AddAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(398, 398, 398)
                                .addComponent(RemoveAFuel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddAFuel)
                            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(236, 236, 236))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(addp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(utilPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addp)
                    .addComponent(utilPerc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(RemoveAFuel)
                    .addComponent(AddAFuel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Plant Utilization", jLayeredPane1);

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setText("Menangle");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(label)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void AddAFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAFuelActionPerformed
        addAditionalFuel();
    }//GEN-LAST:event_AddAFuelActionPerformed

    private void RemoveAFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAFuelActionPerformed
        removeSelected();
    }//GEN-LAST:event_RemoveAFuelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JButton AddAFuel;
    private javax.swing.JTable AditionalFuel;
    public javax.swing.JTable PlantUtil;
    private javax.swing.JButton RemoveAFuel;
    private javax.swing.JButton addp;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JTextField fRego;
    private javax.swing.JTextField fUnitNo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JLabel label;
    private javax.swing.JLabel title;
    private javax.swing.JLabel utilPerc;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("res/hqlogo.png")));
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
    
    private Date yesterday(){
        return new Date(System.currentTimeMillis()-24*60*60*1000);
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
            af.displayViewInTable(AditionalFuel, date);
            utilPercChange();
            if(!today().toString().equals(date.toString())){
                addp.setEnabled(false);
                AddAFuel.setEnabled(false);
                RemoveAFuel.setEnabled(false);
            } else {
                addp.setEnabled(true);
                AddAFuel.setEnabled(true);
                RemoveAFuel.setEnabled(true);
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
        int viewRow = PlantUtil.getEditingRow();
        System.out.println(viewRow);
        if(viewRow>-1){
            int k = PlantUtil.convertRowIndexToModel(viewRow);
            DefaultTableModel model = (DefaultTableModel) PlantUtil.getModel();
            int PlantUtilizationID = (int) model.getValueAt(k, 0);
            int PlantAllocationID = (int) model.getValueAt(k, 1);
            String PlantNo = (String) model.getValueAt(k, 2);
            String PlantDesc = (String) model.getValueAt(k, 3);
            int StartHours = (int) model.getValueAt(k, 4);
            int EndHours = (int) model.getValueAt(k, 5);
            double Fuel = (double) model.getValueAt(k, 6);
            String Notes = (String) model.getValueAt(k, 7);
            
            if(EndHours<StartHours){
                JOptionPane.showMessageDialog(null,"End hours can not be lower than start hours!");
                pw.displayPlantViewInTable(PlantUtil, date);
            } else {
                if(PlantUtilizationID==0){
                    Object[][] query = {{"PlantAllocationID","StartHours","EndHours","DateFor","Fuel","Notes"},{PlantAllocationID,StartHours,EndHours,date,Fuel,Notes}};
                    db.dbInsert("PlantUtilization", query);
                    pw.displayPlantViewInTable(PlantUtil, date); // refresh table
                } else {
                    // update operation
                    Object[][] query = {{"StartHours","EndHours","Fuel","Notes"},{StartHours,EndHours,Fuel,Notes}};
                    Object[][] where = {{"ID"},{"="},{PlantUtilizationID},{}};
                    db.dbUpdate("PlantUtilization", query, where);   
                    pw.displayPlantViewInTable(PlantUtil, date);
                }     
            }
        }
        //System.out.println(k);
    }

    private void changeHeaders() {
        // plant utilization
        PlantUtil.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
    }

    private void addAditionalFuel() {
        String funo = Functions.forHTML(fUnitNo.getText());
        String frego = Functions.forHTML(fRego.getText());
        String fdesc = Functions.forHTML(jTextField3.getText());
        try{
            double famount = Double.parseDouble(jTextField4.getText());
            if((funo.isEmpty() && frego.isEmpty()) || (fdesc.isEmpty()) ){
                JOptionPane.showMessageDialog(null, "You have to fill all fields");
            } else {
                Object[][] query = {{"SiteID","PlantNo","Rego","StartDate","EndDate"},{"=","=","=","<=",">="},{db.userData.getSiteID(),funo,frego,date,date},{"AND","AND","AND","AND"}};                    
                if(db.hasDuplicity(db.dbSelect("AFAllocation", query))){
                    JOptionPane.showMessageDialog(null, "Already in list");                   
                } else {
                    //add
                    Object[][] dataset = {{"SiteID","PlantNo","Rego","Description","StartDate","EndDate"},{db.userData.getSiteID(),funo,frego,fdesc,date,nextDate()}};
                    int allocID = db.dbInsert("AFAllocation", dataset);
                    Object[][] dataset2 = {{"AFAllocationID","Amount","DateFor"},{allocID,famount,date}};
                    db.dbInsert("AFFuel", dataset2);
                    af.displayViewInTable(AditionalFuel, date);
                    jTextField4.setText("");
                    jTextField3.setText("");
                    fRego.setText("");
                    fUnitNo.setText("");
                }
            }            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Amount must be numeric value");
        }
        

    }

    private void removeSelected() {
        int[] rows = AditionalFuel.getSelectedRows();
        DefaultTableModel model = (DefaultTableModel) AditionalFuel.getModel();
        if(AditionalFuel.getSelectedRowCount()>0){
            for (int row : rows) {           
                int alocID =  (int) model.getValueAt(row, 0);
                int utilID =  (int) model.getValueAt(row, 1);

                Object[][] what = {{"amount"},{"0"}};
                Object[][] where = {{"ID"},{"="},{utilID},{}};                                       
                db.dbUpdate("AFFuel", what, where);

                Object[][] w = {{"EndDate"},{yesterday()}};
                Object[][] wh = {{"ID"},{"="},{alocID},{}};
                db.dbUpdate("AFAllocation", w, wh);                               
            }
            af.displayViewInTable(AditionalFuel, date);            
        } else {
            JOptionPane.showMessageDialog(null,"No rows selected!");
        }

    }
    

    
}
