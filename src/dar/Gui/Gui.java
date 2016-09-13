/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.Gui.Production.AddProduct;
import dar.Functions.JControlers;
import dar.Functions.TimeWrapper;
import dar.Gui.Production.ProdSettings;
import dar.dbObjects.LaborList;
import dar.dbObjects.LaborView;
import dar.localDB.AFViewDataHandler;
import dar.localDB.LaborViewDataHandler;
import dar.remoteDB.DBWrapper;
import dar.localDB.LocalWraper;
import dar.localDB.NoteViewHandler;
import dar.localDB.PlantViewDataHandler;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

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
    private JControlers c;
    //private JTextField field;
    private ArrayList<LaborList> fullList;
    private ArrayList<LaborList> siteLabourList;
    private ArrayList<LaborView> laborView;
    private String myFilterDefaultText = "Type Name...";
    private final NoteViewHandler nt;
    public boolean SettingsOpened = false;
    public ProdSettings settingsWindow;
    public AddProduct productWindow;

    public Gui(LocalWraper db) {    
        this.ti = new TimeWrapper();
        date = ti.today();    
        initComponents();
        
        //init custom components
        c = new JControlers();                    
        setIcon();       
        ReportBreakdown.setEnabled(false);
        
        this.db = db;
        // initialize components
        pw = new PlantViewDataHandler(this.db, db.userData,PlantUtil,utilPerc,utilProgressBar);
        pw.displayPlantViewInTable(PlantUtil, date);
        
        af = new AFViewDataHandler(this.db, db.userData, AditionalFuel);
        af.displayViewInTable(AditionalFuel, date);
        
        lw = new LaborViewDataHandler(this.db, db.userData, LaborUtil, date);        
        lw.displayViewInTable(LaborUtil, date);            
       
        nt = new NoteViewHandler(this.db, db.userData, MyComents, date);
        nt.displayNotesInTextField(MyComents,date);
        
        // housekeeping
        refreshLists();
        
        
        // add combo box - delete after successful test
      
        
        actionListenerGo = true;
        setName(title);
        pw.utilPercChange();
        
        
        
//        db1 = new DBWrapper(label);
//        Thread t = new Thread(db1);
//        t.start();
        
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
        AddAFuel = new javax.swing.JButton();
        utilProgressBar = new javax.swing.JProgressBar();
        ReportBreakdown = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fUnitNo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fDesc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        fAmount = new javax.swing.JTextField();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        LaborUtil = new javax.swing.JTable();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        laborList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        laborOnSiteList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        MyFilter = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        lName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lFunc = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        ProdUtil = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        ProdUtil1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        MyComents = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
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
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1260, 720));
        setResizable(false);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(jTabbedPane1.getFont().deriveFont(jTabbedPane1.getFont().getStyle() | java.awt.Font.BOLD, jTabbedPane1.getFont().getSize()+3));

        addp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create.png"))); // NOI18N
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
        PlantUtil.setMinimumSize(new java.awt.Dimension(135, 50));
        PlantUtil.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PlantUtilFocusLost(evt);
            }
        });
        PlantUtil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PlantUtilMousePressed(evt);
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
            PlantUtil.getColumnModel().getColumn(5).setHeaderValue("End hours");
            PlantUtil.getColumnModel().getColumn(6).setHeaderValue("Fuel");
            PlantUtil.getColumnModel().getColumn(8).setResizable(false);
            PlantUtil.getColumnModel().getColumn(8).setHeaderValue("Hours");
        }

        AditionalFuel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "allocationID", "utilID", "UnitNo / Rego", "Description", "Fuel Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AditionalFuel.setMinimumSize(new java.awt.Dimension(75, 50));
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Aditional Fuel:");

        RemoveAFuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry.png"))); // NOI18N
        RemoveAFuel.setText("Remove");
        RemoveAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAFuelActionPerformed(evt);
            }
        });

        AddAFuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create.png"))); // NOI18N
        AddAFuel.setText("Add new");
        AddAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAFuelActionPerformed(evt);
            }
        });

        utilProgressBar.setStringPainted(true);

        ReportBreakdown.setForeground(new java.awt.Color(255, 51, 51));
        ReportBreakdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/breakdown24-24.png"))); // NOI18N
        ReportBreakdown.setMnemonic('A');
        ReportBreakdown.setText("Report Breakdown");
        ReportBreakdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportBreakdownActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("UnitNo / Rego");

        jLabel4.setText("Description");

        jLabel5.setText("Fuel Amount");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(fUnitNo)
                    .addComponent(fAmount))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fUnitNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(addp, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(utilPerc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(RemoveAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(AddAFuel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(utilProgressBar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(ReportBreakdown, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

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
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                                .addGap(370, 370, 370)
                                .addComponent(RemoveAFuel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddAFuel)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(246, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(addp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ReportBreakdown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(utilPerc)
                        .addGap(18, 18, 18)
                        .addComponent(utilProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addp)
                        .addComponent(utilPerc)
                        .addComponent(ReportBreakdown))
                    .addComponent(utilProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(RemoveAFuel)
                    .addComponent(AddAFuel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Plant Utilization", jLayeredPane1);

        LaborUtil.setAutoCreateRowSorter(true);
        LaborUtil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LaborUtil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Labour Name", "Labour Function", "Hours", "Status", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
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
            LaborUtil.getColumnModel().getColumn(5).setHeaderValue("Status");
            LaborUtil.getColumnModel().getColumn(6).setPreferredWidth(400);
            LaborUtil.getColumnModel().getColumn(6).setHeaderValue("Notes");
        }

        laborList.setModel(new DefaultListModel());
        jScrollPane3.setViewportView(laborList);

        laborOnSiteList.setModel(new DefaultListModel()
        );
        jScrollPane5.setViewportView(laborOnSiteList);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Go forward.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Go back.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        MyFilter.setText(myFilterDefaultText);
        MyFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                MyFilterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                MyFilterFocusLost(evt);
            }
        });
        MyFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MyFilterActionPerformed(evt);
            }
        });
        MyFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MyFilterKeyReleased(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/People.png"))); // NOI18N
        jButton3.setText("Apply");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Onsite:");

        jLayeredPane3.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(MyFilter, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)))
                    .addComponent(MyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MyFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane3)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Allocate labour", jLayeredPane3);

        jLabel6.setText("Labour name:");

        jLabel7.setText("Function:");

        lFunc.setModel(new DefaultComboBoxModel());

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create.png"))); // NOI18N
        jButton4.setText("Add Labour");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        infoPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/info1600.png"))); // NOI18N

        jLabel9.setText("<html> New labour will be<br> automaticaly allocated<br> to the site");

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(infoPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLayeredPane4.setLayer(lName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(lFunc, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(infoPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addComponent(lName)
                    .addComponent(lFunc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(infoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Create new labour", jLayeredPane4);

        jLayeredPane2.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jTabbedPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 724, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Labour Utilization", jLayeredPane2);

        ProdUtil.setAutoCreateRowSorter(true);
        ProdUtil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ProdUtil.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Product", "Amount", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProdUtil.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ProdUtil.setDoubleBuffered(true);
        ProdUtil.setDragEnabled(true);
        ProdUtil.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ProdUtil.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ProdUtilPropertyChange(evt);
            }
        });
        jScrollPane7.setViewportView(ProdUtil);
        if (ProdUtil.getColumnModel().getColumnCount() > 0) {
            ProdUtil.getColumnModel().getColumn(0).setResizable(false);
            ProdUtil.getColumnModel().getColumn(0).setPreferredWidth(0);
            ProdUtil.getColumnModel().getColumn(1).setResizable(false);
            ProdUtil.getColumnModel().getColumn(1).setPreferredWidth(0);
            ProdUtil.getColumnModel().getColumn(2).setPreferredWidth(50);
            ProdUtil.getColumnModel().getColumn(4).setPreferredWidth(400);
        }

        ProdUtil1.setAutoCreateRowSorter(true);
        ProdUtil1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ProdUtil1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UtilizationID", "AllocationID", "Product", "Amount", "Notes"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProdUtil1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ProdUtil1.setDoubleBuffered(true);
        ProdUtil1.setDragEnabled(true);
        ProdUtil1.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ProdUtil1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ProdUtil1PropertyChange(evt);
            }
        });
        jScrollPane8.setViewportView(ProdUtil1);
        if (ProdUtil1.getColumnModel().getColumnCount() > 0) {
            ProdUtil1.getColumnModel().getColumn(0).setResizable(false);
            ProdUtil1.getColumnModel().getColumn(0).setPreferredWidth(0);
            ProdUtil1.getColumnModel().getColumn(1).setResizable(false);
            ProdUtil1.getColumnModel().getColumn(1).setPreferredWidth(0);
            ProdUtil1.getColumnModel().getColumn(2).setPreferredWidth(50);
            ProdUtil1.getColumnModel().getColumn(4).setPreferredWidth(400);
        }

        jLabel3.setText("Production");

        jLabel11.setText("Used in Production");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create.png"))); // NOI18N
        jButton9.setText("Add Product");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry.png"))); // NOI18N
        jButton10.setText("Remove");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Application.png"))); // NOI18N
        jButton11.setText("Settings");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
        );

        jTabbedPane1.addTab("Production", jLayeredPane5);

        MyComents.setColumns(20);
        MyComents.setRows(5);
        jScrollPane6.setViewportView(MyComents);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/OK.png"))); // NOI18N
        jButton5.setText("Add / Change Summary");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("B");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jButton7.setText("I");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton7);

        jButton8.setText("U");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton8);

        jLayeredPane6.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1215, Short.MAX_VALUE)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daily Summary / Comments", jLayeredPane6);

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

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Lock.png"))); // NOI18N
        jMenuItem2.setText("Logout");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Exit.png"))); // NOI18N
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

        About.setText("Help");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/About.png"))); // NOI18N
        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        About.add(jMenuItem1);

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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            af.addAditionalFuel(fUnitNo,fDesc,fAmount,date);
    }//GEN-LAST:event_AddAFuelActionPerformed

    private void RemoveAFuelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAFuelActionPerformed
        if(actionListenerGo)
            af.removeSelected(date);
    }//GEN-LAST:event_RemoveAFuelActionPerformed

    private void AditionalFuelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_AditionalFuelPropertyChange
        if(actionListenerGo)
            af.editAditionalFuel(date);
    }//GEN-LAST:event_AditionalFuelPropertyChange

    private void LaborUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_LaborUtilPropertyChange
        if(actionListenerGo){
            lw.updateTable(date);
            refreshLists();
        }
    }//GEN-LAST:event_LaborUtilPropertyChange

    private void MyFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MyFilterKeyReleased
        DefaultListModel me = (DefaultListModel) laborList.getModel();
        c.filterModel(me,fullList,MyFilter.getText());
    }//GEN-LAST:event_MyFilterKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        c.moveDataFromListToList(laborList,laborOnSiteList,fullList,siteLabourList);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        c.moveDataFromListToList(laborOnSiteList,laborList,siteLabourList,fullList);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        lw.realocateLabors(date);
        lw.displayViewInTable(LaborUtil, date);
        
        refreshLists();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void MyFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MyFilterFocusGained
        MyFilter.selectAll();
    }//GEN-LAST:event_MyFilterFocusGained

    private void MyFilterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MyFilterFocusLost
        if(MyFilter.getText().isEmpty())
            MyFilter.setText(myFilterDefaultText);
    }//GEN-LAST:event_MyFilterFocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        lw.addNewLabor(lName,lFunc);
        lName.setText("");
        refreshLists();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ProdUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ProdUtilPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdUtilPropertyChange

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        nt.saveDate(MyComents,date);                
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int start = MyComents.getSelectionStart();
        int end = MyComents.getSelectionEnd();
        MyComents.setFont(new Font("Monospace", BOLD, 13));
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void ReportBreakdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportBreakdownActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportBreakdownActionPerformed

    private void PlantUtilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlantUtilMousePressed
        if(PlantUtil.getSelectedRowCount()>0){
            ReportBreakdown.setEnabled(true);
        } else {
            ReportBreakdown.setEnabled(false);
        }
    }//GEN-LAST:event_PlantUtilMousePressed

    private void PlantUtilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PlantUtilFocusLost
        PlantUtil.clearSelection();
        ReportBreakdown.setEnabled(false);
    }//GEN-LAST:event_PlantUtilFocusLost

    private void ProdUtil1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ProdUtil1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ProdUtil1PropertyChange

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.dispose();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setTitle("eDAR - Hi Quality - Login");
                login.setResizable(false);
