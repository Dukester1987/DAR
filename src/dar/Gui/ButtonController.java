/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ldulka
 */
public class ButtonController implements Runnable{

    private final Gui g;
    private boolean status;
    private boolean run = true;

    public ButtonController(Gui g){
        this.g = g;
        this.status = Gui.isAnyChangesApplicable;                        
    }
    
    @Override
    public void run() {
        while(run){   
            if(status!=Gui.isAnyChangesApplicable){
                g.confirmationControl();
                status = Gui.isAnyChangesApplicable;
                fallsleep(500L);
                System.out.println("sleeping");
            } else {
                fallsleep(500L);
            }
        }
    }

    private void fallsleep(long l) {
        try {
            sleep(l);
        } catch (InterruptedException ex) {
            run = false;
        }
    }
    
}
