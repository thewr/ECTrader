/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.GUI;

import java.awt.Color;
import java.awt.Component;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import tunnelrat.BST;
import tunnelrat.item.Item;

/**
 *
 * @author Thewbacca
 */
public class Controller {
       
    private static final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    private TableRowSorter<TableModel> rowSorter = null;
    private static HashMap<String,Item> map = new HashMap<>();// = BST.map;
        
    JTable table;    
    
    public Controller(){
        map.putAll(BST.map);
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    }
    
        // Define list model (container for elements) (non-edit)
    public DefaultTableModel modelTop = new DefaultTableModel()
    {
            @Override 
            public boolean isCellEditable(int row, int column) { return false; }         
    };
    
            // Define list model (container for elements) (non-edit)
    public DefaultTableModel modelBot = new DefaultTableModel() {
            @Override 
            public boolean isCellEditable(int row, int column) { return false; }     
    };

    public DocumentListener DocListener(TableRowSorter<TableModel> sorter, JTextField field) {
        return new DocumentListener(){
            
            @Override 
            public void insertUpdate(DocumentEvent e) {
                String text = field.getText();
                
                
                System.out.println("Doc created");


                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
        }
        
        @Override
            public void removeUpdate(DocumentEvent e) {
                String text = field.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }
     
     public ActionListener getDeleteListener () {
         return new ActionListener() {
             @Override public void actionPerformed (ActionEvent e) {
                 //
             }
         };
     }
     
         //This listener is shared by the text field and the hire button.
    public ActionListener addListener(JTable fromTable, JTable toTable) { 
        return new ActionListener() {
            @Override public void actionPerformed (ActionEvent e) {
                int selrow = fromTable.convertRowIndexToModel(fromTable.getSelectedRow());
                //String table_click0 = fromTable.getModel()
                //        .getValueAt(fromTable.convertRowIndexToModel(selrow), 0).toString();
                
                DefaultTableModel m1, m2;
                m1 = (DefaultTableModel)fromTable.getModel();
                m2 = (DefaultTableModel)toTable.getModel();
                
                Object o = m1.getDataVector().get(selrow);
                String[] s = o.toString()
                        .substring(1,o.toString().length()-1)
                        .split(",");
                //for(int i = 0; i<3; i++)
                    System.out.print(o + ", ");
                System.out.println("");
                
                Object[] orowsel = new Object[fromTable.getColumnCount()];
                for(int col = 0; col < fromTable.getColumnCount(); col++)
                    orowsel[col] =  fromTable.getValueAt(selrow, col);
                        
                m2.addRow(s);
                m1.removeTableModelListener(table);
                m1.removeRow(selrow);
            
                int size = m1.getRowCount();
                System.out.println("Number of items in cart: " + size);
            }
        };
    }
    
    public JLabel createLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.BOLD, 16));
        label.setForeground(Color.BLACK);
        return label;
    }
    
    public void setupTables(JTable top, JTable bot){
        
        PriceChangeColorRenderer colorRenderer = new PriceChangeColorRenderer();

        
        top.setModel(modelTop);
        bot.setModel(modelBot);
        
        rowSorter = new TableRowSorter<>(bot.getModel());
        bot.setRowSorter(rowSorter);

        //add headers
        JTableHeader topHeader = top.getTableHeader();
        topHeader.setBackground(Constants.HEADER_COLOR);
        topHeader.setFont(new Font("Dialog", Font.BOLD, 12));
        
        JTableHeader botHeader = bot.getTableHeader();
        botHeader.setBackground(Constants.HEADER_COLOR);
        botHeader.setFont(new Font("Dialog", Font.BOLD, 12));
        
        for(String s: Constants.colNamesTop){
            modelTop.addColumn(s);
        }
        for(String s: Constants.colNamesBot)
            modelBot.addColumn(s);
        
        //top.setFillsViewportHeight(true);
        //top.setPreferredScrollableViewportSize(new Dimension(200, 150));
        //top.setDefaultRenderer(String.class, centerRenderer);
        top.setDefaultRenderer(Object.class, centerRenderer);
        
        //for(int i = 1; i < Constants.colNamesTop.length; i++)
         //   top.getColumnModel().getColumn(i).setPreferredWidth(50);
        
        ////// bottom
                
        //bot.setMaximumSize(new Dimension(100,150));
       //bot.setDefaultRenderer(String.class, centerRenderer);
       bot.setDefaultRenderer(Object.class, centerRenderer);
        
       bot.getColumnModel().getColumn(0).setPreferredWidth(200);
       for(int i = 1; i < Constants.colNamesBot.length; i++)
            bot.getColumnModel().getColumn(i).setPreferredWidth(i*25);
       
       top.setDefaultRenderer(Object.class, colorRenderer);
       
       Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry)it.next();
            String key = pair.getKey().toString();
            Item item = map.get(key);
                        
            int count = item.getCount();
            if (count >= 10) {
                
                if (item.isRecent){
                    modelTop.addRow(item.toObjectTop());    
                }
                
                modelBot.addRow(item.toObjectBot());
            }
         it.remove();   
        }

    }

