/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

/**
 *
 * @author Thewbacca
 */

public class multiArray {
    
    private Object[][] arr;
    int N;
    int M;
    int SIZE;
    static int row = 0;

    multiArray(int N, int M){
        this.N = N;
        this.M = M;
        arr = new String[N][M];
        this.SIZE = 0;
    }
    
    multiArray(int M)
    {
        this.M = M;
        arr = new String[M][];
    }
    
    public int getLength(){
        return M;
    }
    
    public int getSize(){
        return SIZE;
    }
    
    
    
    /**
    * Returns the matrix entry at position (row,col).  (If row < col,
    * the value is actually stored at position (col,row).)
    */
    public Object get(int row, int col ) {
            return arr[row][col];
    }
   
    public void add(Object... data)
    {
        
        for(int i = 0; i < data.length; i++)
            arr[row][i] = data[i];
        
        row += 1;
        SIZE += 1;
    }
    
    public Object[][] toObject(){
        return arr;
    }
    
    public void print(){
        for (int row = 0; row < arr.length; ++row) {
            System.out.print("[");
            for(int col = 0; col < arr[row].length; ++col) {

             if(col < arr[row].length-1)
                 System.out.print(arr[row][col] + " ");
             else
                 System.out.print(arr[row][col] + "]\n");
            }
        }
    }
       
    
}