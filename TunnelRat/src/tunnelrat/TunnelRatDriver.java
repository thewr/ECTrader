/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;
import java.io.*;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import tunnelrat.GUI.Controller;
import tunnelrat.GUI.ItemTable;
import static tunnelrat.TunnelRatDriver.logname;

class Helper extends TimerTask 
{
    String logname, filename;
    BST bt;
    ItemTable table;
    Helper(String logname, String filename)
    {
        this.logname = logname;
        this.filename = filename;
        this.bt = new BST();
    }
    
    public void run()
    {    
        try {
            bt.loadDictionary(logname);
            bt.scanFile(filename);
            bt.finalMap();
            Controller c = new Controller();
            ItemTable table = new ItemTable(c);
            table.setAndViewGUI();

        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }catch (InterruptedException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }catch (Exception ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
public class TunnelRatDriver {   
    // config
    static String filename = "K:\\p99\\EQLITE\\Logs\\eqlog_Senjo_project1999.txt";
    static String logname = "C:\\Users\\Thewbacca\\workspace\\TunnelRat\\src\\tunnelrat\\log.txt";
    // sort -nbms -k1.6,1.26 eqlog_Saax_project1999.txt eqlog_Senjo_project1999.txt > merge.txt
    public static void main(String args[]) throws InterruptedException, IOException, Exception {
        
        BST bt = new BST();
        
        bt.loadDictionary(logname);
        bt.scanFile(filename);
        bt.finalMap();
        Controller c = new Controller();
        ItemTable table = new ItemTable(c);
        Runnable task = new RunnableImpl(table);  
            
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.     
       //Timer timer = new Timer();
       //timer.scheduleAtFixedRate(new Helper(logname, filename),new Date(), 50030);
        SwingUtilities.invokeLater(task);        
    }

    private static class RunnableImpl implements Runnable {

        private final ItemTable table;

        public RunnableImpl(ItemTable table) {
            this.table = table;
        }

        @Override
        public void run() {
                table.setAndViewGUI();
        }
    }

}
