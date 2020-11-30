package tunnelrat.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import tunnelrat.BST;
import tunnelrat.Window;
import tunnelrat.item.Data;
import tunnelrat.item.Item;

    public final class ItemTable extends JFrame implements ListSelectionListener{ 
    
    // Define table elements 
    private JTextField textField;
    BST bt;        
    XYSeriesCollection dataset = new XYSeriesCollection();
    DocumentListener documentListener;

    private final Container container;
    private final JButton addButton = createButton("ADD",Color.green,Color.black);
    private final JButton dropButton = createButton("DROP",Color.yellow,Color.black);
    private static HashMap<String,Item> map;


       

    private final JTable tableTop = new JTable();
    private final JTable tableBot = new JTable();
    private final TableRowSorter<TableModel> sorter
            = new TableRowSorter<>(tableBot.getModel());
    
    private final JTextField jtfsearch = new JTextField();
    private final JPanel tablePane = new JPanel();

    private JFreeChart chart;
    private final ChartPanel chartpanel = new ChartPanel(chart, true, true, true, true, true);
   

    private void initComponents(Controller c){
        setMinimumSize(new Dimension(1600, 800));
        setPreferredSize(new Dimension(1600, 800));
        
        setBackground(Color.black);
        
        addButton.addActionListener(c.addListener(tableBot, tableTop));
        dropButton.addActionListener(c.addListener(tableTop, tableBot));
        
        //Add listener
        ListSelectionModel listselection = tableBot.getSelectionModel();
        SelectionListener selectbot = new SelectionListener(tableBot);
        listselection.addListSelectionListener(selectbot);
        
        listselection = tableTop.getSelectionModel();
        SelectionListener selecttop = new SelectionListener(tableTop);
        listselection.addListSelectionListener(selecttop);
        
        //Lay out the buttons from left to right.
        Box controlBox = Box.createHorizontalBox();
       // jtfsearch.setMaximumSize(new Dimension(50,40));
        controlBox.add(jtfsearch);
        controlBox.add(Box.createGlue());
       // controlBox.add(addButton);
        //controlBox.add(Box.createHorizontalStrut(5));
       // controlBox.add(dropButton);
        //controlBox.add(Box.createHorizontalStrut(5));
        controlBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        
        //Create the scroll pane and add the table to it. 
        JScrollPane top_listScroller,bot_listScroller; 
        bot_listScroller = new JScrollPane(tableBot);
        top_listScroller = new JScrollPane(tableTop);
               
        //Add the scroll pane to this panel.
        tablePane.setLayout(new BoxLayout(tablePane, BoxLayout.PAGE_AXIS));
        Box verticalBox = Box.createVerticalBox();
       // verticalBox.add(Box.createGlue());
            verticalBox.add(Box.createVerticalStrut(20));
            verticalBox.add(c.createLabel("SALES"));
            verticalBox.add(Box.createVerticalStrut(5));

            verticalBox.add(top_listScroller);
            verticalBox.add(Box.createVerticalStrut(10));
            verticalBox.add(c.createLabel("ITEMS"));
            verticalBox.add(Box.createVerticalStrut(5));

            verticalBox.add(bot_listScroller);
            verticalBox.add(Box.createVerticalStrut(20));
            //verticalBox.add(Box.createGlue());
            verticalBox.add(controlBox);
            verticalBox.add(Box.createVerticalStrut(20));
            verticalBox.add(Box.createGlue());

       // verticalBox.setMaximumSize(new Dimension(390,630));

        tablePane.add(verticalBox);
       // tablePane.setMaximumSize(new Dimension(1200,840));
        tablePane.setBackground(Color.decode("#f9f9f9"));
    }
    
    private JButton createButton(String name, Color fgc, Color bgc) {
        JButton button = new JButton(name);
        button.setActionCommand(name);
      //  button.addActionListener(new AddListener());
        button.setEnabled(true);
        button.setBackground(fgc);
        button.setForeground(bgc);         
        return button;
    }
    
    void iniTable(){
    
    }
    
    

    public ItemTable(Controller c) throws ParseException, Exception{
        Box landscape = Box.createHorizontalBox();

        c.setupTables(tableTop, tableBot);     
        initComponents(c);         
        
        jtfsearch.getDocument().addDocumentListener(c.DocListener(sorter, jtfsearch));

        Dimension minSize = new Dimension(1600, 1000);
        Dimension prefSize = new Dimension(1600, 1000);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 1600);
        Box.Filler filler = new Box.Filler(minSize, prefSize, maxSize);
        //filler.add(Box.createVerticalStrut(50));
       // filler.setBackground(Color.black);
        
        filler.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        filler.add(chartpanel);

        tablePane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        
        landscape.add(Box.createHorizontalStrut(5));
        //landscape.add(filler);
        landscape.add(tablePane);        
        landscape.add(Box.createHorizontalStrut(5));
        landscape.add(new JSeparator(JSeparator.VERTICAL));
        landscape.add(Box.createHorizontalStrut(5));
        landscape.add(filler);


        container = getContentPane();
        container.add(landscape, BorderLayout.LINE_START);
    }
    
    public class SearchBar extends JPanel{
        public SearchBar(){//final View view, boolean temp){
            	setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		//this.view = view;
		add(Box.createHorizontalStrut(2));
        }
       
        
    }
    
    class addDocListener implements DocumentListener {
        
        @Override 
            public void insertUpdate(DocumentEvent e) {
                String text = jtfsearch.getText();
                
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        
        @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfsearch.getText();

                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    }

    private XYDataset createDataset(){
      //  XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.removeAllSeries();

        Iterator<Data> iter;
        iter = BST.map
                .get(Constants.ITEM_NAME)
                .datapt.iterator();
        
        XYSeries series1 = new XYSeries(Constants.ITEM_NAME);
        XYSeries series2 = new XYSeries(Constants.ITEM_NAME+" Roll");

        int count = 1;         
        int L = 30;
        int K = (int)Math.floor(L/2);
        Window win = new Window();
        
        double smoothed = 0.0;
        while(iter.hasNext()){
            int x;
            
            
            x = iter.next().price;
            win.add(x);
            
            if(count==1)                Constants.YMAX = Constants.YMIN = x;
            if (x > Constants.YMAX)     Constants.YMAX = x;
            if(x < Constants.YMIN)      Constants.YMIN = x;

            series1.add(count, x);
            smoothed = win.smooth();
            series2.add(count, smoothed);            
            count++;
        }
        Constants.XMAX=count-1;
        
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        return dataset;
    }
    
    private void refreshChart() {
        chartpanel.removeAll();
        chartpanel.revalidate(); // This removes the old chart 
        Dimension dim = new Dimension(800,800);
        chartpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        chartpanel.setSize(dim);
        chartpanel.setBackground(Color.black);
        createPlots();
        //chartpanel.setLayout(new BorderLayout(50,50));
        //chartpanel.add(Box.createRigidArea(new Dimension(50,100)));
        chartpanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        
        chartpanel.repaint(); // This method makes the new chart appear
    }
    
    private void createLinePlot(){
        XYSeriesCollection dxy2 = new XYSeriesCollection();
        dxy2.addSeries(dataset.getSeries(1));
       
        
    }
    
    private JFreeChart createOverlaidChart()   
    {
        //Series 1
        XYSeriesCollection dxy1 = new XYSeriesCollection();
        dxy1.addSeries(dataset.getSeries(0));
        
        //Series 2
        XYSeriesCollection dxy2 = new XYSeriesCollection();
        dxy2.addSeries(dataset.getSeries(1));
        
        XYLineAndShapeRenderer  renderer = new XYLineAndShapeRenderer(false,true);
        Shape shape  = new Ellipse2D.Double(0,0,5,5);
        renderer.setSeriesShape(0, shape);
        NumberAxis xAxis = new NumberAxis("");   
        NumberAxis yAxis = new NumberAxis("Price");   
        XYPlot xyplot = new XYPlot(dxy1,xAxis, yAxis, renderer);
        //XYPlot xyplot = new XYPlot(dxy1, dateaxis, numberaxis, xylinerenderer);   
        
        StandardXYItemRenderer standardxyitemrenderer = new StandardXYItemRenderer();   
        //standardxyitemrenderer.setToolTipGenerator(new StandardXYToolTipGenerator("{0}: ({1}, {2})", new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0.00")));   
        xyplot.setDataset(1, dxy2);   
        xyplot.setRenderer(1, standardxyitemrenderer);   
        xyplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);   
        return new JFreeChart("EC Prices", JFreeChart.DEFAULT_TITLE_FONT, xyplot, true);   
    }   
   
    
    
    private void createPlots(){     
        // Changes background color
        // Create chart
        //chart = ChartFactory
         //       .createXYLineChart(Constants.ITEM_NAME, "", "",dataset);
	//XYPlot plot = (XYPlot) chart.getPlot();
        chart = createOverlaidChart();
        
        // Create chart
        XYSeriesCollection dxy1 = new XYSeriesCollection();
        dxy1.addSeries(dataset.getSeries(0));
        

       // chart = ChartFactory.createScatterPlot("EC Prices", 
        //    "X-Axis", "Y-Axis", dxy1);        
        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));
        
        /*
        
        NumberFormat nf = NumberFormat.getInstance(); 
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setDomainGridlineStroke(new BasicStroke(0.5f));
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlineStroke(new BasicStroke(0.5f));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        
        
                
        NumberAxis range = (NumberAxis)plot.getRangeAxis();
        NumberAxis domain = (NumberAxis)plot.getDomainAxis();
        //range.setNumberFormatOverride(NumberFormat.getCurrencyInstance());
        double diff = Constants.YMAX-Constants.YMIN;
        double mid = (Constants.YMAX+Constants.YMIN)/2.0;
        if(diff==0)
            diff = Constants.YMAX*0.10;
        range.setRange(0, mid+diff);
        
        domain.setRange(1,Constants.XMAX);
        range.setAutoRangeIncludesZero(true);
        range.setTickUnit(new NumberTickUnit(Math.round(Constants.YMAX/5)));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true); 
        XYItemRenderer renderer = plot.getRenderer();
        
        renderer.setSeriesPaint(0, Constants.DARK_BLUE_SLATE);
        renderer.setSeriesStroke(0 , new BasicStroke( 2.0f ) );
        renderer.setSeriesPaint(1, Constants.DARK_RED);
        renderer.setSeriesStroke(1 , new BasicStroke( 2.5f ) );
        plot.setRenderer(renderer);
                
        */
                
     }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    class SelectionListener implements ListSelectionListener {
        JTable table;
        SelectionListener(JTable table){
            this.table = table;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            
           int row = table.getSelectedRow();
           if((row != e.getFirstIndex())&(row != e.getLastIndex())) //XOR
               return;
           
           int select = table.convertRowIndexToModel(table.getSelectedRow());

           Constants.ITEM_NAME = table.getModel().getValueAt(select, 0).toString();
           createDataset();
           refreshChart();
        } 
    }
    public void setAndViewGUI() { 
        //Create and set up the content pane.
       // Controller c = new Controller();
      //  ItemTable table = new ItemTable(c);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        pack();
        setVisible(true);     
        
    }   
    
}
