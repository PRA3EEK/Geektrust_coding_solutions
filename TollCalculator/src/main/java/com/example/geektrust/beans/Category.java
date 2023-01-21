package com.example.geektrust.beans;

public class Category {

   public String name;

   public int travelCharge;
	
   public Category(String name, int travelCharge) {
	super();
	this.name = name;
	this.travelCharge = travelCharge;
}

@Override
public String toString() {
	return "Category [name=" + name + ", travelCharge=" + travelCharge + "]";
}

	
}
