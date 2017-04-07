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
    
    public Date moveDate(Date date, int i) {
        int total = (24*60*60*1000)*i;
        return new Date(date.getTime()+total);
    }

    public java.util.Date firstDate(){
        java.util.Date d = new java.util.Date();
        d.setTime(System.currentTimeMillis()-getMonths(1L));
        return d;
    }
    
    public Date setDate(java.util.Date date) {
        Date sqldate = new Date(date.getTime());
        return sqldate;       
    }      
    
    public Date today() {               
        return new Date(System.currentTimeMillis());        
    }    
    
    private Long getSeconds(Long s) {
        return s*1000;
    }
    
    private Long getMinutes(Long m) {
        return getSeconds(60*m);
    }
    
    private Long getHours(Long h) {
        return getMinutes(60*h);
    }
    
    private Long getDays(Long d) {
        return getHours(24*d);
    }
    
    private Long getMonths(Long m) {
        return getDays(30*m);
    }
}
