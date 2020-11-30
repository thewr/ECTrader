/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.item;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


// keep track of when seller auctioned

public class Seller {
    public static HashMap<Integer, String> map = new HashMap<>();
    DateFormat dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss yyyy");

    Date datecurr = new Date();//dateFormat.format(datecurr));
    
    public Seller()
    {
        this.map = new HashMap<>();        
    }
        
    
    public void add(String sname, String time)
    {
        int key = sname.hashCode();
        
        if(map.containsKey(key))
            map.replace(key,time);
        else     
            map.put(key,time);
    }
    
    public boolean isPeek(String key, String time)
    {
        if(map.get(key).equals(time))
            return true;
        else
            return false;
    }
    
    public String getTime(String sname){
        int key = sname.hashCode();
        return map.get(key);
    }

    
}