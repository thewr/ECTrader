/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thewbacca
 */
public class Logger {
    File filename = new File("C:\\Users\\Thewbacca\\workspace\\TunnelRat\\src\\tunnelrat\\auclog.txt");
    FileWriter writer;
    Boolean iniWriter = true;
    private Logger(){}
    private static class LazyLoader { 
        static final Logger INSTANCE = new Logger();
    }
    public static Logger getInstance(){
        return LazyLoader.INSTANCE;
    }
    
    
    
    public void add(String line) throws IOException, InterruptedException{
            if(iniWriter)
                writer = new FileWriter(filename);
            iniWriter = false;
            writer.write(line);
            Thread.sleep(50);
            writer.write("\n");
    }
    
    public void close() throws IOException{
        writer.close();
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
                String name = result.group(1);  
                name = tr.processItem(name);
                if(tr.skipItem(name)) continue;

                String price_string = result.group(2).replaceAll("p", "").trim();
                if(price_string.isEmpty()) continue;
     
                int price = tr.processPrice(price_string);
                
                name = tr.replaceItem(name.trim());

                if(price >= 10){
                    //message.isValidInstance = 
                    BST bt = new BST();
                    bt.insert(name, price, seller, timestamp);
                }
            }
        }
        br.close();
    }
    
    

}
