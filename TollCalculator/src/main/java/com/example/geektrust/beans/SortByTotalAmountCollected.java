package com.example.geektrust.beans;

import java.util.Comparator;
import java.util.Map.Entry;

public class SortByTotalAmountCollected implements Comparator<Entry<TypeSummary, Integer>>{

	@Override
	public int compare(Entry<TypeSummary, Integer> o1, Entry<TypeSummary, Integer> o2) {
		// TODO Auto-generated method stub
		if(o1.getKey().totalAmountCollected > o2.getKey().totalAmountCollected) return -1;
		else if(o1.getKey().totalAmountCollected < o2.getKey().totalAmountCollected) return 1;
		else return o1.getKey().name.compareTo(o2.getKey().name);
	}



}
