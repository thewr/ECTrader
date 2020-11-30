/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

/**
 *
 * @author Thewbacca
 */
public class Message {
    public Boolean isValidInstance;
    private Message(){}
    private static class LazyLoader { 
        static final Message INSTANCE = new Message();
    }
    public static Message getInstance(){
        return LazyLoader.INSTANCE;
    }
    private static String[] tLine;
    private static final int tsLen = ("[Thu Sep 19 10:30:44 2019]").length();
    private String seller;
    private String timestamp; 
    private String auction;

    public void load(String text) {
        tLine = text.split(" auctions, ");
    }
    

    
    public String getSeller(){
        seller = substr(tLine[0],tsLen+1,tLine[0].length());
        return seller;
    }
    
    public String getTimestamp(){
        timestamp = substr(tLine[0],1,tsLen-1);
        return timestamp;
    }
    
    
    public String get(){
        auction = tLine[1].trim();
        auction =  substr(auction,1,-1);
        return auction;
    }

    // php equivalent class
    private String substr(String s,int start, int length) {
        if (length < 0)
            length = s.length()+length;
        if (start < 0)
            start = s.length()+start;

        s = s.substring(start,length);
        return s;
    }
    
    public boolean isValid(){
        Boolean case1 = !auction.isEmpty();
        Boolean case2 = !auction.matches("^(WTB)\\s.*$");
        isValidInstance = case1&&case2;
        
        return isValidInstance;
    }
        
    
    
}
