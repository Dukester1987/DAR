/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.Functions.FileLogger;
import dar.Functions.Functions;
import dar.Gui.CloserData.Closer;
import dar.Gui.Production.AddProduct;
import dar.Functions.JControlers;
import dar.Functions.RXTable;
import dar.Functions.TimeWrapper;
import dar.Functions.tableRenderers.NumberTableCellRenderer;
import dar.Gui.About.About_popup;
import dar.Gui.Labour.LabourEdit;
import dar.Gui.Production.ProdSettings;
import dar.Gui.Stock.StockGui;
import dar.dbObjects.LaborList;
import dar.dbObjects.LaborView;
import dar.dbObjects.SiteList;
import dar.localDB.AFViewDataHandler;
import dar.localDB.LaborViewDataHandler;
import dar.remoteDB.DBWrapper;
import dar.localDB.LocalWraper;
import dar.localDB.NoteViewHandler;
import dar.localDB.PlantViewDataHandler;
import dar.localDB.ProductViewHandler;
import dar.localDB.SiteListHandler;
import java.awt.Color;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author ldulka
 */
public class Gui extends javax.swing.JFrame {
    private LocalWraper db;
    private DBWrapper db1;
    private PlantViewDataHandler pw;
    public Date date;
    public boolean actionListenerGo = false;  
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
    private final ProductViewHandler ph;
    private Thread t;
    private JControlers controller;
    private StockGui stockGui;
    private SiteListHandler slh;
    public static boolean isAnyChangesApplicable = false;

