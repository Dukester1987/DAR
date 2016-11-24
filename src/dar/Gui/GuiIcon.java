/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Gui;

import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author ldulka
 */
public class GuiIcon {

    public GuiIcon(JFrame frame) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/hqlogo.png")));
    }    
    
    public GuiIcon(JFrame frame, String iconName) {
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/res/"+iconName+".png")));
    }     
}
