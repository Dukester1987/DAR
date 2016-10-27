/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ldulka
 */
public class FileLogger {

    public FileLogger(String error) {
        logInFile(error);
    }
    
    public FileLogger(StackTraceElement[] traces){
        String error = "";
        for (StackTraceElement trace : traces) {
            error += trace.toString()+"\n";
        }
        logInFile(error);
    }

    private String createFileName() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        String date = dt.format(new Date());
        return date+"_Systemlog.txt";
    }
    
    private void logInFile(String st){
        String path = "./log/"+createFileName();
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            fw = new FileWriter(path, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(new Date().toString());
            out.println(st);
            out.close();
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        finally {
            if(out != null)
                out.close();
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }        
    }
    
    
    
}
