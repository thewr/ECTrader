/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class Window {
    //FOR WINDOW
    LinkedList<Integer> elements = new LinkedList<>(); 
    LinkedList<Double> weights = new LinkedList<>();
    private Window queueInstance = null;
    private final static int DEFAULT_N = 7;
    private final int N;
    

    boolean implosion = false;
        
    public Window getStreamInstance(int N) {

            if (queueInstance == null) {
                   queueInstance = new Window(N);
            }
           elements.clear();
           return queueInstance;
    }
    
    public Window(){
        this(DEFAULT_N);
        elements.clear();
    }
    
    public int get(int t){
        return elements.get(t);
    }
    

    public Window(int maxN){
        N=maxN;
        elements.clear();
        for(int i = 0; i < N; i++)
            weights.add(hamming(i));
    }
    
    public Queue<Integer> get() {
            return elements;
    }
    
    public void clear(){
        elements.clear();
    }
    
    // Inserts the specified element into this queue if it is possible to do so
    // immediately without violating capacity restrictions
    public void add(int value) {
        if(isFull())
            elements.removeFirst();
        elements.addLast(value);       
    }
        
    // Removes a single instance of the specified element from this collection
    public void replace(int value) {
        elements.removeLast();
        elements.addLast(value);
    }
    
    public int size(){
        return elements.size();
    }
    
    public long[] corr(int p){
        int M = elements.size();
        Integer[] x = elements.toArray(new Integer[elements.size()]);
        long[] R = new long[M];
        long r = 0;
        for(int i = 0; i <= p; i++) {
          R[i] = 0;
          long temp = 0;
          for(int j = 0; j < M-i; j++) {
            temp += (long)x[j]*(long)x[j+i];
          }
          R[i] += temp;
        }
        return R;
    }
    
    // Retrieves and removes the head of this queue, or returns null if this
    // elements is empty.
    public int poll() {
            int data = elements.poll();
            return data;
    }

    // Returns true if this collection contains no elements
    public boolean isEmpty() {
            return elements.isEmpty();
    }

    // Returns the number of elements in this collection. If this collection
    // contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE
    public int getTotalSize() {
            return elements.size();
    }
   

    public double median(){
        int M = elements.size();
        Integer[] a = elements.toArray(new Integer[elements.size()]);
        Arrays.sort(a);
        if(M%2!=0)
            return (double)a[M/2];
        return (double)(a[(M-1)/2] + a[M/2]) / 2.0; 
    }
    
    public double median(double[] a)
    {
        Arrays.sort(a);
        int M = a.length;
        
        if(M%2!=0)
            return (double)a[M/2];
        return (double)(a[(M-1)/2] + a[M/2]) / 2.0; 
    }
    
    public double median(LinkedList<Integer> v)
    {
        int M = v.size();
        Integer[] a = elements.toArray(new Integer[v.size()]);
        Arrays.sort(a);        
        
        if(M%2!=0)
            return (double)a[M/2];
        return (double)(a[(M-1)/2] + a[M/2]) / 2.0; 
    }
    
    public double MAD(){
        int M = elements.size();
        Integer[] a = elements.toArray(new Integer[elements.size()]);
        Arrays.sort(a);
        double xm = median();
        double[] A = new double[M];
        for(int i = 0; i<M; i++)
            A[i] = Math.abs(a[i]-xm);
        double MAD = median(A);
        if((MAD==0.0))//&&(xm==elements.peekLast()))
            implosion = true;
        else
            implosion = false;
        return MAD;// mSk;
    }
    
    
    public boolean isFull(){
        return elements.size()==N;
    }
    
    public double triangle(int t){
        double den = (N)*(N+1)/2.0;
        return 1/den;
    }
    
    public double hamming(int t){
        return 0.54-.46*Math.cos(2*Math.PI*t/N);
    }
    
    public double blackman(int t){
        return 0.427-0.497*Math.cos(2*Math.PI*t/N)+0.077*Math.cos(4*Math.PI*t/N);
    }
    
    public void set(int value){
        elements.set(N/2, value);
    }
    
    public void set(int index, int value)
    {
        elements.set(index,value);
    }
    
    public double smooth(){
        double sumxw, sumw, w;
        sumxw = sumw = w = 0.0;
        int x;
        x = 0;
        ListIterator<Integer> it = elements.listIterator();
        int M = elements.size();
            for(int k = 0; k < M; k++){
                w = weights.get(k);
                sumw += w;
                x = it.next();
                sumxw += x*w;
            }
     //   }
        return sumxw/(double)sumw;
    }
    
    @Override
    public String toString(){
        ListIterator<Integer> it = elements.listIterator();
        String out = "";
        while(it.hasNext()){
            int value = it.next();
            out += String.format("%8d", value);
        }
        return out;
    }
}