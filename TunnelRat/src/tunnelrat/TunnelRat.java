/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

import java.util.Hashtable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Thewbacca
 */

public class TunnelRat {
    
    String[] names1 = new String[79];
    String[] names2 = new String[79];
    private static final Hashtable<Integer, String> h = new Hashtable<>(); 
    //String[] a_names = {"A Broom", "A Stein","A Gypsy Medallion","A Stone Key","A Yellow Whip"};

    TunnelRat(){
        
        names1[0] = "GEB";                      names2[0] = "Golden Efreeti Boots";
        names1[1] = "SSOY";                     names2[1] = "Short Sword of the Ykesha";
        names1[2] = "HQ Bear Skin";             names2[2] = "High Quality Bear Skin";
        names1[3] = "Hand Made Backpacks";      names2[3] = "Hand Made Backpack";
        names1[4] = "A Dark Reaver";            names2[4] = "Dark Reaver";
        names1[5] = "Plat Ruby Veil";           names2[5] = "Platinum Ruby Veil";
        names1[6] = "jade prod";                names2[6] = "Jade Chokidai Prod";
        names1[7] = "zombie skins";             names2[7] = "Zombie Skin";
        names1[8] = "Zombie skin";              names2[8] = "Zombie Skin";
        names1[9] = "LP";                       names2[9] = "Leather Padding";
        names1[10] = "Sow pot";                 names2[10] = "10 Dose Blood of the Wolf";
        names1[11] = "Sow Potion";              names2[11] = "10 Dose Blood of the Wolf";
        names1[12] = "SoW potions";             names2[12] = "10 Dose Blood of the Wolf";
        names1[13] = "Sow pots";                names2[13] = "10 Dose Blood of the Wolf";
        names1[14] = "Wort pots";               names2[14] = "Potion of Stinging Wort";
        names1[15] = "Zheart";                  names2[15] = "Zlandicar's Heart";
        names1[16] = "Z Heart";                 names2[16] = "Zlandicar's Heart";
        names1[17] = "J Boot";                  names2[17] = "Journeyman's Boots";
        names1[18] = "Bag of Sewn Evil Eye";    names2[18] = "Bag of Sewn Evil-Eye";        
        names1[19] = "Lumi Staff";              names2[19] = "Luminescent Staff";
        names1[20] = "Jboots MQ";               names2[20] = "Journeyman's Boots";
        names1[21] = "Orb Of Infitie Void";     names2[21] = "Orb Of the Infitie Void";
        names1[22] = "peggy cloak";             names2[22] = "Pegasus Feather Cloak"; 
        names1[23] = "skull shaped barbute";    names2[23] = "Skullshaped Barbute";
        names1[24] = "Coldain Heads";           names2[24] = "Coldain Head";
        names1[25] = "Yew Leaves";              names2[25] = "Yew Leaf";
        names1[26] = "giant warrior helmets";   names2[26] = "Giant Warrior Helmet";
        names1[27] = "Giant Warrior Helm";      names2[27] = "Giant Warrior Helmet";
        names1[28] = "Fungi Tunic";             names2[28] = "Fungus Covered Scale Tunic";
        names1[29] = "Fungy";                   names2[29] = "Fungus Covered Scale Tunic";
        names1[30] = "fungi staff";             names2[30] = "Fungi Covered Great Staff";
        names1[31] = "Fungi staff";             names2[31] = "Fungi Covered Great Staff";
        names1[32] = "fungi";                   names2[32] = "Fungus Covered Scale Tunic";
        names1[33] = "Black pearls";            names2[33] = "Black Pearl"; 
        names1[34] = "Thurg Plate Helm";        names2[34] = "Corroded Plate Helmet";
        names1[35] = "thurg plate legs";        names2[35] = "Corroded Plate Greaves";
        names1[36] = "";                        names2[36] = "";
        names1[37] = "TBB";                     names2[37] = "Thick Banded Belt";
        names1[38] = "Lodi Shield";             names2[38] = "Lodizal Shell Shield";
        names1[39] = "High Quality Bear Skins"; names2[39] = "High Quality Bear Skin";
        names1[40] = "Flawless Diamonds";       names2[40] = "Flawless Diamond";
        names1[41] = "Dose Blood of the Wolf";  names2[41] = "10 Dose Blood of the Wolf";
        names1[42] = "Dose Greater Null Potion";            names2[42] = "10 Dose Greater Null Potion";
        names1[43] = "Dose Greater Potion of Purity";       names2[43] = "10 Dose Greater Potion of Purity";
        names1[44] = "Dose Kilva's Blistering Flesh";       names2[44] = "10 Dose Kilva's Blistering Flesh";    
        names1[45] = "Dose Kilva's Skin of Flame";          names2[45] = "10 Dose Kilva's Skin of Flame";
        names1[46] = "Dose Potion of Aquatic Haunt";        names2[46] = "10 Dose Potion of Aquatic Haunt";
        names1[47] = "Dose Potion of Stinging Wort";        names2[47] = "10 Dose Potion of Stinging Wort";
        names1[48] = "crusty bracer";                       names2[48] = "Crustacean Shell Bracers";
        names1[49] = "Crystal Chitin Chest";                names2[49] = "Crystal Chitin Chestguard";
        names1[50] = "FBSS";                                names2[50] = "Flowing Black Silk Sash";
        names1[51] = "Giant Warrior Helmets";               names2[51] = "Giant Warrior Helmet";
        names1[52] ="Woodman's Staff";                      names2[52] = "Woodsman's Staff";
        names1[53] = "adamantite band";                     names2[53] = "Adamantite Armband";
        names1[54] = "SCHW";                                names2[54] = "Silver Chitin Hand Wraps";
        names1[55] = "Bio Orb";                             names2[55] ="Bioluminescent Orb";
        names1[56] = "HQ Bear Pelts";                       names2[56] ="High Quality Bear Skin";
        names1[57]= "HQ Bear Skins";                         names2[57] ="High Quality Bear Skin";
        names1[58] = "HQ Bear Pelts";                       names2[58] ="High Quality Bear Skin";
        names1[59] = "Kael Corroded plate bp";              names2[59] = "Corroded Plate Breastplate";    
        names1[60] = "lammy";                               names2[60] = "Lamentation";
        names1[61] = "Jboot";                               names2[61] = "Journeyman's Boots";
        names1[62] = "Jboots";                              names2[62] = "Journeyman's Boots";
        names1[63] = "J boots";                              names2[63] = "Journeyman's Boots";
        names1[64] = "J-boots";                              names2[64] = "Journeyman's Boots";
        names1[65] = "stein of moggok";                      names2[65] = "Stein of Moggok";
        names1[66] = "JBB";                                 names2[66] = "Jaundiced Bone Bracer";
        names1[67] = "Hiero Cloak";                          names2[67] = "Hierophant's Cloak";
        names1[68] = "";                                    names2[68] = "";
        names1[69] = "IFS";                                  names2[69] = "Imbued Fighters Staff";
        names1[70] = "AoN";                                  names2[70] = "Amulet of Necropotence";
        names1[71] = "wdh";                                 names2[71] = "White Dragon Helm";
        names1[72] = "CoF";                                 names2[72] = "Cloak of Flames";
        names1[73] = "gebs";                                names2[73] = "Golden Efreeti Boots";
        names1[74] = "pre nerf cos";                        names2[74] = "Circlet of Shadow";
        names1[75] = "pre-nerf cos";                        names2[75] = "Circlet of Shadow";
        names1[76] = "prenerf cos";                         names2[76] = "Circlet of Shadow";
        names1[77] = "pre-nerf circlet of shadow";          names2[77] = "Circlet of Shadow";
        names1[78] = "RBG";                                 names2[78] = "Runebranded Girdle";
        
        for(int i = 0; i < names1.length; i++){
            if(names1[i].isEmpty())
                continue;
            h.put(names1[i].toLowerCase().hashCode(),names2[i]);
            
        }
    }
    

