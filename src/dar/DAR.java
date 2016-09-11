/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar;

import dar.Gui.Login;
import javax.swing.SwingUtilities;

/**
 *
 * @author ldulka
 */
public class DAR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                login.setTitle("eDAR - Hi Quality - Login");
                login.setResizable(false);
                //login.setSize(433, 220);
                login.setLocationRelativeTo(null);
                login.setVisible(true);                
            }
        });

      
//        Gui gui = new Gui();
//        gui.setTitle("DAR v1.1 - Hi Quality Group");
//        gui.setResizable(false);
//        gui.setSize(1080,720);
//        gui.setLocationRelativeTo(null);
//        gui.setVisible(true);
    }       

}