// Customize the code to set the background and foreground color for each column of a JTable
class PriceChangeColorRenderer extends  DefaultTableCellRenderer  {

   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, 
           boolean isSelected,   boolean hasFocus, int row, int column) 
   {

       Component c = super.getTableCellRendererComponent(table, value,
               isSelected, hasFocus, row, column);
       
       
       
        // Apply zebra style on table rows
       /*
        if (row % 2 == 0) {
            c.setBackground(Constants.EVEN_ROW_COLOR);
        } else {
            c.setBackground(Constants.ODD_ROW_COLOR);
        }
        */
                
        
        if (column == Constants.SALE_IDX) {
            Object priceObj = table.getModel().getValueAt(row,Constants.PRICE_IDX);
            Object saleObj = table.getModel().getValueAt(row,Constants.SALE_IDX);
            Object stdObj = table.getModel().getValueAt(row, Constants.PERCENTAGE_CHANGE_IDX);
            
            double price = Double.parseDouble(priceObj.toString());
            double sale = Double.parseDouble(saleObj.toString());
            double std = Double.parseDouble(stdObj.toString());
            
            double z = (price-sale)/(double)std;
            Color color;
            //if (z != 0) {
                if(z > 1){
                    color = Constants.PRICE_UP_COLOR;
                //else
                //    color = Constants.PRICE_DOWN_COLOR;
                c.setForeground(color);
                }
            //}           
        } else {
            c.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
        }
        
      return c;
   }
}
    
    
}
    

    
    
    
    


//NOTES


//I think you are making a mistake of conceptually tying a "view" to individual GUI components. In the MVC model there really is not such concept as a "subview".
//
//If you have a frame, for example, with many panels and subpanels and subcomponents such as buttons, etc., that entire thing is the view/controller interface in MVC. Your top-level frame (or some encapsulating class, perhaps your GUI has many frames, it doesn't matter) would provide the interface to the controller (via, say, events) and the view (via, say, listeners). Exactly how your UI is arranged is abstracted behind that. Think of your entire UI as a black box. How events are dispatched / provided from the internal components is part of the UI implementation, and this may very well involve event chains and delegates and etc. -- but that is not something the view/controller is concerned with.
//
//So you end up with something like (conceptual example):
//
//Model m = new Model();
//View v = new View(m);
//Controller c = new Controller(m);
//MyFrame gui = new MyFrame(v, c);
//
//Then:
//
//public MyFrame (View v, Controller c) {
//
//   // register listeners to view, e.g.
//   v.addChangeListener(this /* or some other ui component */);
//
//   // send events to controller, e.g.
//   addActionListener(c /* or some interface that c provides */).
//
//   // or even:
//   deleteButton.addActionListener(new ActionListener(){
//       @Override public void actionPerformed (ActionEvent e) {
//           c.doDelete();
//       }
//   });   
//
//}
//
//In an ideal situation, you can completely rework the hierarchy of your GUI, having a totally different component tree and organization, and, providing the information being conveyed through the GUI remains unchanged, the view and controller remain unchanged as well.
//
//Once you get used to the MVC pattern, you may start to take shortcuts here and there (for example, in many cases the view and/or controller are just middle-men for events, and the GUI itself sometimes ends up encapsulating the entire controller and view concepts, leaving you with a GUI, a model, and a bunch of event listeners -- the Swing event architecture is fairly MVC in itself), and boundaries may get fuzzier. That's OK. But there's no reason that either the view or the controller have to know about the structure and object tree in the GUI -- even in cases where controller/view are just abstract concepts instead of concrete classes.
//
//Sometimes MVC can get especially fuzzy when working with Swing because you kind of end up doing everything in an MVC way naturally, so when you try to explicitly impose an MVC pattern on your architecture, you're left wondering why that doesn't seem to change much at all (and it becomes difficult to see the benefits, because you forget that you're already doing it). Sometimes it's more of a way of thinking than a concrete way of doing.
//
//Essentially, any time your model is completely independent of your GUI, you are using some incarnation of MVC.
//
//Edit: I noticed you also tagged this with "observer pattern" and mentioned it briefly. It's worth noting that many times, explicitly implementing an observer pattern for Swing-based GUIs, at least in relatively simple applications, adds nothing but a redundant abstraction layer, as the entire Swing API is already fundamentally based on this design pattern (it's the whole event -> listener subsystem).
//
//Frequently I have found that something like the following works well, using the so-called observer pattern all around, where the controller was essentially just a collection of event listeners, like:
//
// public class Controller {
//     Model m;
//     public ActionListener getDeleteListener () {
//         return new ActionListener() {
//             @Override public void actionPerformed (ActionEvent e) {
//                 m.deleteSomething();
//             }
//         };
//     }
// }
//
//And then the GUI does something like:
//
// public class GUI extends JFrame {
//     JButton deleteButton; 
//     public GUI (View v, Controller c) {
//         deleteButton.addActionListener(c.getDeleteListener()); 
//     }
// }
//
//But, there are many ways to skin that cat. In that example, even, once you are familiar with the concept, and if it is appropriate, you could take a shortcut and just have the GUI constructor register listeners that modify the model. Then, as mentioned above, the controller becomes just an abstract concept.
