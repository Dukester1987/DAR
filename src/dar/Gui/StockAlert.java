/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import dar.dbObjects.StockData;
import dar.localDB.LocalWraper;
import dar.localDB.StockHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ldulka
 */
public class StockAlert implements Runnable{

    private final LocalWraper db;
    private final Gui g;
    private final StockHandler sh;
    private ArrayList<StockData> stockData;
    private boolean check;
    private boolean firstTime;
    private long siteID;

    public StockAlert(Gui g, LocalWraper db){
        this.g = g;
        this.db = db;
        this.sh = new StockHandler(db);        
        this.check = true;
        this.firstTime = true;
        this.siteID = db.userData.getSiteID();
        
    }    
    
    @Override
    public void run() {
        while(check){
            //load stock data and itterate through
            stockData = sh.getStockData();
            if(siteID!=db.userData.getSiteID()) firstTime = true;
            boolean error = false;
            for (StockData sd : stockData) {
                if(sd.getStock()<0){                    
                    error = true;
                    break;
                }                
            }
            if(error){
                g.negStock.setVisible(true);                 
                if(firstTime){
                    JOptionPane.showMessageDialog(null,"Please check your stock levels\nadjust if necessary.\n\nFile->Stock (ctrl+s)","Negative stocks detected!",JOptionPane.WARNING_MESSAGE);
                    firstTime = false;
                    siteID = db.userData.getSiteID();
                }                
            } else {
                g.negStock.setVisible(false);
            }
            try {
                Thread.sleep(60000);                
            } catch (Exception e) {                
            }
        }
    }
    
}
