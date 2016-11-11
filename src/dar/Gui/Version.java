package dar.Gui;

import dar.Functions.FileLogger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ldulka
 */
public class Version {
    public static String version = "1.2.5";
    
    public Version(){
        try {
            FileInputStream input = new FileInputStream("./changelog/version_manifest.properties");
            Properties props = new Properties();
            props.load(input);
            this.version = props.getProperty("app.version");           
        } catch (FileNotFoundException ex) {
            new FileLogger(ex.getStackTrace());
        } catch (IOException ex) {
            new FileLogger(ex.getStackTrace());
        }        
    }
    
}
