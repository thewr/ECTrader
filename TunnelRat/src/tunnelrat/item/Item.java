package tunnelrat.item;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import tunnelrat.Window;
import tunnelrat.math.Statistics;


public class Item implements Comparable<Item> {
    
    // members of the Item Class
    public Statistics prices = new Statistics();
    public LinkedList<Data> datapt = new LinkedList<>();
    public Window win; //= new Window();
    public Seller sellers = new Seller();
    public boolean isRecent = false;
    
    int price;
    String name, seller;
    
    DecimalFormat df = new DecimalFormat("###.###");
    public Item(String item_name, int price, String seller_name, String time) {
        win = new Window();
        win.add(price);
        this.name = item_name;
        prices.push(price);// = //new StackLinkedList();
        sellers.add(seller_name, time);    
        this.seller = seller_name;
        this.price = price;
    }
    
    public Object[] toObjectTop(){
        int EMA = (int)this.win.smooth();
        //int change = (int)prices.getTypicalPrice()-EMA;
        //int percentChange = 100*(change)/EMA;

        Object[] o = new Object[]{name, seller, 
            new StringBuilder().append((int)price).toString(),
            new StringBuilder().append(EMA).toString(),
                    new StringBuilder().append((int)prices.stdev()).toString(),
        };
                
        return o;
    }
    
    public Object[] toObjectBot(){
        int EMA = (int)this.win.smooth();
       // int change = (int)prices.getTypicalPrice()-EMA;
       // int percentChange = 100*(change)/EMA;
        Object[] o = {name,  
                    //count
                    new StringBuilder().append(getCount()).toString(),    
                    //weighted
                    new StringBuilder().append(EMA).toString(),
                    //stdev
                    //new StringBuilder().append((int)prices.stdev()).toString(),
                };
        return o;
    }

    public LinkedList<Data> getDataList(){
        return datapt;
    }
    
    public int getCount(){
        return prices.size();
    }
    
    public Statistics getPriceList(){
        return prices;
    }
    
    public void setPriceList(Statistics other){
        prices = other;
    }
       
   boolean inTable(boolean flag){
       return flag;       
   }
   
   public boolean isSale() throws ParseException{
       Time timeobj = Time.getInstance();
       int EMA = (int)win.smooth();
        if (((EMA-this.price)>100))//-this.prices.mean())/this.prices.stdev())<-2.0){
            return true;
        else
            return false;
   }

   public String getName() {
      return name;
   }

   public void update(int price, String seller_name, String time) throws ParseException 
   {
       this.prices.push(price);
       this.win.add(price);
       this.sellers.add(seller_name, time);
       this.seller = seller_name;
       this.datapt.add(new Data(price,time));
               this.price = price;

       isRecent = isRecent(time,30);
    }
   
   public String getPrice(){
       return new StringBuilder().append(this.price).toString();
   }
   
   public String getMean(){
       return new StringBuilder().append((int)this.prices.mean()).toString();
   }
   
   
    boolean isRecent(String time, int cutoff) throws ParseException
    {
        Time t = Time.getInstance();
        t.setTime(time);

        long days = t.getDays();
        long hours = t.getHours();
        long minutes = t.getMinutes();
        if ( (days < 1) && (hours < 1) && (minutes < cutoff))
        {
            return true;
        }
        else 
            return false;          
    }
   
   public boolean isValid(String sname, String time) throws ParseException{ 
       Time t = Time.getInstance();
       boolean isPast = true;
       
       int key = sname.hashCode();
       if(Seller.map.containsKey(key)){
           isPast = t.isPast(time,Seller.map.get(key),2);
       }       

       return ((isPast));//||(sellers.isPeek(sname, time) && prices.peek() != price));
   }
   
       /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */

    @Override
    public int compareTo(Item item) {
        return this.name.compareTo(item.name);
    }
   
} // end of class
