package com.example.geektrust.utils;

public class TypeSummary {

	private String carType;
	private Integer numberOfPassings;
	@Override
	public String toString() {
		return "TypeSummary [carType=" + carType + ", numberOfPassings=" + numberOfPassings + ", amountCollected="
				+ amountCollected + "]";
	}
	private Double amountCollected;
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public Integer getNumberOfPassings() {
		return numberOfPassings;
	}
	public void setNumberOfPassings(Integer numberOfPassings) {
		this.numberOfPassings = numberOfPassings;
	}
	public Double getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(Double amountCollected) {
		this.amountCollected = amountCollected;
	}
	public TypeSummary(String carType, Integer numberOfPassings, Double amountCollected) {
		super();
		this.carType = carType;
		this.numberOfPassings = numberOfPassings;
		this.amountCollected = amountCollected;
	}
	
}
