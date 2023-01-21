package com.example.geektrust.beans;

public class VehicleSummary {

	public String vehicleNumber;
	
	public Type type;
	
	public int totalAmountPaid;
	
	public int amountPaidByCash;
	
	public int amountPaidByFastag;
	
	public int discountGiven;

	public VehicleSummary(String vehicleNumber, Type type, int totalAmountPaid, int amountPaidByCash, int amountPaidByFastag,
			int discountGiven) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.type = type;
		this.totalAmountPaid = totalAmountPaid;
		this.amountPaidByCash = amountPaidByCash;
		this.amountPaidByFastag = amountPaidByFastag;
		this.discountGiven = discountGiven;
	}

	@Override
	public String toString() {
		return "VehicleSummary [vehicleNumber=" + vehicleNumber + ", type=" + type + ", totalAmountPaid="
				+ totalAmountPaid + ", amountPaidByCash=" + amountPaidByCash + ", amountPaidByFastag="
				+ amountPaidByFastag + ", discountGiven=" + discountGiven + "]";
	}

	
	
	
	
	
	
}
