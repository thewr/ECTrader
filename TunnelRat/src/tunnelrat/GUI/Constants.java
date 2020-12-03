/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.GUI;
 
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
 
/**
 * @author ashraf_sarhan
 * 
 */
public class Constants {
 /*
 //   public static final Object[] TABLE_HEADER = { "Symbol", "Company Name",
 //           "Price", "Change", "% Change", "Volume" };
 
 //   public static final Object[][] DATA = {
            { "BAC", "Bank of America Corporation", 15.98, 0.14, "+0.88%",
                    32157250 },
            { "AAPL", "Apple Inc.", 126.57, -1.97, "-1.54%", 31367143 },
            { "ABBV", "AbbVie Inc.", 57.84, -2.43, "-4.03%", 30620258 },
            { "ECA", "Encana Corporation", 11.74, -0.53, "-4.33%", 27317436 },
            { "VALE", "Vale S.A.", 6.55, -0.33, "-4.80%", 19764400 },
            { "FB", "Facebook, Inc.", 81.53, 0.64, "+0.78%", 16909729 },
            { "PBR", "Petróleo Brasileiro S.A. - Petrobras", 6.05, -0.12,
                    "-2.02%", 16181759 },
            { "NOK", "Nokia Corporation", 8.06, 0.01, "+0.12%", 13611860 },
            { "PCYC", "Pharmacyclics Inc.", 254.67, 24.19, "+10.50%", 13737834 },
            { "RAD", "Rite Aid Corporation", 7.87, -0.18, "-2.24%", 13606253 } };
    */
    
    public static final String[] colNamesTop = {"ITEM", "SELLER", "PRICE", "EMA"};
    
    public static final String[] colNamesBot = {"ITEM", "COUNT", "EMA"};

    public static final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public static final int DATA_REFRESH_RATE = 3000;
 
    public static final Color colors_good[] = {Color.decode("#28cf35"),Color.decode("#16AF21"),Color.decode("#099213")};
    
    public static final Color colors_bad[] = {Color.decode("#E04214"),Color.decode("#A93715"),Color.decode("#701E06")};
    
    public static final Color DEFAULT_FOREGROUND_COLOR = Color.BLACK;
 
    public static final Color ODD_ROW_COLOR = Color.decode("#e2e2fa");
 
    public static final Color EVEN_ROW_COLOR = Color.decode("#f2f2fa");
    
    public static final Color HEADER_COLOR = Color.decode("#cedff2");
 
    public static final String PLUS_SIGN = "+";
 
    public static final String PERCENTAGE_SIGN = "%";
 
    public static final int ROUND_PLACES = 2;
 
    public static final double[] PRICE_CHANGE_RANGE = { 0.1, 0.5 };
 
    public static final int AUC_PRICE = 2;
 
    public static final int EMA_PRICE = 3;
 
    public static final int PERCENTAGE_CHANGE_IDX = 4;
    
    public static double YMAX = 0.0;
    
    public static double YMIN = 0.0;
    
    public static int XMAX = 0;
    
    public static final Color DARK_BLUE_SLATE = new Color(72,61,139);
    
    public static final Color DARK_RED = new Color(190,32,32);
    
    public static final Color CHARCOAL = new Color(127,127,127);

    
    public static String ITEM_NAME = "";
 
}