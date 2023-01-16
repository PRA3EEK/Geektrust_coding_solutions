package com.example.geektrust.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FastagUtils {

	
	private double discountGiven = 0;
	
	public double getDiscount_given() {
		return discountGiven;
	}
	
	/* Toll amount collected by a fastag. */
	private double fastagAmountCollected = 0;
	/* Toll amount collected by cash. */
	private double cashAmountCollected = 0;

	public double getFastagAmountCollected() {
		return fastagAmountCollected;
	}

	public double getCashAmountCollected() {
		return cashAmountCollected;
	}

	private double totalAmountCollected = 0;
	
 /*Getter to get the value of the total amount collected outside the class */
	public double getTotal_amount_collected() {
		return totalAmountCollected;
	}
	
	public List<TypeSummary> getAmountCollectedSummaryByType() {
		return amountCollectedSummaryByType;
	}

	/*  Fastags Map contains the vehicle number and the balance in their Fastag.
        These fastags are added by using add fastag method of this class while reading the input from a file.
    */	
 	private Map<String, Double> fastags = new HashMap<>();
	
	private final Map<String, String> vehicleTypes = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

	{
		put("TRUCK", "Heavy vehicle");
		put("BUS", "Heavy vehicle");
		put("VAN", "Light vehicle");
		put("CAR", "Light vehicle");
		put("RIKSHAW", "Light vehicle");
		put("SCOOTER", "Two vehicle");
		put("MOTORBIKE", "Two vehicle");
	}};
	
//	vehicleCategory contains the categories as the key and charge of that category as value.
	private final Map<String, Double> vehicleCategories = new HashMap<String, Double>() {
		 
		private static final long serialVersionUID = 1L;
		{
		put("Heavy vehicle", 200.0);
		put("Light vehicle", 100.0);
		put("Two wheeler", 50.0);
	
		
	}};
//	vehiclePassed keeps the record of the vehicles that passed the toll (means they are not returning).
	List<String> vehiclePassed = new ArrayList<>();

	private List<TypeSummary> amountCollectedSummaryByType = new ArrayList<>();
	
	
	public Map<String, Double> addFastags(String vehicleNumber, double amount)
	{
       
		fastags.put(vehicleNumber, amount);
		
		return fastags;
		
	}
	
	public boolean hasFastag(String vehicleNumber)
	{
		
		return fastags.containsKey(vehicleNumber);
		
	}
	
	
	public void collectToll(String vehicleType, String vehicleNumber)
	{
		String vehicleCategory = getVehicleCategory(vehicleType);
		
		double tollCollected = 0;
	
		
		if(!vehiclePassed.contains(vehicleNumber))
		{
           //iIf the vehicle is passing the toll for the first time then it is using the passingToll method to calculate the toll.
			tollCollected = passingToll(vehicleCategory, vehicleNumber);
			totalAmountCollected += tollCollected;
			vehiclePassed.add(vehicleNumber);
		}else
		{
          //If the vehicle is returning then the toll is calculated using the returningToll method of this class.
			tollCollected = returningToll(vehicleCategory, vehicleNumber);
			totalAmountCollected += tollCollected;
			vehiclePassed.remove(vehicleNumber);
		}
		
	
		// Maintaining the amount summary, number of times a car type has passed the toll, and the total amount payed by the vehicle so far using a class TypeSummary.
		if(amountCollectedSummaryByType.size() > 0)
		{			
			boolean flag = false;
			for(TypeSummary summary:amountCollectedSummaryByType)
			{
				if(summary.getCarType().equals(vehicleType))
				{
					summary.setAmountCollected(summary.getAmountCollected() + tollCollected);
					summary.setNumberOfPassings(summary.getNumberOfPassings() + 1);
					flag = true;
				}
			}
			if(!flag)
			{
				amountCollectedSummaryByType.add(new TypeSummary(vehicleType, 1, tollCollected));
			}
		}else
		{
			amountCollectedSummaryByType.add(new TypeSummary(vehicleType, 1, tollCollected));
		}
		
	}
	
	
	// return the calculated toll for a vehicle if it is passing the toll.
	public double passingToll(String vehicleCategory, String vehicleNumber)
	{
		double toll = 0;
		
		double payingAmount = vehicleCategories.get(vehicleCategory);
		if(hasFastag(vehicleNumber))
		{
			
			double fastagAmount = fastags.get(vehicleNumber);
			if(fastagAmount < payingAmount)
			{
				//balance in fastag is less.
				payingAmount += 40;
				fastagAmountCollected += fastagAmount;
				cashAmountCollected += payingAmount - fastagAmount;
				fastags.put(vehicleNumber, 0.0);
				toll = payingAmount;
				
			}else
			{
				fastagAmountCollected += payingAmount;
				fastags.put(vehicleNumber, fastagAmount - payingAmount);
				toll = payingAmount;
			}
			
		}else
		{
			//if a vehicle doesn't have a fastag.
			
			payingAmount += 40; 
			cashAmountCollected += payingAmount;
			toll = payingAmount;
		}
		
		return toll;
		
	}
	// returns the calculated toll amount of the returning journey of a vehicle.
	public double returningToll(String vehicleCategory, String vehicleNumber)
	{
		
		double toll = 0;
		
		double payingAmount = vehicleCategories.get(vehicleCategory) / 2;
		discountGiven += payingAmount;
		if(hasFastag(vehicleCategory))
		{
			double fastagAmount = fastags.get(vehicleNumber);
		   if(fastagAmount < payingAmount)
		   {
			    payingAmount += 40;
				fastagAmountCollected += fastagAmount;
				cashAmountCollected += payingAmount - fastagAmount;
				fastags.put(vehicleNumber, 0.0);
				toll = payingAmount;
		   }else
		   {
			    fastagAmountCollected += payingAmount;
				fastags.put(vehicleNumber, fastagAmount - payingAmount);
				toll = payingAmount;
		   }
			
		}else
		{
			payingAmount += 40; 
			cashAmountCollected += payingAmount;
			toll = payingAmount;	
		}
		
		return toll;
		
	}
	//returns the vehicle category to which the passed vehicle type belongs
	public String getVehicleCategory(String vehicleType)
	{
		
		return vehicleTypes.get(vehicleType);
		
	}
	
}
