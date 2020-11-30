/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.item;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Thewbacca
 */
public class Time {
    private static Time instance;
    private Time(){
    System.out.println("Time Constructor Called");}
    public static Time getInstance(){
        if(instance==null)
            instance = new Time();
        return instance;
    }
    Date current = new Date();//dateFormat.format(datecurr));
    Date recorded;
    DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
    static private long diff = 0;
    
    long getMinutes(){
        return diff / (60 * 1000) % 60;
    }
    long getHours(){
        return diff / (60 * 60 * 1000) % 24;
    }
    long getDays(){
        return diff / (24 * 60 * 60 * 1000);      
    }
    
    public void setTime(String time) throws ParseException{
        recorded = dateFormat.parse(time);
        diff = current.getTime() - recorded.getTime(); 
    }
    
    boolean isPast(String now, String past, long hour) throws ParseException{
        Date timeNow = dateFormat.parse(now);
        Date timePast = dateFormat.parse(past);
        diff = timeNow.getTime() - timePast.getTime(); 
        
        long days = getDays();
        long totHours = days*24+getHours();
        
        if (totHours >= hour)
            return true;
        else 
            return false;      
    }
}
