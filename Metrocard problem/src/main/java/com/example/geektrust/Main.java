package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.beans.Metrocard;
import com.example.geektrust.utilities.MetroCardUtility;

public class Main {
    public static void main(String[] args) {
       // Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            MetroCardUtility metroUtils = new MetroCardUtility();
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            	String[] commandDetails = sc.nextLine().split(" ");
            	if(commandDetails[0].equals("BALANCE"))metroUtils.addMetroCard(commandDetails[1], Integer.parseInt(commandDetails[2]));
                else if(commandDetails[0].equals("CHECK_IN")) metroUtils.checkIn(commandDetails[1], commandDetails[2], commandDetails[3]);
            	else metroUtils.createPrintSummary();
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        	
        }
        
    }
}
