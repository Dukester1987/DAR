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
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DAR.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }        
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