    public Gui(LocalWraper db) {    
        Version v = new Version();
        this.ti = new TimeWrapper();
        this.date = ti.today();    
        this.db = db; 
        db.userData.setDate(date);
        isAnyChangesApplicable = checkChanges();
        initComponents();
        editComponents();
        
        //init custom components
        c = new JControlers();                    
        setIcon();       
        ReportBreakdown.setEnabled(false);
                
        
        // initialize components
        pw = new PlantViewDataHandler(this.db, db.userData,PlantUtil,utilPerc,utilProgressBar);
        af = new AFViewDataHandler(this.db, db.userData, AditionalFuel);
        lw = new LaborViewDataHandler(this.db, db.userData, LaborUtil, date);        
        nt = new NoteViewHandler(this.db, db.userData, MyComents, date);
        ph = new ProductViewHandler(this.db, db.userData, date);
        
        ph.hideID(ProdUtilization);
        ph.hideID(UsedInProduction);
        //ph.displayUtilizationInTable(ProdUtilization, 3,date);
        //ph.displayUtilizationInTable(UsedInProduction, 4,date);
        
        // housekeeping
        refreshLists();
        
        
        // add combo box - delete after successful test
      
        
        actionListenerGo = true;
        setName(title);
        pw.utilPercChange();        
        
        db1 = new DBWrapper(label,db,this);
        t = new Thread(db1);
        t.start();
        
        ButtonController bc = new ButtonController(this);
        Thread t1 = new Thread(bc);
        t1.start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProdPopup = new javax.swing.JPopupMenu();
        AddNew = new javax.swing.JMenuItem();
        RemoveSelected = new javax.swing.JMenuItem();
        RemoveSelection = new javax.swing.JMenuItem();
        UsedInProdPopUP = new javax.swing.JPopupMenu();
        AddNew1 = new javax.swing.JMenuItem();
        RemoveSelected1 = new javax.swing.JMenuItem();
        RemoveSelection1 = new javax.swing.JMenuItem();
        LaborChangeMenu = new javax.swing.JPopupMenu();
        editLabour = new javax.swing.JMenuItem();
        PlantPopUp = new javax.swing.JPopupMenu();
        pRemoveSelected = new javax.swing.JMenuItem();
        title = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        Sales = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        addp = new javax.swing.JButton();
        utilPerc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PlantUtil = new RXTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        AditionalFuel = new RXTable();
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
        LaborUtil = new RXTable();
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
        UsedInProduction = new RXTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        ProdUtilization = new RXTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        salesGui = new dar.Gui.Sales.SalesGui(this.db,this);
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        MyComents = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        datePicker = new com.toedter.calendar.JDateChooser();
        powerTitle = new javax.swing.JComboBox<>();
        reportConfirm = new javax.swing.JButton();
        ConfirmationStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        About = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        AddNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        AddNew.setText("Add New");
        AddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewActionPerformed(evt);
            }
        });
        ProdPopup.add(AddNew);

        RemoveSelected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        RemoveSelected.setText("Remove selected");
        RemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelectedActionPerformed(evt);
            }
        });
        ProdPopup.add(RemoveSelected);

        RemoveSelection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/edit16.png"))); // NOI18N
        RemoveSelection.setText("Remove selection");
        RemoveSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelectionActionPerformed(evt);
            }
        });
        ProdPopup.add(RemoveSelection);

        AddNew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        AddNew1.setText("Add New");
        AddNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNew1ActionPerformed(evt);
            }
        });
        UsedInProdPopUP.add(AddNew1);

        RemoveSelected1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        RemoveSelected1.setText("Remove selected");
        RemoveSelected1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelected1ActionPerformed(evt);
            }
        });
        UsedInProdPopUP.add(RemoveSelected1);

        RemoveSelection1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/edit16.png"))); // NOI18N
        RemoveSelection1.setText("Remove selection");
        RemoveSelection1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelection1ActionPerformed(evt);
            }
        });
        UsedInProdPopUP.add(RemoveSelection1);

        editLabour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/edit16.png"))); // NOI18N
        editLabour.setText("Edit Labour");
        editLabour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editLabourActionPerformed(evt);
            }
        });
        LaborChangeMenu.add(editLabour);

        pRemoveSelected.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        pRemoveSelected.setText("Remove Selected");
        pRemoveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pRemoveSelectedActionPerformed(evt);
            }
        });
        PlantPopUp.add(pRemoveSelected);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle(String.format("DAR v%s - Hi Quality Group",Version.version));
        setMinimumSize(new java.awt.Dimension(1128, 682));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        title.setText("Menangle");

        label.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Sync.png"))); // NOI18N
        label.setText("Trying to connect...");

        Sales.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        Sales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Sales.setFont(Sales.getFont().deriveFont(Sales.getFont().getStyle() | java.awt.Font.BOLD, Sales.getFont().getSize()+3));

        addp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        addp.setMnemonic('A');
        addp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpActionPerformed(evt);
            }
        });

        utilPerc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        utilPerc.setForeground(new java.awt.Color(255, 51, 51));
        utilPerc.setText("Utilisation");

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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PlantUtilMouseReleased(evt);
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

        RemoveAFuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        RemoveAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAFuelActionPerformed(evt);
            }
        });

        AddAFuel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        AddAFuel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAFuelActionPerformed(evt);
            }
        });

        utilProgressBar.setStringPainted(true);

        ReportBreakdown.setForeground(new java.awt.Color(255, 51, 51));
        ReportBreakdown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/breakdown16.png"))); // NOI18N
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
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                                .addGap(422, 422, 422)
                                .addComponent(RemoveAFuel))
                            .addComponent(jScrollPane2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddAFuel)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(186, Short.MAX_VALUE))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(addp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReportBreakdown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(utilPerc)
                        .addGap(18, 18, 18)
                        .addComponent(utilProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane1)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ReportBreakdown)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addp)
                        .addComponent(utilPerc))
                    .addComponent(utilProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(RemoveAFuel)
                    .addComponent(AddAFuel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Sales.addTab("Plant Utilisation", jLayeredPane1);

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
        LaborUtil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LaborUtilMouseReleased(evt);
            }
        });
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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
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
                        .addGap(0, 136, Short.MAX_VALUE))
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
                .addContainerGap(105, Short.MAX_VALUE))
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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 592, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2)
                .addGap(29, 29, 29))
        );

        Sales.addTab("Labour Utilisation", jLayeredPane2);

        UsedInProduction.setAutoCreateRowSorter(true);
        UsedInProduction.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        UsedInProduction.setModel(new javax.swing.table.DefaultTableModel(
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
        UsedInProduction.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        UsedInProduction.setDoubleBuffered(true);
        UsedInProduction.setDragEnabled(true);
        UsedInProduction.setIntercellSpacing(new java.awt.Dimension(2, 2));
        UsedInProduction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                UsedInProductionMouseReleased(evt);
            }
        });
        jScrollPane7.setViewportView(UsedInProduction);
        if (UsedInProduction.getColumnModel().getColumnCount() > 0) {
            UsedInProduction.getColumnModel().getColumn(0).setResizable(false);
            UsedInProduction.getColumnModel().getColumn(0).setPreferredWidth(0);
            UsedInProduction.getColumnModel().getColumn(1).setResizable(false);
            UsedInProduction.getColumnModel().getColumn(1).setPreferredWidth(0);
            UsedInProduction.getColumnModel().getColumn(2).setPreferredWidth(50);
            UsedInProduction.getColumnModel().getColumn(4).setPreferredWidth(400);
        }

        ProdUtilization.setAutoCreateRowSorter(true);
        ProdUtilization.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ProdUtilization.setModel(new javax.swing.table.DefaultTableModel(
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
        ProdUtilization.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        ProdUtilization.setDoubleBuffered(true);
        ProdUtilization.setDragEnabled(true);
        ProdUtilization.setIntercellSpacing(new java.awt.Dimension(2, 2));
        ProdUtilization.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ProdUtilizationMouseReleased(evt);
            }
        });
        jScrollPane8.setViewportView(ProdUtilization);
        if (ProdUtilization.getColumnModel().getColumnCount() > 0) {
            ProdUtilization.getColumnModel().getColumn(0).setResizable(false);
            ProdUtilization.getColumnModel().getColumn(0).setPreferredWidth(0);
            ProdUtilization.getColumnModel().getColumn(1).setResizable(false);
            ProdUtilization.getColumnModel().getColumn(1).setPreferredWidth(0);
            ProdUtilization.getColumnModel().getColumn(2).setPreferredWidth(50);
            ProdUtilization.getColumnModel().getColumn(4).setPreferredWidth(400);
        }

        jLabel3.setText("Production");

        jLabel11.setText("Used in Production");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Application.png"))); // NOI18N
        jButton11.setText("Settings");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Create16.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/No-entry16.png"))); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jButton11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12)
                        .addComponent(jButton13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(jButton11)
                .addGap(38, 38, 38))
        );

        Sales.addTab("Production", jLayeredPane5);
        Sales.addTab("Sales", salesGui);

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

        jLayeredPane6.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        Sales.addTab("Daily Summary / Comments", jLayeredPane6);

        datePicker.setComponentPopupMenu(ProdPopup);
        datePicker.setDate(date);
        datePicker.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datePicker.setMaxSelectableDate(ti.today());
        datePicker.setMinSelectableDate(ti.firstDate());
        datePicker.setName("dateFor"); // NOI18N
        datePicker.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datePickerPropertyChange(evt);
            }
        });

        powerTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        powerTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Site 1", "Site 2" }));
        powerTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                powerTitleActionPerformed(evt);
            }
        });

        reportConfirm.setText("CONFIRM");
        reportConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportConfirmActionPerformed(evt);
            }
        });

        ConfirmationStatus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ConfirmationStatus.setForeground(new java.awt.Color(255, 0, 0));
        ConfirmationStatus.setText("jLabel12");

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

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Wrench.png"))); // NOI18N
        jMenuItem3.setText("Settings");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/Shopping cart.png"))); // NOI18N
        jMenuItem5.setText("Stock");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
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

        jMenu2.setText("System");

        jMenuItem8.setText("Application log");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(powerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104)
                        .addComponent(reportConfirm)
                        .addGap(18, 18, 18)
                        .addComponent(ConfirmationStatus))
                    .addComponent(Sales))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(powerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reportConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ConfirmationStatus))
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Sales, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        quitApp();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void datePickerPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datePickerPropertyChange
        changeDate();
    }//GEN-LAST:event_datePickerPropertyChange

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
        About_popup ag = new About_popup();
        ag.setLocationRelativeTo(null);
        ag.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void RemoveSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveSelectionActionPerformed
        ProdUtilization.clearSelection();
        UsedInProduction.clearSelection();
    }//GEN-LAST:event_RemoveSelectionActionPerformed

    private void AddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewActionPerformed
        addNewProduction(3);
    }//GEN-LAST:event_AddNewActionPerformed

    private void AddNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNew1ActionPerformed
        addNewProduction(4);
    }//GEN-LAST:event_AddNew1ActionPerformed

    private void RemoveSelection1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveSelection1ActionPerformed
        ProdUtilization.clearSelection();
        UsedInProduction.clearSelection();
    }//GEN-LAST:event_RemoveSelection1ActionPerformed

    private void RemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveSelectedActionPerformed
        prodRemoveSelected(3);
    }//GEN-LAST:event_RemoveSelectedActionPerformed

    private void RemoveSelected1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveSelected1ActionPerformed
        prodRemoveSelected(3);
    }//GEN-LAST:event_RemoveSelected1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if(settingsWindow!=null){            
            settingsWindow.dispose();
        } 
        settingsWindow = new ProdSettings(db, date,this);
        settingsWindow.setLocationRelativeTo(null);
        settingsWindow.setVisible(true);   
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void editLabourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editLabourActionPerformed
       if(LaborUtil.getSelectedRow()>-1){
           int selRow = LaborUtil.convertRowIndexToModel(LaborUtil.getSelectedRow());           
           DefaultTableModel model = (DefaultTableModel) LaborUtil.getModel();
           
           int laborAllocationId = (int) model.getValueAt(selRow, 1);
           //System.out.println(model.getValueAt(selRow, 2));
           JFrame ledit = new LabourEdit(lw,(String) model.getValueAt(selRow, 2),LaborUtil,date);
           ledit.setLocationRelativeTo(LaborChangeMenu);
           ledit.setResizable(false);
           ledit.setVisible(true);
       }
    }//GEN-LAST:event_editLabourActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        quitApp();
    }//GEN-LAST:event_formWindowClosing

    private void pRemoveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pRemoveSelectedActionPerformed
        int option = JOptionPane.showConfirmDialog(null, "Selected plants will be removed for selected date.\nAll data inputed for next days will be lost\nProceed?","Info",JOptionPane.INFORMATION_MESSAGE);
        if(option==0)pw.deleteSelectedRows(date,PlantUtil);
    }//GEN-LAST:event_pRemoveSelectedActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        nt.saveDate(MyComents,date);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        prodRemoveSelected(2);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        addNewProduction(4);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(settingsWindow!=null){
            settingsWindow.dispose();
        }
        settingsWindow = new ProdSettings(db, date,this);
        settingsWindow.setLocationRelativeTo(null);
        settingsWindow.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        prodRemoveSelected(1);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        addNewProduction(3);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void ProdUtilizationMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdUtilizationMouseReleased
        if(evt.isPopupTrigger()){
            ProdPopup.show(ProdUtilization, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_ProdUtilizationMouseReleased

    private void UsedInProductionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UsedInProductionMouseReleased
        if(evt.isPopupTrigger()){
            ProdPopup.show(UsedInProduction, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_UsedInProductionMouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        lw.addNewLabor(lName,lFunc);
        lName.setText("");
        refreshLists();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        lw.realocateLabors(date);
        lw.displayViewInTable(LaborUtil, date);

        refreshLists();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void MyFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MyFilterKeyReleased
        DefaultListModel me = (DefaultListModel) laborList.getModel();
        c.filterModel(me,fullList,MyFilter.getText());
    }//GEN-LAST:event_MyFilterKeyReleased

    private void MyFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MyFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MyFilterActionPerformed

    private void MyFilterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MyFilterFocusLost
        if(MyFilter.getText().isEmpty())
        MyFilter.setText(myFilterDefaultText);
    }//GEN-LAST:event_MyFilterFocusLost

    private void MyFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MyFilterFocusGained
        MyFilter.selectAll();
    }//GEN-LAST:event_MyFilterFocusGained

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        c.moveDataFromListToList(laborOnSiteList,laborList,siteLabourList,fullList);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        c.moveDataFromListToList(laborList,laborOnSiteList,fullList,siteLabourList);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void LaborUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_LaborUtilPropertyChange
        if(actionListenerGo){
            lw.updateTable(date);
            //refreshLists();
        }
    }//GEN-LAST:event_LaborUtilPropertyChange

    private void LaborUtilMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LaborUtilMouseReleased
        if(evt.isPopupTrigger()){
            LaborChangeMenu.show(LaborUtil, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_LaborUtilMouseReleased

    private void ReportBreakdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportBreakdownActionPerformed
        JOptionPane.showMessageDialog(null, "Hahaaa stihnul sem to");
    }//GEN-LAST:event_ReportBreakdownActionPerformed

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

    private void PlantUtilPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_PlantUtilPropertyChange
        //        if(actionListenerGo){
            //            pw.updateTable(date);
            //        }
    }//GEN-LAST:event_PlantUtilPropertyChange

    private void PlantUtilMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlantUtilMouseReleased
        if(evt.isPopupTrigger()){
            PlantPopUp.show(PlantUtil, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_PlantUtilMouseReleased

    private void PlantUtilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlantUtilMousePressed
        if(PlantUtil.getSelectedRowCount()>0){
            ReportBreakdown.setEnabled(true);
        } else {
            ReportBreakdown.setEnabled(false);
        }
    }//GEN-LAST:event_PlantUtilMousePressed

    private void PlantUtilFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PlantUtilFocusLost

    }//GEN-LAST:event_PlantUtilFocusLost

    private void addpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpActionPerformed
        if(actionListenerGo)
        pw.addPlant(date);
    }//GEN-LAST:event_addpActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if(!stockGui.isOpen){
            stockGui = new StockGui(db);
            stockGui.setLocationRelativeTo(null);
            stockGui.setVisible(true);
        } else {
            stockGui.toFront();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void powerTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_powerTitleActionPerformed
        //System.out.println("changing site");
        if(actionListenerGo){            
            DefaultComboBoxModel model = (DefaultComboBoxModel) powerTitle.getModel();
            SiteList site = (SiteList) model.getSelectedItem();
            db.userData.setSiteID(site.getSiteID());
            //System.out.println("choosed site is:" + site.getSiteName());
            refreshLists();
            //System.out.println("success / refreshing lists");
        }
    }//GEN-LAST:event_powerTitleActionPerformed

    private void reportConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportConfirmActionPerformed
        ConfirmDay(db.userData.getDate(), db.userData.getSiteID());
    }//GEN-LAST:event_reportConfirmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu About;
    private javax.swing.JButton AddAFuel;
    private javax.swing.JMenuItem AddNew;
    private javax.swing.JMenuItem AddNew1;
    private javax.swing.JTable AditionalFuel;
    private javax.swing.JLabel ConfirmationStatus;
    private javax.swing.JPopupMenu LaborChangeMenu;
    public javax.swing.JTable LaborUtil;
    private javax.swing.JTextArea MyComents;
    private javax.swing.JTextField MyFilter;
    private javax.swing.JPopupMenu PlantPopUp;
    public javax.swing.JTable PlantUtil;
    private javax.swing.JPopupMenu ProdPopup;
    public javax.swing.JTable ProdUtilization;
    private javax.swing.JButton RemoveAFuel;
    private javax.swing.JMenuItem RemoveSelected;
    private javax.swing.JMenuItem RemoveSelected1;
    private javax.swing.JMenuItem RemoveSelection;
    private javax.swing.JMenuItem RemoveSelection1;
    private javax.swing.JButton ReportBreakdown;
    private javax.swing.JTabbedPane Sales;
    private javax.swing.JPopupMenu UsedInProdPopUP;
    public javax.swing.JTable UsedInProduction;
    private javax.swing.JButton addp;
    private com.toedter.calendar.JDateChooser datePicker;
    private javax.swing.JMenuItem editLabour;
    public javax.swing.JTextField fAmount;
    private javax.swing.JTextField fDesc;
    private javax.swing.JTextField fUnitNo;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem8;
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
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JComboBox<String> lFunc;
    private javax.swing.JTextField lName;
    private javax.swing.JLabel label;
    private javax.swing.JList<String> laborList;
    private javax.swing.JList<String> laborOnSiteList;
    private javax.swing.JMenuItem pRemoveSelected;
    private javax.swing.JComboBox<String> powerTitle;
    private javax.swing.JButton reportConfirm;
    private dar.Gui.Sales.SalesGui salesGui;
    private javax.swing.JLabel title;
    private javax.swing.JLabel utilPerc;
    private javax.swing.JProgressBar utilProgressBar;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        GuiIcon icon = new GuiIcon(this);
    }

    private void setName(JLabel title) {
        String name = db.userData.getSiteName(db);
        title.setText(name);
    }

    private void changeDate() {
        if(actionListenerGo){
            date = ti.setDate(datePicker.getDate());
            db.userData.setDate(date);
            //pw.displayPlantViewInTable(PlantUtil, date); //refresh table
            //af.displayViewInTable(AditionalFuel, date);
            //pw.utilPercChange();            
            refreshLists();
            if(!ti.today().toString().equals(date.toString())){
                //addp.setEnabled(false);
                AddAFuel.setEnabled(false);
                RemoveAFuel.setEnabled(false);
            } else {
                addp.setEnabled(true);
                AddAFuel.setEnabled(true);
                RemoveAFuel.setEnabled(true);
            }
        }        
    }

    public void refreshLists() {
        //lists housekeeping stuff
        confirmationControl();
        System.out.println(db.userData.getSiteName(db)+" "+db.userData.getSiteID());
        actionListenerGo = false;
        lw.createLaborList(date);
        lw.createFunctionsList();
        lw.fillComboBoxWithFunctions(lFunc);
        lw.displayViewInTable(LaborUtil, date);
        lw.getStatusList();
        pw.displayPlantViewInTable(PlantUtil, date); 
        pw.utilPercChange();
        af.displayViewInTable(AditionalFuel, date);        
        ph.displayUtilizationInTable(ProdUtilization, 3,date);
        ph.displayUtilizationInTable(UsedInProduction, 4,date);
        nt.displayNotesInTextField(MyComents, date);
        siteLabourList = lw.getLaborsOnSiteList();        
        fullList = lw.getLaborList();
        laborView = lw.getLaborView();     
        c.refreshList(fullList, (DefaultListModel) laborList.getModel());
        c.refreshList(siteLabourList, (DefaultListModel) laborOnSiteList.getModel());   
        salesGui.refreshData(date);
        actionListenerGo = true;
    }

    private void addNewProduction(int prodType) {
        if(productWindow!=null){
            productWindow.dispose();            
        }
        productWindow = new AddProduct(db, date, this, prodType);
        productWindow.setLocationRelativeTo(null);
        productWindow.setVisible(true);        
    }

    private void prodRemoveSelected(int where) {
        String msg = "";
        boolean correct = false;
        switch(where){
            case 1:
                correct = delUtilFromTable(ProdUtilization);                
                break;
            case 2:
                correct = delUtilFromTable(UsedInProduction);
                break;   
            default: 
                boolean pu = delUtilFromTable(ProdUtilization);
                boolean uip = delUtilFromTable(UsedInProduction);
                correct = (pu || uip)?true:false;
                break;
        }
        if(!correct){
            JOptionPane.showMessageDialog(null,"No rows selected!", "Error",JOptionPane.ERROR_MESSAGE);
        } else {            
         refreshLists();
        }
              
    }

    private boolean delUtilFromTable(JTable table) {       
        if(table.getSelectedRowCount()!=0){
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            for (int prdU : table.getSelectedRows()) {
                int utilID = (int) m.getValueAt(table.convertRowIndexToView(prdU), 0);
                //preform delete
                db.dbDelete("ProductUtilization", new Object[][]{{"ID"},{"="},{utilID},{}}, "ID");     
            }   
            return true;            
        } else {
            return false;
        }
            
    }

    private void editComponents() {        
        Gui g = this;
        confirmationControl();
        Functions.setFrameMinimumSize(this);
        setTitle();
        
        setRenderers();
        //productUtilization
        ProdUtilization.getModel().addTableModelListener( new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                if(actionListenerGo){
                    ph.updateProduct((DefaultTableModel) ProdUtilization.getModel(),tme.getFirstRow());
                    //refreshLists();
                }
            }
        });
        
        PlantUtil.getRowSorter().addRowSorterListener(new RowSorterListener() {
            @Override
            public void sorterChanged(RowSorterEvent rse) {
                pw.utilPercChange();     
//                for (LaborList laborList1 : fullList) {
//                    laborList1.getFunctionID();
//                }
            }
        });
        
        PlantUtil.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                if(actionListenerGo){                    
                    pw.updateTable(date,g);                    
                    //System.out.println("changed now");
                }                
            }
        });
        
        //Used in Production
        UsedInProduction.getModel().addTableModelListener( new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                if(actionListenerGo){
                    ph.updateProduct((DefaultTableModel) UsedInProduction.getModel(),tme.getFirstRow());
                    //refreshLists();
                }
            }
        });  
        
