/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui.Sales;

import dar.Functions.tableRenderers.CurrencyTableCellRenderer;
import dar.Functions.FileLogger;
import dar.Functions.JControlers;
import dar.Functions.RXTable;
import dar.Gui.Gui;
import dar.dbObjects.SalesDetailView;
import dar.localDB.LocalWraper;
import dar.localDB.SalesDataHandler;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ldulka
 */
public class SalesGui extends javax.swing.JPanel {

    private Gui gui;
    private LocalWraper db;
    private newSale sale;
    private SalesDataHandler sdh;
    private final JControlers controller;

    /**
     * Creates new form SalesGui
     */
//    public SalesGui(){   
//       this.db = new LocalWraper();
//    }
    
    public SalesGui(){
        controller = new JControlers();
    }
    
    public SalesGui(LocalWraper db, Gui gui) {
        initComponents();
        editComponents();
        this.db = db;
        this.gui = gui;       
        summaryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        controller = new JControlers();
        controller.setTableCellRenderer(summaryTable, 3, new CurrencyTableCellRenderer());                               
        controller.setTableCellRenderer(summaryTable, 4, new CurrencyTableCellRenderer());   
        controller.setTableCellRenderer(salesDetail, 4, new CurrencyTableCellRenderer());                               
        controller.setTableCellRenderer(salesDetail, 5, new CurrencyTableCellRenderer());  
        
        hideColumn(summaryTable, "ProductID");
        hideColumn(salesDetail, "SalesID");
                
        
        sdh = new SalesDataHandler(db, gui.date);
        refreshData(gui.date);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPopUp = new javax.swing.JPopupMenu();
        insertSale = new javax.swing.JMenuItem();
        tablePopUp = new javax.swing.JPopupMenu();
        insertSaleTable = new javax.swing.JMenuItem();
        removeSelected = new javax.swing.JMenuItem();
        clearSelection = new javax.swing.JMenuItem();
        tablePopUp1 = new javax.swing.JPopupMenu();
        insertSaleTable1 = new javax.swing.JMenuItem();
        removeSelected1 = new javax.swing.JMenuItem();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        salesDetail = new RXTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        summaryTable = new RXTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tonesIN1 = new javax.swing.JLabel();
        tonesTotal1 = new javax.swing.JLabel();
        tonesOut1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        tonesIN = new javax.swing.JLabel();
        tonesOut = new javax.swing.JLabel();
        tonesTotal = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        tonesIN2 = new javax.swing.JLabel();
        tonesTotal2 = new javax.swing.JLabel();
        tonesOut2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        tonesIN3 = new javax.swing.JLabel();
        tonesTotal3 = new javax.swing.JLabel();
        tonesOut3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        EPA = new javax.swing.JLabel();

        insertSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        insertSale.setText("Insert Sale");
        insertSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertSaleActionPerformed(evt);
            }
        });
        mainPopUp.add(insertSale);

        insertSaleTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        insertSaleTable.setText("Insert sale");
        insertSaleTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertSaleTableActionPerformed(evt);
            }
        });
        tablePopUp.add(insertSaleTable);

        removeSelected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        removeSelected.setText("Remove selected");
        removeSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelectedActionPerformed(evt);
            }
        });
        tablePopUp.add(removeSelected);

        clearSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/edit16.png"))); // NOI18N
        clearSelection.setText("Clear selection");
        clearSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSelectionActionPerformed(evt);
            }
        });
        tablePopUp.add(clearSelection);

        insertSaleTable1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        insertSaleTable1.setText("Insert sale");
        insertSaleTable1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertSaleTable1ActionPerformed(evt);
            }
        });
        tablePopUp1.add(insertSaleTable1);

        removeSelected1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        removeSelected1.setText("Remove selected");
        removeSelected1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeSelected1ActionPerformed(evt);
            }
        });
        tablePopUp1.add(removeSelected1);

        jLayeredPane7.setBackground(new java.awt.Color(200, 221, 242));
        jLayeredPane7.setOpaque(true);
        jLayeredPane7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLayeredPane7MouseReleased(evt);
            }
        });

        salesDetail.setAutoCreateRowSorter(true);
        salesDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SalesID", "Product Name", "Direction", "Tonnage", "Price inc GST", "Price ex GST"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                salesDetailMouseReleased(evt);
            }
        });
        jScrollPane9.setViewportView(salesDetail);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        jButton7.setMnemonic('q');
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("from AWS");
        jButton8.setEnabled(false);

        summaryTable.setAutoCreateRowSorter(true);
        summaryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductID", "Product Name", "Tonnage", "Price inc GST", "Price ex GST"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        summaryTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                summaryTableMouseReleased(evt);
            }
        });
        jScrollPane11.setViewportView(summaryTable);

        jLabel12.setText("Sales detail");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setText("Summary");

        tonesIN1.setText("IN");

        tonesTotal1.setText("TOTAL");

        tonesOut1.setText("OUT");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(tonesIN1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tonesOut1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(tonesTotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tonesTotal1)
                    .addComponent(tonesOut1)
                    .addComponent(tonesIN1))
                .addContainerGap())
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                    .addContainerGap(28, Short.MAX_VALUE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)))
        );

        jLabel17.setText("Tones:");

        tonesIN.setText("0");

        tonesOut.setText("0");

        tonesTotal.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tonesIN, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tonesOut, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tonesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tonesTotal)
                    .addComponent(tonesOut)
                    .addComponent(tonesIN)
                    .addComponent(jLabel17))
                .addContainerGap())
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(28, Short.MAX_VALUE)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)))
        );

        jLabel18.setText("$ ex GST");

        tonesIN2.setText("$0");

        tonesTotal2.setText("$0");

        tonesOut2.setText("$0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tonesIN2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tonesOut2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tonesTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 40, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tonesTotal2)
                    .addComponent(tonesOut2)
                    .addComponent(tonesIN2))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                    .addContainerGap(28, Short.MAX_VALUE)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)))
        );

        jLabel19.setText("$ inc GST");

        tonesIN3.setText("$0");

        tonesTotal3.setText("$0");

        tonesOut3.setText("$0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tonesIN3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tonesOut3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tonesTotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tonesTotal3)
                    .addComponent(tonesOut3)
                    .addComponent(tonesIN3))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("EPA LEVY:");

        EPA.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(EPA)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(EPA))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane7.setLayer(jScrollPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jScrollPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane7Layout = new javax.swing.GroupLayout(jLayeredPane7);
        jLayeredPane7.setLayout(jLayeredPane7Layout);
        jLayeredPane7Layout.setHorizontalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 1101, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6))
                    .addComponent(jScrollPane9)
                    .addGroup(jLayeredPane7Layout.createSequentialGroup()
                        .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane7Layout.setVerticalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane7)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane7)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLayeredPane7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane7MouseReleased
        if(evt.isPopupTrigger()){
            mainPopUp.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jLayeredPane7MouseReleased

    private void summaryTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_summaryTableMouseReleased
        if(evt.isPopupTrigger()){
            tablePopUp.show(summaryTable,evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_summaryTableMouseReleased

    private void salesDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salesDetailMouseReleased
        if(evt.isPopupTrigger()){
            tablePopUp1.show(salesDetail,evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_salesDetailMouseReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        insertSale();       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void clearSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSelectionActionPerformed
        refreshData(gui.date);
    }//GEN-LAST:event_clearSelectionActionPerformed

    private void insertSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertSaleActionPerformed
        insertSale();
    }//GEN-LAST:event_insertSaleActionPerformed

    private void insertSaleTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertSaleTableActionPerformed
        insertSale();
    }//GEN-LAST:event_insertSaleTableActionPerformed

    private void insertSaleTable1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertSaleTable1ActionPerformed
        insertSale();
    }//GEN-LAST:event_insertSaleTable1ActionPerformed

    private void removeSelected1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelected1ActionPerformed
        removeSelectedItems();
    }//GEN-LAST:event_removeSelected1ActionPerformed

    private void removeSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeSelectedActionPerformed
        removeSelectedGroups();
    }//GEN-LAST:event_removeSelectedActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(salesDetail.getSelectedRowCount()>0){
            removeSelectedItems();
        } else {
            removeSelectedGroups();
        }
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EPA;
    private javax.swing.JMenuItem clearSelection;
    private javax.swing.JMenuItem insertSale;
    private javax.swing.JMenuItem insertSaleTable;
    private javax.swing.JMenuItem insertSaleTable1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JPopupMenu mainPopUp;
    private javax.swing.JMenuItem removeSelected;
    private javax.swing.JMenuItem removeSelected1;
    private javax.swing.JTable salesDetail;
    private javax.swing.JTable summaryTable;
    private javax.swing.JPopupMenu tablePopUp;
    private javax.swing.JPopupMenu tablePopUp1;
    private javax.swing.JLabel tonesIN;
    private javax.swing.JLabel tonesIN1;
    private javax.swing.JLabel tonesIN2;
    private javax.swing.JLabel tonesIN3;
    private javax.swing.JLabel tonesOut;
    private javax.swing.JLabel tonesOut1;
    private javax.swing.JLabel tonesOut2;
    private javax.swing.JLabel tonesOut3;
    private javax.swing.JLabel tonesTotal;
    private javax.swing.JLabel tonesTotal1;
    private javax.swing.JLabel tonesTotal2;
    private javax.swing.JLabel tonesTotal3;
    // End of variables declaration//GEN-END:variables

    private void hideColumn(JTable table,String columnName) {
        table.removeColumn(table.getColumn(columnName));
    }
    
    public void refreshData(Date date){        
        sdh.displaySummaryInTable(summaryTable,date);
        sdh.displayDetailInTable(salesDetail, gui.date, -1);
        countSummaryTable(date);
    }

    private void countSummaryTable(Date date) {
        double tonageIn = 0;
        double tonageOut = 0;
        double tonageTot = 0;
        double exGSTIn = 0;
        double exGSTOut = 0;
        double exGSTTot = 0;
        double inGSTIn = 0;
        double inGSTOut = 0;
        double inGSTTot = 0;
        double lEPA = 0;
        for (SalesDetailView view : sdh.salesDetailList) {
            if(view.getDirection().equals("IN")){
                tonageIn += view.getTonage();
                exGSTIn += view.getPriceExtGST();
                inGSTIn += view.getPriceIncGST();
                lEPA += view.getEPA()*view.getTonage();
            } else if(view.getDirection().equals("OUT")){
                tonageOut += view.getTonage();
                exGSTOut += view.getPriceExtGST();
                inGSTOut += view.getPriceIncGST();
            }
        }
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat numberFormat = NumberFormat.getInstance();
        
        tonesIN.setText(numberFormat.format(tonageIn));
        tonesOut.setText(numberFormat.format(tonageOut));
        tonesTotal.setText(numberFormat.format(tonageOut+tonageIn));
        tonesIN2.setText(currencyFormat.format(exGSTIn));
        tonesOut2.setText(currencyFormat.format(exGSTOut));
        tonesTotal2.setText(currencyFormat.format(exGSTOut+exGSTIn));
        tonesIN3.setText(currencyFormat.format(inGSTIn));
        tonesOut3.setText(currencyFormat.format(inGSTOut));
        tonesTotal3.setText(currencyFormat.format(inGSTOut+inGSTIn));  
        EPA.setText(currencyFormat.format(lEPA));
    }

    private void insertSale() {
        if(!sale.running){
            sale = new newSale(db,gui.date,this);
            sale.setLocationRelativeTo(null);
            sale.setResizable(false);
            sale.setVisible(true);
        } else {
            sale.toFront();
        }        
    }

    private void removeSelectedItems() {
        if(salesDetail.getSelectedRowCount()>0){
            DefaultTableModel model = (DefaultTableModel) salesDetail.getModel();
            for (int row : salesDetail.getSelectedRows()) {
                int modelRow = salesDetail.convertRowIndexToModel(row);
                int SalesID = (int) model.getValueAt(modelRow, 0);
                //preform deletion
                System.out.println("deleting item ID "+SalesID);
                db.dbDelete("Sales", new Object[][]{
                    {"ID"},{"="},{SalesID},{}
                }, "ID");
                System.out.println("Deleted");
            }
            refreshData(gui.date);
        } else {
            JOptionPane.showMessageDialog(null, "No rows selected", "error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removeSelectedGroups() {        
        if(summaryTable.getSelectedRowCount()>0){
            DefaultTableModel model = (DefaultTableModel) summaryTable.getModel();
            for (int row : summaryTable.getSelectedRows()) {
                int modelRow = summaryTable.convertRowIndexToModel(row);
                int productID = (int) model.getValueAt(modelRow, 0);
                ArrayList<Integer> delIDs = getIdsToDeleteByProductId(productID);
                for (Integer delID : delIDs) {
                    db.dbDelete("Sales", new Object[][]{
                        {"ID"},{"="},{delID},{}
                    },"ID");
                }               
            }
            refreshData(gui.date);
        } else {
            JOptionPane.showMessageDialog(null, "No rows selected", "error", JOptionPane.ERROR_MESSAGE);
        }
              
    }

    private ArrayList<Integer> getIdsToDeleteByProductId(int productID) {
        ArrayList<Integer> list = new ArrayList<>();
        ResultSet rs = db.dbSelect(new Object[]{"ID"}, "Sales", new Object[][]{
                    {"ProductID","SiteID","DateFor"},{"=","=","="},{productID,db.userData.getSiteID(),gui.date},{"AND","AND","AND"}
                });
        try {
            while (rs.next()) {
                list.add(rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.toString());
        }
        return list;
    }

    private void editComponents() {
        summaryTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(!lse.getValueIsAdjusting()){
                    if(summaryTable.getSelectedRowCount()>0){
                        int rowNo = summaryTable.convertRowIndexToModel(summaryTable.getSelectedRow());
                        DefaultTableModel model = (DefaultTableModel) summaryTable.getModel();
                        int productID = (int) model.getValueAt(rowNo, 0);
                        //System.out.println(productID);
                        sdh.displayDetailInTable(salesDetail, gui.date, productID);
                    }                    
                }
            }
        });
    }
}
