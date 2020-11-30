/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.item;

/**
 *
 * @author Thewbacca
 */
public class Data {
    public int price;
    String time;
    double roll;
    public double mean = 0.0d;
    public double variance = 0.0d;
    public int n_elements = 0;
    
    //public Data(){};
    
    public Data(int price, String time){
        this.price = price;
        this.time = time;
    }
    
    public Data(Data other){
        this.price = other.price;
        this.time = other.time;
    }

    public Data(Data other, double roll)
    {
        this.roll = roll;
        this.price = other.price;
        this.time = other.time;
    }
    
    public int getPrice(){
        return price;
    }
    
    public void setPrice(int price){
        this.price = price;
    }
    
    public void update(int price){
        variance = ((variance + Math.pow(mean,2)) * n_elements + Math.pow(price, 2)) / (n_elements + 1);
        mean = ((mean * n_elements) + price) / (n_elements + 1);
        variance = variance - Math.pow(mean,2);
        n_elements += 1;
    }
    
    

}