//                login.setSize(433, 220);
                login.setLocationRelativeTo(null);
                login.setVisible(true);                
            }
        });
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                About about = new About();
                about.setTitle("eDAR - Hi Quality - About");
                about.setResizable(false);
                about.setSize(369, 220);
                about.setLocationRelativeTo(null);
                about.setVisible(true);                
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(productWindow!=null){
            productWindow.dispose();            
        }
        productWindow = new AddProduct(db, date, this);
        productWindow.setLocationRelativeTo(null);
        productWindow.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(settingsWindow!=null){            
            settingsWindow.dispose();
        } 
        settingsWindow = new ProdSettings(db, date,this);
        settingsWindow.setLocationRelativeTo(null);
        settingsWindow.setVisible(true);        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void MyFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MyFilterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JButton AddAFuel;
    private javax.swing.JTable AditionalFuel;
    public javax.swing.JTable LaborUtil;
    private javax.swing.JTextArea MyComents;
    private javax.swing.JTextField MyFilter;
    public javax.swing.JTable PlantUtil;
    public javax.swing.JTable ProdUtil;
    public javax.swing.JTable ProdUtil1;
    private javax.swing.JButton RemoveAFuel;
    private javax.swing.JButton ReportBreakdown;
    private javax.swing.JButton addp;
    private com.toedter.calendar.JDateChooser datePicker;
    public javax.swing.JTextField fAmount;
    private javax.swing.JTextField fDesc;
    private javax.swing.JTextField fUnitNo;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JComboBox<String> lFunc;
    private javax.swing.JTextField lName;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> laborList;
    private javax.swing.JList<String> laborOnSiteList;
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
            refreshLists();
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

    private void refreshLists() {
        //lists housekeeping stuff
        
        lw.createLaborList(date);
        lw.createFunctionsList();
        lw.fillComboBoxWithFunctions(lFunc);
        lw.displayViewInTable(LaborUtil, date);
        lw.getStatusList();
        nt.displayNotesInTextField(MyComents, date);
        siteLabourList = lw.getLaborsOnSiteList();        
        fullList = lw.getLaborList();
        laborView = lw.getLaborView();     
        c.refreshList(fullList, (DefaultListModel) laborList.getModel());
        c.refreshList(siteLabourList, (DefaultListModel) laborOnSiteList.getModel());        
    }
    
    
 
}
