/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Thewbacca
 */
public class Statistics extends StackLinked {
    public static double TP = 0.0;
    
    public String printStats(){
        String out = String.format(" %n: %d%nmean: %10f\nstdev: %10f\n", size(),mean(),stdev());
        return out;
    }

    public String printPrices(){
        String out = "\t";

        int col = 0;
        Iterator<Integer> it = iterator();
        while(it.hasNext())
        {
            int x = it.next();
            if(col %10 == 0)
                out += "\n\t";
            out += String.format("%5d ", x);
            col +=1;
        }
        return out + "\n";
    }

    public double mean(){
        int N = 1;
        double w;
        double num = 0.0;
        double den = 0.0;
        int x = 0;
        Iterator<Integer> it = iterator();
        
        while(it.hasNext())
        {
            x = it.next();
            w = 1/(double)(1+Math.exp(-1*(N-(size()-5))));
            num += (double)x*w;
            den += w;
            N++;
        }
        
        return num/(double)den;
    }
    
    
    public double stdev(){
        int N = 1;
        double w;
        int Nz = 1;
        double num = 0.0;
        double den = 0.0;
        double xm = mean();
        Iterator<Integer> it = iterator();
        while(it.hasNext())   
        {
            int x = it.next();
            w = 1/(double)(1+Math.exp(-1*(N-(size()-20))));
            if (w>0.0001)
                Nz++;
            num += w*Math.pow(x-xm,2);
            den += w;
            N++;
        }
        den *= (Nz-1)/(double)Nz;
        return Math.sqrt(num/(double)den);
    }
    
    
    public double RSI(){
        int up=0;
        int tup=0;
        int dwn=0;
        int tdwn=0;
        int prev = 0; 
        int now;
        int t = 1;
        Iterator<Integer> it = iterator();
        while(it.hasNext())   
        {
            int x = it.next();
            if(t==1)
                prev = x;
            if(t>1){
                now = x;
                if(prev < now){
                    up++;
                    tup++;
                }
                if(prev > now){
                    dwn++;
                    tdwn++;
                }
                
                prev = now;
            }
            t++;
        }
        //System.out.println(1+(up/(double)dwn));
        return (100-100/(double)(1+(up/(double)dwn)));
    }
    
    public double getTypicalPrice(){
        return TP;
    }
    
    
}

class StackLinked {
    private int size;
    private Node top;
    public List<Integer> list = new ArrayList<>();
    
    
    // Initial empty stack
    public StackLinked(){
        //super();
       // win.clear();
        top = null;
        size = 0;
    }
    
    // Node link class
    private class Node {
        private int value;
        private Node next;
    }
    
    public void push(int value){
        Node oldTop = top;
        top = new Node();
        top.value = value;
        top.next = oldTop;
        size++;
       // EMA = win.smooth();
        list.add(value);
    }
    
    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int value = top.value;
        top = top.next;
        size -= 1;
        
        
        return value;
    }
    
    public Statistics toStats(List<Integer> list)
    {
        Statistics stat = new  Statistics();
        Collections.reverse(list);
        
        
        for (Integer iter : list) {
            int value = Integer.parseInt(iter.toString());
            stat.push(value);
        }
        return stat;
    }
    
    public List<Integer> toArray(){
        Iterator<Integer> it = iterator();
        List<Integer> list = new ArrayList<>();
        
        while(it.hasNext())
            list.add(it.next());
        Collections.reverse(list);
        return list;
    }
    
    public Node getTop(){
        return top;
    }

    public int size(){return size; }
    
    boolean isEmpty(){ return top==null; }
    
    public Integer peek() { return top.value; }
    
    
    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     * @return an iterator to this stack that iterates through the items in LIFO order.
     */
    public Iterator<Integer> iterator() {
        return new LinkedIterator();
    }
   
    
        // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Integer> {
        private Node current = top;
        @Override
        public boolean hasNext()  { return current != null;                     }
        @Override
        public void remove()      { throw new UnsupportedOperationException();  }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer value = current.value;
            current = current.next; 
            return value;
        }
        
        
    }

    
}


