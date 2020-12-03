/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
//import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tunnelrat.item.Data;
import tunnelrat.item.Item;
import tunnelrat.math.Statistics;

//
public class BST {
    Item item;
    int total_count = 0;
    //static multiArray arr = new multiArray(10040,6);
    public static final HashMap<String,Item> map = new HashMap<>();
    public static final HashMap<Integer,String> dictionary = new HashMap<>();

    //DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
    //Date datecurr = new Date();//dateFormat.format(datecurr));
    //FileWriter writer; 
    Logger log = Logger.getInstance();

    BST(){
    }
    
    public boolean insert(String key, int price, String sname, String time)
    {
        Item item = map.get(key);        
        
        if(!map.containsKey(key))
        {
           item = new Item(key,price,sname,time);
            map.put(key, item);
        }
        else try {
            if(item.isValid(sname, time))
            {
                item.update(price, sname, time);
                map.replace(key, item);
            }
            else
                return false;
        } catch (ParseException ex) {
            System.out.println(ex.toString());
        }
        return true;
    }

    public void removeOutliers(Item item)
    {
        Statistics tmp = new Statistics();
        
        int L = 15;
        int  K = (int)Math.floor(L/2);
        Window win = new Window();
        win.clear();
        int T = 1;
        double sum = 0.0;
        double sk = 0.0;
        boolean ini = true;
        
        List<Integer> plist = item.getPriceList().toArray();

        if(item.getCount() >= 20){  
            //int x = item.prices.pop();   
            Iterator<Data> it = item.getDataList().iterator();
            int pos = 1;
            while(it.hasNext())//!item.prices.isEmpty())
            {   
                int x;
                x = 0;
                int med;
                double z;
                Data data;
                
                if(!win.isFull())
                {
                    for(int k=pos; k<K+pos-1; k++){
                        data = new Data(it.next());
                        x = data.getPrice();  //next price
                        win.add(x);
                    }           
                }
                else{
                    data = new Data(it.next());
                    x = data.getPrice(); //next price
                    win.add(x);  
                }
                
                med = (int)Math.round(win.median());
                double MAD = win.MAD();
                sum += MAD;
                
                if(MAD != 0.0){
                    sk = sum/T;
                    T++;
                }

                z = Math.abs((plist.get(pos)-med)/(1.4826*sk));

                if((z<=6)){
                    tmp.push(x);
                }
               else {
                    if(!win.isFull())
                        win.set(pos,(int)Math.round(med));
                    else
                        win.set((int)Math.round(med));

                    plist.set(pos, med);
                    item.datapt.get(pos).price = med;
               }                   
               pos = pos+1;              
            } 
        }
        
        item.prices = tmp.toStats(plist);
    }  
    
    public long getTotalSize() {
        return total_count;
    }
    
    public void scanFile(String filename) throws FileNotFoundException, IOException, InterruptedException
    {       
       BufferedReader br = new BufferedReader(new FileReader(filename));
       TunnelRat tr = new TunnelRat();
        // scan lines
       String line;
       while ((line = br.readLine()) != null) {
           if (!line.contains(" auctions, ")) continue;
            Message message = Message.getInstance();
            message.load(line);

            String aucMsg = message.get();
            String seller = message.getSeller();                    
            String timestamp = message.getTimestamp();
            if(!message.isValid()) continue;

            //            Matcher m = Pattern.compile( "([-_`\\'\\sa-zA-Z:]+)[\\s\\/,;]((X)?[0-9\\.kKp]+)")

            Matcher m = Pattern.compile( "([-_`\\'\\sa-zA-Z:]+)[\\s\\/,;]((X)?[0-9\\.kKp]+)")
                    .matcher(aucMsg);

            while(m.find()) {
                // get match result object
                MatchResult result = m.toMatchResult();
                String price_string = result.group(2).replaceAll("p", "").trim();
                int price = tr.processPrice(price_string);
                if(price < 100) continue;
                
                String name = result.group(1);  
                name = tr.processItem(name);
                if(tr.skipItem(name)) continue;

               // name = tr.replaceItem(name.trim());

                insert(name, price, seller, timestamp);

            }
            
        }
        br.close();
    }
    
