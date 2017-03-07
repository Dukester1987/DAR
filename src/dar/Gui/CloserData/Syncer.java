/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui.CloserData;

import dar.remoteDB.DBWrapper;
import javax.swing.JLabel;

/**
 *
 * @author ldulka
 */
public class Syncer implements Runnable{

    private final JLabel label;
    private final Thread t;
    private final DBWrapper db;

    public Syncer(DBWrapper db1, Thread t, JLabel jLabel1) {
        this.db = db1;
        this.t = t;
        this.label = jLabel1;
    }
    
    
    
    @Override
    public void run() {
        t.interrupt(); 
        label.setText("Checking connection to the server");
        if(db.isConnected()){
            System.out.println("still connected");
        }        
        long startTime = System.currentTimeMillis()+(1000*1);
        while(t.isAlive()){ 
            try {
                Thread.sleep(20);
                //label.setText("Closing in: "+ timeLeft +"s");
                if(startTime<=System.currentTimeMillis() && db.isSynced){
                    System.out.println("closing");                
                    System.exit(0);               
                }                  
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Long timeLeft = (startTime - System.currentTimeMillis()) / 1000;      
        }
        label.setText("closing");
        System.exit(0);          
    }
    
}
