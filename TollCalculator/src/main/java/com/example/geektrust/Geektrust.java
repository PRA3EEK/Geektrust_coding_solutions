package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.utils.TollUtility;


public class Geektrust {
	
	
	
    public static void main(String[] args) {
        
//        Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            TollUtility tollUtil = new TollUtility();
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
            	String[] command = sc.nextLine().split(" ");
            	tollUtil.processInput(command);
            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        	System.out.println(e.getMessage());
        }

        
    }
}