    public HashMap getDictionary(){
        return dictionary;
    }
    
    public void loadDictionary(String filename) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        // scan lines
        String line;
        while ((line = br.readLine()) != null) {
            int key = line.hashCode();
            dictionary.put(key, line);
        }
        br.close();
    }
    
    public void finalMap() throws IOException, InterruptedException {
        //List<Item> mapValues = new ArrayList<Item>(map.values());
        TreeMap<String, Item> sorted = new TreeMap<>();
        sorted.putAll(map);
        Iterator it = sorted.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String key = pair.getKey().toString();//Value().toString();
            Item item = map.get(key);
            removeOutliers(item);
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
    /*
    public static Item insert(Item item, String key, int price, String sname, String time) {

        // if item is null, create new Item object
        if (item == null) {
            ++total_count;
            return new Item(key,price,sname,time);
        }
        
        // if item is found inside of tree, add data to item's fields
        if ( key.compareToIgnoreCase(item.name)==0 ) {
            
            // if seller's price has not been recently added, proceed with adding items
            // or if seller has changed their price ... include price change
          
            double stdev = item.prices.weighted_STD();
            double mean = item.prices.weighted_harmonicMean();
            double x = item.price;
            if ((isRecent(item.time)) && (stdev > 10) && (! item.sellers.peek().equals(sname)) 
                && ((x-mean)/stdev < -0.5)){
                System.out.println("\nItem: " + item + "\nPrice: " + item.price 
                    + " ("+Math.round(mean)+")\nSeller: " + item.seller + "\n\n");
            }
            
            if( (! item.sellers.peek().equals(sname))||(item.sellers.peek().equals(sname) && item.prices.peek() != price) ){
                    item.update(price, sname, time);
            }

            return item;                            
        }
        

        if (key.compareToIgnoreCase(item.name)<0) {
            item.left = insert(item.left, key, price, sname, time);
        }
        
        else 
            item.right = insert(item.right, key, price, sname, time);
            
        return item;          
      }
    */
    
    /*
    public void inorder(){
        arr = new multiArray(10040,6);
        try {
            writer = new FileWriter("C:\\Users\\Thewbacca\\workspace\\TunnelRat\\src\\tunnelrat\\auclog.txt");
            //writer.write(String.format("%-40s %-15s %s %n", "NAME","MEAN","INTERVAL"));
            inorder(item, writer);
            writer.close();
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    public void inorder(Item item, FileWriter w) throws IOException{            
        if (item == null) {
                return;
        }
        inorder(item.left, w);
        
        int count = item.prices.size;
        if (count >= 10) {// & (isRecent(item)) ){
            //item.prices.sort();
            removeOutliers(item);
            double xw = item.prices.weighted_harmonicMean();
            double sw = item.prices.weighted_STD();
            double E = 2.54*(sw)/Math.sqrt(count);//count);
            double upper = xw+E;
            double lower = xw-E;


       //     if ((isRecent(item.time)) && (sw > 10) && ((item.price-xw)/sw < -1.0)){
               
                //w.write(item.time + "\n" + item + "\n\tPrice: " + item.price 
                 //       + " ("+xw+")\n\tSeller: " + item.seller + "\n\n");
       //     }
            
            String[] in = {
                item.name, 
                //count
                new StringBuilder().append(count).toString(),    
                //weighted
                new StringBuilder().append((int)item.prices.weighted_harmonicMean()).toString(),
                //stdev
                new StringBuilder().append(Math.round(sw)).toString(),
                new StringBuilder().append(0).toString(),
                new StringBuilder().append(0).toString()};

                //new StringBuilder().append(item.prices.min).toString(),
                //new StringBuilder().append(item.prices.max).toString()};

            arr.add(in);
            map.put(item.name, item);

            //System.out.println(item.sellers.toString());
           //w.write("\n"+item.name);
           w.write(item.prices.printStats());
           //w.write(item.prices.printPrices());

        }    
        inorder(item.right, w);   

    }
    */