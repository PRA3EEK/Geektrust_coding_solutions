package com.example.geektrust.utilities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.example.geektrust.beans.CheckIn;
import com.example.geektrust.beans.Metrocard;
import com.example.geektrust.beans.PrintSummaryStation;
import com.example.geektrust.beans.Stations;
import com.example.geektrust.comparators.SortByTypeCount;



public class MetroCardUtility {

    /*Metro cards*/
	public List<Metrocard> metroCards = new ArrayList<>();

    public List<CheckIn> allCheckIns = new ArrayList<>();
    
    private List<CheckIn> firstTimeCheckIn = new ArrayList<>();
    
    private final double discountPercentage = 0.5;  

    private final Map<String, Integer> categoryCharges = new HashMap<String, Integer>() {/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
    	put("ADULT", 200);
    	put("SENIOR_CITIZEN", 100);
    	put("KID", 50);
    }};
 /*Method to recharge the card by providing the card number and amount.*/
    public int recharge(Metrocard metroCard, int amount)
    {
    	
    	for(Metrocard card:metroCards)
    	{
    		if(card.getCardNumber().equals(metroCard.getCardNumber()))
    		{
    			card.setBalance(card.getBalance() + amount);
    			
    			return (amount/50);
    		}
    	}
    	
    	return amount/50;
    	
    }

    /*Method used to add the metro cards while reading input from the file.*/
	public Metrocard addMetroCard(String cardNumber, int balance)
	{
	   Metrocard card = new Metrocard(cardNumber, balance);
	   
	   metroCards.add(card);
	   
	   return card;
	}
	
	/*This method is used when a person check in at a station and creates a checkin object to store the all the check in data.*/
	public void checkIn(String cardNumber, String category, String source)
	{
	    Metrocard card = null;
		int balance;
		int payingAmount = categoryCharges.get(category);
		int amountPaid;
		int discountGiven = 0;
		int charge = 0;
		boolean isReturning = false;
		
		for(Metrocard c:metroCards)
		{
			if(c.getCardNumber().equals(cardNumber))
			{
				card = c;
				break;
			}
		}
		
		balance = card.getBalance();
		
		for(CheckIn checkIn:firstTimeCheckIn)
		{
			if(checkIn.getCardNumber().equals(cardNumber))
			{
				isReturning = true;
				firstTimeCheckIn.remove(checkIn);
				break;
			}
		}
		
		/*If the person is returning then the amount to be paid will be half*/		
		if(isReturning) amountPaid = (int)(payingAmount * discountPercentage);
		else amountPaid = payingAmount;
		/*If the person is returning then the discount given will be 50% of the original charges*/
		if(isReturning) discountGiven = (int)(payingAmount * discountPercentage);
		
		
		/*If the balance of card is less then the amount to be paid then recharge the card*/
		if(balance < amountPaid)
		{
		    charge = recharge(card, amountPaid - balance);
			
			if(!(charge<0)) {
				
                amountPaid += charge;
				
			}
			
		}
		
		card.setBalance(card.getBalance() - amountPaid);
		
		CheckIn newCheckIn = new CheckIn(cardNumber, category, source, amountPaid, discountGiven, charge);
		
		allCheckIns.add(newCheckIn);
		if(!isReturning) firstTimeCheckIn.add(newCheckIn);
		 
	}
	
	//printing summary
	public void createPrintSummary()
	{
		
	    int totalCollectionCentral = 0;
	    int totalCollectionAirport = 0;
	    int discountGivenAirport = 0;
	    int discountGivenCentral = 0; 
	    
	    List<CheckIn> airportCheckIns = new ArrayList<>();
	    List<CheckIn> centralCheckIns = new ArrayList<>();
	    
	    

		
		Map<String, Integer> typeCountAirport = new HashMap<>();
		Map<String, Integer> typeCountCentral = new HashMap<>();

	    
		for(CheckIn checkin: allCheckIns)
	    {
	    	if(checkin.getJourneySource().equals("CENTRAL"))
	    	{
	    		centralCheckIns.add(checkin);
	    		totalCollectionCentral += checkin.getAmountPaid();
	    		discountGivenCentral += checkin.getDiscountGiven();
	    		if(typeCountCentral.containsKey(checkin.getCategory()))
	    		{
	    			typeCountCentral.put(checkin.getCategory(), typeCountCentral.get(checkin.getCategory()) + 1);
	    		}else
	    		{
	    			typeCountCentral.put(checkin.getCategory(), 1);
	    		}
	    		
	    	}else
	    	{
	    		airportCheckIns.add(checkin);
	    		totalCollectionAirport += checkin.getAmountPaid();
	    		discountGivenAirport += checkin.getDiscountGiven();
	    	
	    		if(typeCountAirport.containsKey(checkin.getCategory()))
	    		{
	    			typeCountAirport.put(checkin.getCategory(), typeCountAirport.get(checkin.getCategory()) + 1);
	    		}else
	    		{
	    			typeCountAirport.put(checkin.getCategory(), 1);
	    		}
	    		
	    	}
	    }
		PrintSummaryStation airportPrintSummary = new PrintSummaryStation(totalCollectionAirport, discountGivenAirport, airportCheckIns, typeCountAirport);
		PrintSummaryStation centralPrintSummary = new PrintSummaryStation(totalCollectionCentral, discountGivenCentral, centralCheckIns, typeCountCentral);


        
           
        printSummary(centralPrintSummary, Stations.CENTRAL.name());
        printSummary(airportPrintSummary, Stations.AIRPORT.name());
       
	}
	
	public void printSummary(PrintSummaryStation summary, String station) {
		
		Set<Entry<String, Integer>> entrySet = summary.getTypeCount().entrySet();
		List<Entry<String, Integer>> mapToList = new ArrayList<>(entrySet);
		Collections.sort(mapToList, new SortByTypeCount());
		System.out.println("TOTAL_COLLECTION  "+station+" "+summary.getTotalAmountCollected()+" "+summary.getDiscountGiven()+"\n"+"PASSENGER_TYPE_SUMMARY");
		
		for(Entry<String, Integer> entry:mapToList)
			System.out.println(entry.getKey()+" "+entry.getValue());
		
	}
	
	
	
}
