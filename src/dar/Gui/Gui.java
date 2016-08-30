/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.Functions.TimeWrapper;
import dar.localDB.AFViewDataHandler;
import dar.localDB.LaborViewDataHandler;
import dar.remoteDB.DBWrapper;
import dar.localDB.LocalWraper;
import dar.localDB.PlantViewDataHandler;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

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
    private LaborViewDataHandler lw;
    private TimeWrapper ti;
    private TableCellEditor editor;
    //private JTextField field;

    public Gui(LocalWraper db) {    
        this.ti = new TimeWrapper();
        date = ti.today();    
        initComponents();
        
        JTextField txt = new JTextField();
        txt.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                txt.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                
            }
        });
        DefaultTableModel model = (DefaultTableModel) PlantUtil.getModel();
        TableColumn col;
        col = PlantUtil.getColumnModel().getColumn(7);
        col.setCellEditor(new DefaultCellEditor(txt));
        
        setIcon();
        
        this.db = db;
        
        pw = new PlantViewDataHandler(this.db, db.userData,PlantUtil,utilPerc,utilProgressBar);
        pw.displayPlantViewInTable(PlantUtil, date);
        
        af = new AFViewDataHandler(this.db, db.userData, AditionalFuel);
        af.displayViewInTable(AditionalFuel, date);
        
        lw = new LaborViewDataHandler(this.db, db.userData, LaborUtil);
        lw.displayViewInTable(LaborUtil, date);
        
        actionListenerGo = true;
        setName(title);
        pw.utilPercChange();
        
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
        fDesc = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fAmount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        AddAFuel = new javax.swing.JButton();
        utilProgressBar = new javax.swing.JProgressBar();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        addp1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        LaborUtil = new javax.swing.JTable();
        addp2 = new javax.swing.JButton();
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
        utilPerc.setText("Utilization");

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
            PlantUtil.getColumnModel().getColumn(5).setHeaderValue("End hours");
            PlantUtil.getColumnModel().getColumn(6).setHeaderValue("Fuel");
            PlantUtil.getColumnModel().getColumn(8).setResizable(false);
            PlantUtil.getColumnModel().getColumn(8).setHeaderValue("Hours");
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
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AditionalFuel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                AditionalFuelPropertyChange(evt);
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
                    .addComponent(fAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fDesc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(fDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        AddAFuel.setText("Add new");
        AddAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAFuelActionPerformed(evt);
            }
        });

        utilProgressBar.setStringPainted(true);

        jLayeredPane1.setLayer(addp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(utilPerc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(RemoveAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jInternalFrame1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AddAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(utilProgressBar, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                        .addComponent(utilPerc)
                        .addGap(18, 18, 18)
                        .addComponent(utilProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addp)
                        .addComponent(utilPerc))
                    .addComponent(utilProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        addp1.setMnemonic('A');
        addp1.setText("Add Labor");
        addp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addp1ActionPerformed(evt);
            }
        });

        LaborUtil.setAutoCreateRowSorter(true);
        LaborUtil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LaborUtil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Labor Name", "Labor Function", "Hours", "Status", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        LaborUtil.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        LaborUtil.setDoubleBuffered(true);
        LaborUtil.setDragEnabled(true);
        LaborUtil.setIntercellSpacing(new java.awt.Dimension(2, 2));
        LaborUtil.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                LaborUtilPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(LaborUtil);
        if (LaborUtil.getColumnModel().getColumnCount() > 0) {
            LaborUtil.getColumnModel().getColumn(0).setResizable(false);
            LaborUtil.getColumnModel().getColumn(0).setPreferredWidth(0);
            LaborUtil.getColumnModel().getColumn(1).setResizable(false);
            LaborUtil.getColumnModel().getColumn(1).setPreferredWidth(0);
            LaborUtil.getColumnModel().getColumn(2).setPreferredWidth(50);
            LaborUtil.getColumnModel().getColumn(6).setPreferredWidth(400);
        }

        addp2.setMnemonic('A');
        addp2.setText("Remove");
        addp2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addp2ActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(addp1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(addp2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(addp1)
                        .addGap(18, 18, 18)
                        .addComponent(addp2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 999, Short.MAX_VALUE))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addp1)
                    .addComponent(addp2))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 229, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Labor Utilization", jLayeredPane2);

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setText("Menangle");

        label.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        label.setText("Trying to connect...");

        datePicker.setDate(date);
        datePicker.setMaxSelectableDate(ti.today());
        datePicker.setMinSelectableDate(ti.firstDate());
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
        if(actionListenerGo){
            pw.updateTable(date);
        }
    }//GEN-LAST:event_PlantUtilPropertyChange

    private void addpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpActionPerformed
        if(actionListenerGo)
            pw.addPlant(date);
    }//GEN-LAST:event_addpActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        changeDate();
    }//GEN-LAST:event_datePickerPropertyChange

    private void AddAFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAFuelActionPerformed
        if(actionListenerGo)
            af.addAditionalFuel(fUnitNo,fRego,fDesc,fAmount,date);
    }//GEN-LAST:event_AddAFuelActionPerformed

    private void RemoveAFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAFuelActionPerformed
        if(actionListenerGo)
            af.removeSelected(date);
    }//GEN-LAST:event_RemoveAFuelActionPerformed

    private void AditionalFuelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_AditionalFuelPropertyChange
        if(actionListenerGo)
            af.editAditionalFuel(date);
    }//GEN-LAST:event_AditionalFuelPropertyChange

    private void addp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addp1ActionPerformed

    private void LaborUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_LaborUtilPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_LaborUtilPropertyChange

    private void addp2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addp2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addp2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JButton AddAFuel;
    private javax.swing.JTable AditionalFuel;
    public javax.swing.JTable LaborUtil;
    public javax.swing.JTable PlantUtil;
    private javax.swing.JButton RemoveAFuel;
    private javax.swing.JButton addp;
    private javax.swing.JButton addp1;
    private javax.swing.JButton addp2;
    private com.toedter.calendar.JDateChooser datePicker;
    public javax.swing.JTextField fAmount;
    private javax.swing.JTextField fDesc;
    private javax.swing.JTextField fRego;
    private javax.swing.JTextField fUnitNo;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
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
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel title;
    private javax.swing.JLabel utilPerc;
    private javax.swing.JProgressBar utilProgressBar;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        GuiIcon icon = new GuiIcon(this);
    }

    private void setName(JLabel title) {
        String name = db.userData.getLoginName();
        title.setText(name);
    }

    private void changeDate() {
        if(actionListenerGo){
            date = ti.setDate(datePicker.getDate());
            pw.displayPlantViewInTable(PlantUtil, date); //refresh table
            af.displayViewInTable(AditionalFuel, date);
            pw.utilPercChange();
            if(!ti.today().toString().equals(date.toString())){
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
    
    
 
}
