package com.example.geektrust.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.example.geektrust.beans.Category;
import com.example.geektrust.beans.Fastag;
import com.example.geektrust.beans.SortByTotalAmountCollected;
import com.example.geektrust.beans.Type;
import com.example.geektrust.beans.TypeSummary;
import com.example.geektrust.beans.Vehicle;
import com.example.geektrust.beans.VehicleSummary;
//import com.sun.tools.javac.code.Attribute.Array;

public class TollUtility {
	
	private Category category1 = new Category("Heavy vehicle", 200);
	private Category category2 = new Category("Light vehicle", 100);
	private Category category3 = new Category("Two wheeler", 50);

	private List<Category> categories = Arrays.asList(new Category[] {category1, category2, category3});
	
	private List<Type> vehicleTypes = Arrays.asList(new Type[] {new Type("TRUCK", category1), new Type("BUS", category1), new Type("VAN", category2), new Type("CAR", category2), new Type("RICKSHAW", category2), new Type("SCOOTER", category3), new Type("MOTORBIKE", category3)});
	
	private Set<Fastag> fastags = new HashSet<>();
	
	private List<String> vehiclesPassedForFirst = new ArrayList<>();
	
	private List<VehicleSummary> summaries = new ArrayList<>();
	
	private Map<TypeSummary, Integer> typeSummaries = new HashMap<>();

	private final int cashPaymentFee = 40;
	
	private final double discountRatio = 0.5;
	
	private int totalAmountCollected;
	
	private int totalDiscountGiven;
	
	private int amountCollectedByCash;
	
	private int amountCollectedByFastag;
	
	public void processInput(String... commandLine)
	{
		if(commandLine[0].equals("FASTAG")) addFastag(commandLine[1], Integer.parseInt(commandLine[2]));
		else if(commandLine[0].equals("COLLECT_TOLL")) collectToll(commandLine[1], commandLine[2]);
		else print();
	}
	
	public void addFastag(String carNumber, int balance)
	{
	    Vehicle vehicle = new Vehicle();
	    Fastag fastag = new Fastag();
	    fastag.vehicle = vehicle;
	    fastag.balance = balance;
	    vehicle.number = carNumber;
	    vehicle.fastag = fastag;
	    
	    
	    String firstLetterOfNumber = carNumber.split("")[0];
	    
	    for(Type type:vehicleTypes)
	    {
	    	if(type.name.split("")[0].equals(firstLetterOfNumber)) vehicle.vehicleType = type;
	    }
	    
	    fastags.add(fastag);
	}
	
	public void collectToll(String typeName, String vehicleNumber)
	{
		VehicleSummary summary = vehiclePassingSummary(typeName, vehicleNumber);
		summaries.add(summary);
		boolean isAvailable = false;
		for(TypeSummary typeSummary:typeSummaries.keySet())
		{
			if(typeSummary.name.equals(summary.type.name))
			{
				typeSummary.totalAmountCollected += (summary.amountPaidByCash + summary.amountPaidByFastag);
				typeSummaries.put(typeSummary, typeSummaries.get(typeSummary) + 1);
				isAvailable = true;
			}
		
		}
		if(!isAvailable)typeSummaries.put(new TypeSummary(summary.type.name, summary.amountPaidByCash + summary.amountPaidByFastag), 1);
//		System.out.println(typeSummaries);
		
	}
	
	public void createFirstPassSummary(String vehicleNumber)
	{
		boolean flag = false;
		for(int i=0; i<vehiclesPassedForFirst.size(); i++)
		{
			if(vehiclesPassedForFirst.get(i).equals(vehicleNumber)) {
				flag = true;
				vehiclesPassedForFirst.remove(vehiclesPassedForFirst.get(i));
			}
		}
		if(!flag)vehiclesPassedForFirst.add(vehicleNumber);
//		System.out.println(vehiclesPassedForFirst);
		
	}
	
	public VehicleSummary vehiclePassingSummary(String typeName, String vehicleNumber)
	{
		/*create the summary for the vehicles which are passing for the first time and we also have to check
		that the vehicle which is passing has a fastag or not.*/
		Type type = getType(typeName); 
		Fastag fastag = getFastag(vehicleNumber);
        int amountToBePaid, amountPaidByCash, amountPaidByFastag, discountGiven;
        
        if(isReturning(vehicleNumber)) amountToBePaid = (int)(type.category.travelCharge * discountRatio);
        else amountToBePaid = type.category.travelCharge;
        if(isReturning(vehicleNumber))  discountGiven = (int)(type.category.travelCharge * discountRatio);
        else discountGiven = 0;
		
	if(fastag!=null) {
		if(amountToBePaid > fastag.balance) { 
			amountPaidByFastag = fastag.balance;
			amountPaidByCash = (amountToBePaid - fastag.balance) + cashPaymentFee; 
            fastag.balance = 0;
		}else {
			amountPaidByFastag = amountToBePaid;
			amountPaidByCash = 0;
			fastag.balance = (fastag.balance - amountToBePaid);
		}
	}else {
		amountPaidByFastag = 0;
		amountPaidByCash = amountToBePaid + cashPaymentFee;
	}  
	    createFirstPassSummary(vehicleNumber);	
	    addFinalDetails(amountPaidByFastag + amountPaidByCash, amountPaidByCash, amountPaidByFastag, discountGiven);
		return new VehicleSummary(vehicleNumber, type,amountPaidByFastag + amountPaidByCash, amountPaidByCash, amountPaidByFastag, discountGiven);
	}
	
	public void addFinalDetails(int totalAmount, int cashAmount, int amountFastag, int discountGiven) {
		totalAmountCollected += totalAmount;
		totalDiscountGiven += discountGiven;
		amountCollectedByCash += cashAmount;
		amountCollectedByFastag += amountFastag;
	}
	
	public Fastag getFastag(String vehicleNumber)
	{
		
		for(Fastag fastag:fastags)
		{
			if(fastag.vehicle.number.equals(vehicleNumber)) return fastag;
		}
		
		return null;
	}
	
	public Type getType(String typeName)
	{
		for(Type type:vehicleTypes) {
			if(type.name.equals(typeName)) return type;
		}
		return null;
	}

	public boolean isReturning(String vehicleNumber)
	{
		
	 for(String number:vehiclesPassedForFirst)
	 {
		 if(number.equals(vehicleNumber)) return true;
	 }
	 return false;
		
	}
	
	public void print()
	{
		System.out.println("TOTAL_COLLECTION "+totalAmountCollected+" "+totalDiscountGiven);
		System.out.println("PAYMENT_SUMMARY "+amountCollectedByFastag+" "+amountCollectedByCash);
		System.out.println("VEHICLE_TYPE_SUMMARY");
		Set<Entry<TypeSummary, Integer>> set = typeSummaries.entrySet();
		List<Entry<TypeSummary, Integer>> mapToList = new ArrayList<>(set);
		Collections.sort(mapToList, new SortByTotalAmountCollected());
		for(Entry<TypeSummary, Integer> typeSummary:mapToList)
		{
			System.out.println(typeSummary.getKey().name+" "+typeSummaries.get(typeSummary.getKey()));
		}
	}
	
	
}
