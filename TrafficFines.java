package assignment.bits.ds;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TrafficFines {

	public static void main(String[] args) {
		DriverHash dh=null;
		BufferedReader inputFileReader = null;
		try {
			//Creating an empty hash table
			dh=initializeHash(dh);
			//DriverHash driverHash = new DriverHash();
			PoliceTree policeTree = new PoliceTree();
			//using file reader to read input file
			inputFileReader = new BufferedReader(new FileReader("input.txt"));
			
			String fineLine;
	
			while ((fineLine = inputFileReader.readLine()) != null) {
				
				String[] fineLineParts = fineLine.split(",");
				int policeId = Integer.parseInt(fineLineParts[0].trim());
				int licenseNumber = Integer.parseInt(fineLineParts[1].trim());
				int fineAmount = Integer.parseInt(fineLineParts[2].trim());
				
				System.out.println("PoliceId:: " + policeId + ":" + "Licence No :: " + licenseNumber + ":" + "Fine amount collected:: " +fineAmount);
				
				//driverHash.insertHash(licenseNumber);
				//making hashed entries
				insertHash(dh,licenseNumber);
				//creating binary tree entries
				policeTree.insertByPoliceId(policeId, fineAmount);
				
			} 
			if(policeTree.root!=null)
			 {
			 System.out.println("\nPrint Tree:: Ordered by PoliceId");
			 
			 policeTree.printPoliceTree();
			 
			 policeTree.reorderByFineAmount();
			 System.out.println("\nPrint Tree::  Ordered by FineAmount");
			 policeTree.printPoliceTree();
			 
			 policeTree.printPolicemenWithBonus();
			 
			 policeTree.destroyPoliceTree(policeTree.root);
			
			 printViolators(dh);
			 destroyHash(dh);
			 }
			 else
			 {
				 System.out.println("The police tree is empty");
			 }
		} catch (IOException e) {
			System.out.println("Failed to read");
			e.printStackTrace();
		} finally { 
			if (inputFileReader!=null)
				try {
					inputFileReader.close();
				} catch (IOException e) {
					System.out.println("Failed to close the input file");
					e.printStackTrace();
				}
		}
		
	}
	public static DriverHash initializeHash(DriverHash dh)
	{
		dh=new DriverHash();
		return dh;
	
	}
	public static void destroyHash(DriverHash dh)
	{
		dh.makeEmpty();
	}
	public static void insertHash(DriverHash  dh,  int  lic)
	{
		dh.insert(lic, 1);
	}
	public static void printViolators(DriverHash  dh)
	{
		dh.printViolators();
	}
	

}