//        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//            public void run() {
//                System.out.println("closing app");
//                try {
//                    this.finalize();
//                } catch (Throwable ex) {
//                    ex.printStackTrace();
//                    new FileLogger(ex.toString());
//                }
//            }
//        }));        
        
    }

    private void quitApp() {
        System.out.println("closing app!");
        label.setText("Saving data before close please wait...");
        Closer c = new Closer(db1,t);
        this.dispose();              
    }

    private void setRenderers() {
        controller = new JControlers();        
        controller.setTableCellRenderer(ProdUtilization, 3, new NumberTableCellRenderer());
    }

    private void setTitle() {
        if(db.userData.getAmountOfSites()>1){
            title.setVisible(false);
            slh = new SiteListHandler(db);            
            slh.fillComboBoxWithSites(powerTitle);
        } else {
            powerTitle.setVisible(false);
        }
    }           

    public void confirmationControl(){
        isAnyChangesApplicable = checkChanges();
        System.out.println("user permissions are:"+ db.userData.getUserPermissions());
        if(db.userData.getUserPermissions()==2){
            reportConfirm.setVisible(true);   
            ConfirmationStatus.setVisible(false);
            setButtonText(reportConfirm);
        } else {
            reportConfirm.setVisible(false);                        
            ConfirmationStatus.setVisible(true);
            setLabelText(ConfirmationStatus);
            
        }
    }

    private void setButtonText(JButton reportConfirm) {
        System.out.println("setting button");
        if(isAnyChangesApplicable){
            reportConfirm.setEnabled(true);
            reportConfirm.setText("CONFIRM");
        } else {
            reportConfirm.setEnabled(false);
            reportConfirm.setText("CONFIRMED");
        }
    }

    private void setLabelText(JLabel statusLabel) {
        System.out.println("setting text");
        if(isAnyChangesApplicable){
            statusLabel.setText("REPORT IS NOT CONFIRMED");
            statusLabel.setForeground(Color.red);
        } else {
            statusLabel.setText("REPORT IS CONFIRMED");
            statusLabel.setForeground(Color.black);
        }        
    }

    private boolean checkChanges() {
        
        String[] query1 = {"SELECT \n" +
        "lu.*\n" +
        "FROM `LaborUtilization` lu\n" +
        "INNER JOIN LaborAllocation la on lu.LaborAllocationID = la.ID\n" +
        "WHERE lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT \n" +
        "lu.*\n" +
        "FROM `PlantUtilization` lu\n" +
        "INNER JOIN PlantAllocation la on lu.PlantAllocationID = la.ID\n" +
        "WHERE lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT \n" +
        "lu.*\n" +
        "FROM `ProductUtilization` lu\n" +
        "INNER JOIN ProductAllocation la on lu.ProductAllocationID = la.ID\n" +
        "WHERE lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT * FROM `Sales` WHERE DateFor = '%s' AND SiteID = %s"
        };        
        
        String[] query = {"SELECT \n" +
        "lu.*\n" +
        "FROM `LaborUtilization` lu\n" +
        "INNER JOIN LaborAllocation la on lu.LaborAllocationID = la.ID\n" +
        "WHERE `ApprovalID` IS NULL\n" +
        "AND lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT \n" +
        "lu.*\n" +
        "FROM `PlantUtilization` lu\n" +
        "INNER JOIN PlantAllocation la on lu.PlantAllocationID = la.ID\n" +
        "WHERE `ApprovalID` IS NULL\n" +
        "AND lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT \n" +
        "lu.*\n" +
        "FROM `ProductUtilization` lu\n" +
        "INNER JOIN ProductAllocation la on lu.ProductAllocationID = la.ID\n" +
        "WHERE `ApprovalID` IS NULL\n" +
        "AND lu.DateFor = '%s' AND la.SiteID = %s",
        "SELECT * FROM `Sales` WHERE `ApprovalID` IS NULL AND DateFor = '%s' AND SiteID = %s"
        };
        
        boolean done = true;
        boolean rough = false;
        
        for (String string : query1) {
            ResultSet rs = db.runQuery(String.format(string, db.userData.getDate(),db.userData.getSiteID()));
            if(db.getRowCount(rs)>0){
               rough = true; 
            }
        }
        
        if(rough){
            for (String string : query) {
                ResultSet rs =  db.runQuery(String.format(string,db.userData.getDate(),db.userData.getSiteID()));
                System.out.println("Rows for query is: "+db.getRowCount(rs) );
                if(db.getRowCount(rs)>0){
                    System.out.println(String.format(string, db.userData.getDate(),db.userData.getSiteID()));
                    done = true;
                    break;
                } else {
                    done = false;                
                }                 
            };
        } else {
            done = false;
        }
        return done;
    }

    private void ConfirmDay(Date dt, long siteID) {
        if(db.userData.getUserPermissions()==2){
            if(isCommentsFilled()){            
                int insertID = db.dbInsert("inputValidation", new Object[][]{
                    {"LoginID","Comment"},
                    {db.userData.getId(),"Confirmed"}
                });

                //labor utilization
                approveUtilization(getUtilization(dt,siteID,"LaborAllocation","LaborUtilization","LaborAllocationID"),"LaborUtilization",insertID,dt);
                //Plant utilization
                approveUtilization(getUtilization(dt,siteID,"PlantAllocation","PlantUtilization","PlantAllocationID"),"PlantUtilization",insertID,dt);
                //Product utilization
                approveUtilization(getUtilization(dt,siteID,"ProductAllocation","ProductUtilization","ProductAllocationID"),"ProductUtilization",insertID,dt);
                //Sales
                approveSales(dt,siteID,insertID);          



                isAnyChangesApplicable = false;
                confirmationControl();                
            } else {
                JOptionPane.showMessageDialog(null,"To confirm the report you have to fill Daily summary / Comments","Input error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getTableName(String string) {
        String[] cleared = string.split(" ");        
        return cleared[1];
    }

    private ArrayList<Integer> getUtilization(Date dt, long siteID,String tableName,String joinTableName, String joinTableColumn) {
        String q = String.format("SELECT "+joinTableName+".ID FROM"
                + "`"+tableName+"` "
                + "INNER JOIN "+joinTableName+" "
                + "ON "+joinTableName+"."+joinTableColumn+" = %s.ID AND "+joinTableName+".DateFor='%s' WHERE "+tableName+".`StartDate` <= '%s' AND "+tableName+".`EndDate` >= '%s' AND "+tableName+".`SiteID`=%s",tableName,dt,dt,dt,siteID);
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        ResultSet rs = db.runQuery(q);
        try {
            while(rs.next()){
                IDs.add(rs.getInt("ID"));                                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.getStackTrace());
        }
        return IDs;
    }

    private void approveUtilization(ArrayList<Integer> IDs, String tableName,int AprovalID, Date dt) {
        for (Integer ID : IDs) {
            //System.out.printf("aproving %s, with ID %s\n",tableName,ID);
            try{
                db.dbUpdate(tableName, new Object[][]{{"ApprovalID"},{AprovalID}}, new Object[][]{{"ID"},{"="},{ID},{"AND"}});
            }catch(Exception e){
                e.printStackTrace();
            }
        }        
    }

    private void approveSales(Date dt, long siteID, int insertID) {        
        ResultSet rs = db.dbSelect("Sales", new Object[][]{{"DateFor","SiteID"},{"=","="},{dt,siteID},{"AND"}});
        try {
            while(rs.next()){
                db.dbUpdate("Sales", new Object[][]{{"ApprovalID"},{insertID}}, new Object[][]{{"ID"},{"="},{rs.getInt("ID")},{"AND"}});                
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
            new FileLogger(ex.getStackTrace());
        }
        
    }

    private boolean isCommentsFilled() {
        return !MyComents.getText().isEmpty();
    }
}
