package com.example.geektrust.beans;

public class Fastag {

	public Vehicle vehicle;
	
	public int balance;

	@Override
	public String toString() {
		return "Fastag [vehicle=" + vehicle + ", balance=" + balance + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Fastag fastag = (Fastag)obj;
		// TODO Auto-generated method stub
		return vehicle.number.equals(fastag.vehicle.number);
	}
	
	
	
}
