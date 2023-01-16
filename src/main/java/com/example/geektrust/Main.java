package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.example.geektrust.utils.FastagUtils;
import com.example.geektrust.utils.TypeSummary;

public class Main {
	
	
	
    public static void main(String[] args) {
        
//        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            FastagUtils utils = new FastagUtils();
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            	String commandLine = sc.nextLine();
            	String[] command = commandLine.split(" ");
            	if(command[0].equals("FASTAG"))
            	{
            		utils.addFastags(command[1], (double)Integer.parseInt(command[2]));
            	}
            	else if(command[0].equals("COLLECT_TOLL"))
            	{
            		utils.collectToll(command[1], command[2]);
            	}else
            	{
            		// printing summary
            		
            		System.out.println("TOTAL_COLLECTION "+utils.getTotal_amount_collected()+" "+utils.getDiscount_given());
            		System.out.println("PAYMENT_SUMMARY "+utils.getFastagAmountCollected()+" "+utils.getCashAmountCollected());
            		
            		// sorting the list of car type summaries by first amount collected then alphabetical order of cartype.
            		List<TypeSummary> temp = utils.getAmountCollectedSummaryByType();
            		
            		Collections.sort(temp, new Comparator<TypeSummary>() {
					   @Override
					   public int compare(TypeSummary summary1, TypeSummary summary2)
					   {
						   if(summary1.getAmountCollected() > summary2.getAmountCollected()) return -1;
						   else if(summary1.getAmountCollected() < summary2.getAmountCollected()) return 1;
						   else return summary1.getCarType().compareTo(summary2.getCarType());
					   }
            		});
            		System.out.println("VEHICLE_TYPE_SUMMARY ");
            		for(TypeSummary summary:temp)
            		{
            			System.out.println(summary.getCarType()+" "+summary.getNumberOfPassings());
            		}
            	}
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }

        
    }
}
