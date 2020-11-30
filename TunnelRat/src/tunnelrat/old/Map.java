/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunnelrat;

// Java program to demonstrate implementation of our 
// own hash table with chaining for collision detection 
import java.util.Hashtable;

// Class to represent entire hash table 
class Map
{ 
    static multiArray arr;
    // Current capacity of array list 
    
    // private int numBuckets; 
    
    // Current size of array list 
    private int size; 
    
    // Define Hashtable
    Hashtable<String, Item> hashTable;

	// Constructor (Initializes capacity, size and 
	// empty chains. 
	public Map() 
	{
            hashTable = new Hashtable<>();
	} 

	public int size() { return size; } 
	public boolean isEmpty() { return size() == 0; } 

	// Adds a name value pair to hash 
	public void add(String key, int price, String seller, String time) 
	{ 
           // int hashCode = getBucketIndex(key);
            Item item;
            
            if(! hashTable.containsKey(key))
            {
                System.out.println("Adding: " + key);
                item = new Item(key, price, seller, time);
                hashTable.put(key, item);
            }
            else
            {
              // System.out.println("Updating: " + key);
               item = hashTable.get(key);
               item.update(price, seller, time);
               hashTable.replace(key, item);
            }
            
	} 
        
        public void toTable()
        {



            
        }

} 
