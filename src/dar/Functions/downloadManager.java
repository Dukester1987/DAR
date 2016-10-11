/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JProgressBar;

/**
 *
 * @author ldulka
 */
public class downloadManager {

    private final String SERVER_ADDRESS;

    public downloadManager(String SERVER_ADDRESS) {
        this.SERVER_ADDRESS = SERVER_ADDRESS;
    }
           
    public boolean download(URL link){
        try {
            InputStream in = new BufferedInputStream(link.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            
            FileOutputStream fos = new FileOutputStream(getFileName(link));
            fos.write(response);
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
    public double download(URL link,JProgressBar overall, JProgressBar actual, double totalSize, double downlaoded){
        double actualDownloaded = 0;
        try {
            System.out.println(link.toString());            
            InputStream in = new BufferedInputStream(link.openStream());
            double actualFileSize = getFileSize(link);
            System.out.println(actualFileSize);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
                actualDownloaded+=n;
                updateProgressBar(actual,actualFileSize,actualDownloaded);
                updateProgressBar(overall, totalSize, downlaoded+actualDownloaded);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            
            FileOutputStream fos = new FileOutputStream(getFileName(link));
            fos.write(response);
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return actualDownloaded;
    }    
    
    private String getFileName(URL link){
        return link.getFile()
                .substring(link.getFile()
                        .lastIndexOf("/UPDATE/")+"/UPDATE/".length()
                );
    }
    
    public double getOverallSize(ArrayList<String> files){
        double bytes = 0;
        for (String file : files) {
            bytes += getFileSize(file);
        }
        return bytes;
    }

    public double getFileSize(String file) {
        //double bytes = 0;
        try {

            URL link = new URL(SERVER_ADDRESS+"/UPDATE/"+file);
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) link.openConnection();
                conn.setRequestMethod("HEAD");
                conn.getInputStream();
                return conn.getContentLength();
            } catch (IOException e) {
                return -1;
            } finally {
                conn.disconnect();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }   
        return 0;
    }
    
    
public double getFileSize(URL link) {
    HttpURLConnection conn = null;
    try {
        conn = (HttpURLConnection) link.openConnection();
        conn.setRequestMethod("HEAD");
        conn.getInputStream();
        return conn.getContentLength();
    } catch (IOException e) {
        return -1;
    } finally {
        conn.disconnect();
    }   
    }    

    private void updateProgressBar(JProgressBar actual, double actualFileSize, double actualDownloaded) {
        actual.setValue((int) ((actualDownloaded/actualFileSize)*100));
        //System.out.println((int) ((actualDownloaded/actualFileSize)*100));
    }
    
}
