package com.example.geektrust.comparators;

import java.util.Comparator;
import java.util.Map.Entry;

public class SortByTypeCount implements Comparator<Entry<String, Integer>>{

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		// TODO Auto-generated method stub
		if(o1.getValue() > o2.getValue()) return -1;
		else if(o1.getValue() < o2.getValue()) return 1;
		else return o1.getKey().compareTo(o2.getKey());
	}



}
