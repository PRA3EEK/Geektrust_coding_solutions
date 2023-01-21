package com.example.geektrust.beans;

public class TypeSummary implements Comparable<TypeSummary>{

	public String name;
	
	public int totalAmountCollected;

	@Override
	public int compareTo(TypeSummary o) {
		// TODO Auto-generated method stub
		if(this.totalAmountCollected > o.totalAmountCollected) return -1;
		else if(this.totalAmountCollected < o.totalAmountCollected) return 1;
		else return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "TypeSummary [name=" + name + ", totalAmountCollected=" + totalAmountCollected + "]";
	}

	public TypeSummary(String name, int totalAmountCollected) {
		super();
		this.name = name;
		this.totalAmountCollected = totalAmountCollected;
	}

	
	
	
	
}
