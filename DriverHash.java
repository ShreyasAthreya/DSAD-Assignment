package assignment.bits.ds;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DriverHash {
	//Declaring table size as a prime number
    public int TABLE_SIZE=227;

    private int size; 

    private LinkedHashEntry[] table;
    
    //initializing empty hash
    public DriverHash() 
    {
        size = 0;
        table = new LinkedHashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    } 
    //used to destroy hash
    public void makeEmpty()
    {
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }
    //Inserting hashed entries
    public void insert(int key, int value) 
    {
    	//Hashing function
        int hash = ( key  % TABLE_SIZE);
        if (table[hash] == null)
            table[hash] = new LinkedHashEntry(key, value);
        else 
        {
            LinkedHashEntry entry = table[hash];
            while (entry.next != null && !(entry.key!=key))
                entry = entry.next;
            //If value already exists
            if (entry.key==key)
                entry.value = entry.value+value;
            else
                entry.next = new LinkedHashEntry(key, value);
        }
        size++;
    }
    
    //method to print license numbers with more than 3 violations
	public void printViolators() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		String FILENAME = "violators";
		try {
			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0; i < TABLE_SIZE; i++)
        {
            //System.out.print("\nBucket "+ (i + 1) +" : ");
            LinkedHashEntry entry = table[i];
            while (entry != null)
            {
            	//print only if violations are greater greater than 3
               if(entry.value>=3)
               {
				try {
					bw.write(entry.key+ "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
               }
               //System.out.print(entry.value +" ");
                entry = entry.next;
            }           
        }
        try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
class LinkedHashEntry 
{
    int key;
    int value;
    LinkedHashEntry next;
    LinkedHashEntry(int key, int value) 
    {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}