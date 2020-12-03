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
        
        
        
        
       // top.setDefaultRenderer(Object.class, centerRenderer);
        top.getColumnModel().getColumn(0).setPreferredWidth(200);

        //for(int i = 1; i < Constants.colNamesTop.length; i++){
        //    TableColumn tableColumn = top.getColumnModel().getColumn(i);
        //    tableColumn.setPreferredWidth(50);
        //    if(i>1){
        //        tableColumn.setCellRenderer(centerRenderer);
        //        //top.getColumnModel().getCol
        //    }
        //}
        PriceChangeColorRenderer colorRenderer = new PriceChangeColorRenderer();
        top.setDefaultRenderer(Object.class, colorRenderer);
        ////// bottom
                
        //bot.setMaximumSize(new Dimension(100,150));
       //bot.setDefaultRenderer(String.class, centerRenderer);
       bot.setDefaultRenderer(Object.class, centerRenderer);
       bot.getColumnModel().getColumn(0).setPreferredWidth(200);
       for(int i = 1; i < Constants.colNamesBot.length; i++)
            bot.getColumnModel().getColumn(i).setPreferredWidth(i*25);
       

       
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

       Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value,
               isSelected, hasFocus, row, column);
       
       int align = DefaultTableCellRenderer.CENTER;
       ((DefaultTableCellRenderer)tableCellRendererComponent).setHorizontalAlignment(align);
        // Apply zebra style on table rows
       /*
        if (row % 2 == 0) {
            c.setBackground(Constants.EVEN_ROW_COLOR);
        } else {
            c.setBackground(Constants.ODD_ROW_COLOR);
        }
        */
                
        
        if (column == Constants.AUC_PRICE) {
            Object aucObj = table.getModel().getValueAt(row,Constants.AUC_PRICE);
            Object emaObj = table.getModel().getValueAt(row,Constants.EMA_PRICE);
            //Object stdObj = table.getModel().getValueAt(row, Constants.PERCENTAGE_CHANGE_IDX);
            
            int auc_price = Integer.parseInt(aucObj.toString());
            int ema_price = Integer.parseInt(emaObj.toString());
            
            Color color = null;
            int diff = ema_price - auc_price;
            if(diff >= 500){
                color = Constants.colors_good[0];
            }
            else if ((diff < 500)&&(diff >= 250)){
                color = Constants.colors_good[1];
            }
            else if ((diff < 250)&&(diff>100))
            {
                color = Constants.colors_good[2];
            }
            
            if (diff <= -500)
                color = Constants.colors_bad[0];
            else if ((diff >-500)&&(diff <= -250))
                color = Constants.colors_bad[1];
            else if ((diff >-250)&&(diff <= -100))
                color = Constants.colors_bad[2];
            
            tableCellRendererComponent.setForeground(color);
        } else {
            tableCellRendererComponent.setForeground(Constants.DEFAULT_FOREGROUND_COLOR);
        }
        
      return tableCellRendererComponent;
   }
}

class MyRenderer{
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer(){
        @Override
        public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus, int row, int column) {
             Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
             int align = DefaultTableCellRenderer.CENTER;
            ((DefaultTableCellRenderer)tableCellRendererComponent).setHorizontalAlignment(align);
             return tableCellRendererComponent;
        }
    };
}
    
    
}