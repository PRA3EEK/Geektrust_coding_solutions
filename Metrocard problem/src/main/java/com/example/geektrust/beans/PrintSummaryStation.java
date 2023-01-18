package com.example.geektrust.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PrintSummaryStation {
    
	private int totalAmountCollected = 0;
	
	private int discountGiven = 0;
	
	private List<CheckIn> allCheckIns = new ArrayList<>();
	
	private Map<String, Integer> typeCount = new HashMap<>();

	public Map<String, Integer> getTypeCount() {
		return typeCount;
	}

	public int getTotalAmountCollected() {
		return totalAmountCollected;
	}
	
	public int getDiscountGiven() {
		return discountGiven;
	}

	public List<CheckIn> getAllCheckIns() {
		return allCheckIns;
	}

	@Override
	public String toString() {
		return "PrintSummaryCentralStation [totalAmountCollected=" + totalAmountCollected + ", discountGiven="
				+ discountGiven + ", allCheckIns=" + allCheckIns + ", typeCount=" + typeCount + "]";
	}

	public PrintSummaryStation(int totalAmountCollected, int discountGiven, List<CheckIn> allCheckIns,
			Map<String, Integer> typeCount) {
		super();
		this.totalAmountCollected = totalAmountCollected;
		this.discountGiven = discountGiven;
		this.allCheckIns = allCheckIns;
		this.typeCount = typeCount;
	}
	
	
}
