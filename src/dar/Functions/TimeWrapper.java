/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dar.Functions;

import java.sql.Date;

/**
 *
 * @author ldulka
 */
public class TimeWrapper {
    
    public Date nextDate() {
        long add = 315360000000L;
        return new Date(System.currentTimeMillis()+add);
    }
    
    public Date yesterday(){
        return new Date(System.currentTimeMillis()-24*60*60*1000);
    }
    
    public Date previousDay(Date date){
        return new Date(date.getTime()-24*60*60*1000);
    }

    public java.util.Date firstDate(){
        java.util.Date d = new java.util.Date();
        d.setTime(System.currentTimeMillis()-432000000);
        return d;
    }
    
    public Date setDate(java.util.Date date) {
        Date sqldate = new Date(date.getTime());
        return sqldate;       
    }      
    
    public Date today() {
        Date date;
        date = new Date(System.currentTimeMillis());
        return date;
    }    
    
}