    // php equivalent class
    public String substr(String s,int start, int length) {
        if (length < 0)
            length = s.length()+length;
        if (start < 0)
            start = s.length()+start;

        s = s.substring(start,length);
        return s;
    }

    // removes key from text puts back string
    public String implodeLine(String key, String text) {
        String[] split = text.split(key);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < split.length; i++) {
            sb.append(split[i]);
            if (i != split.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public String[] explodeLine(String key, String text) {
        String[] split = text.split(key);
        return split;
    }
    
    
    public String replaceItem(String itemName){


        int key = itemName.toLowerCase().hashCode();

        if (h.containsKey(key)) 
            return h.get(key);

        String[] words = itemName.split("\\s");
        StringBuilder builder = new StringBuilder();
        for (String word: words) {
          
            if(!word.isEmpty())
                word = capitalize(word);
            
            if(builder.length() != 0)
                builder.append(" ");
            
            builder.append(word);

        }
        
        itemName = builder.toString();

        return itemName;
    }
        
    private String capitalize(final String line) {
        return Character.toUpperCase(line.charAt(0)) + line.substring(1);
    }
        
        
        static int calcLD(String x, String y) {
            int[][] dp = new int[x.length() + 1][y.length() + 1];

            for (int i = 0; i <= x.length(); i++) {
                for (int j = 0; j <= y.length(); j++) {
                    if (i == 0) {
                        dp[i][j] = j;
                    }
                    else if (j == 0) {
                        dp[i][j] = i;
                    }
                    else {
                        dp[i][j] = min(dp[i - 1][j - 1] 
                         + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
                          dp[i - 1][j] + 1, 
                          dp[i][j - 1] + 1);
                    }
                }
            }

            return dp[x.length()][y.length()];
        }
    
    
    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
 
    public static int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
    
    public int processPrice(String price){
                    int p;
                    price = price.toLowerCase();
                    
                    if(price.matches("^(?i)(x).*$")) return -1;
                    
                    
                    if(price.isEmpty()) return -1;
                    if(price.endsWith(".")) return -1;
                    if(price.matches(".*(\\.).*$") && !price.endsWith("k")) return -1;
                    
                    //System.out.println(price);
                    if (price.matches(".*(k|K).*$")) {
                        if(!price.endsWith("k"))
                            return -1;
                        
                        
                        price = price.replace("k","");                       
                        if(!price.contains(".")){
                            price = price + "000";
                        }
                        else{
                            int pos = price.length()-1;
                            char[] chars = price.toCharArray();
                            for(char ch: chars){
                                if(ch == '.'){
                                    break;
                                }
                                pos -= 1;
                            }
                            price = price.replaceAll("(\\.)","");
                            int zeros = 3-pos;
                            while(zeros != 0){
                                price = price +"0";
                                zeros -=1;
                            }
                                                
                        //price = price.replaceAll("(\\.)","");
                        
                        }
                    }
                    //System.out.println("\t"+price);
                    
                    price = price.replaceAll("(\\.)",""); 
                    if (price.isEmpty()) return -1;
                    
                    p = Integer.parseInt(price);
                    return p;
    }
    
    public static String convertToTitleCaseIteratingChars(String text) {
        
        //check for roman numeral ending
        String sub = null;
        int L = text.length();
        if(L>5){
            for(int i = 3; i>=2; i--){
                sub = text.substring(L-i, L);
                    if(sub.matches("(I|V)+"))
                        return text;  
            }
        }
        
        StringBuilder converted = new StringBuilder();
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } 
            else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
        return converted.toString();
    }
        
    public String processItem(String name) {
        while(true)
        {
            
            String old = name;
            name = name.replaceAll("^(?i)((wtsell)|(wts)|(spell:)|wtt|wtb(uy)?|paying|"
                    + "buying|plat\\s|selling|full\\s|only|ea\\s|stack(s)?"
                    + "|(per)|(each)|(Ll ))","").trim();
            name = name.replaceAll("^(?i)(x|-)+","").trim();
            name = name.replaceAll("(\\s)(x|-)+$","").trim();
            name = name.replaceAll("^(?i)((\\'|-|:|_)+)","").trim();
            
            if(name.matches(old)){
                break;            
            }

                
        }
        
        int key = name.toLowerCase().hashCode();
        if(h.containsKey(key)){
            //System.out.println("\n\tUPDATING...\n\t" + itemName + "\n\t\tto\n\t" + h.get(key)+"\n");
            name = h.get(key);
        }

            return convertToTitleCaseIteratingChars(name);                    
        }   
        
        public boolean skipItem(String name){           
          
           if(name.isEmpty())  return true;

           name = convertToTitleCaseIteratingChars(name);
           int key = name.hashCode();
           if(!BST.dictionary.containsKey(key)){
               return true;
           }
            
             return false;
            
        }
         
    public static int levdistance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
    
    public  List<String> split(String str){
        return Stream.of(str.split(","))
                .map(elem -> new String(elem))
                .collect(Collectors.toList());
    }
    
}
