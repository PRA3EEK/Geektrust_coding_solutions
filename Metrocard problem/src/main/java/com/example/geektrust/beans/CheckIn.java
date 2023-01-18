package com.example.geektrust.beans;

public class CheckIn {

	
	private String cardNumber;
	
	private String category;
	
	private String journeySource;

	private int amountPaid;
	
	private int discountGiven;
	
	private int serviceFeeOfRecharge;
	
	

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCategory() {
		return category;
	}

	public String getJourneySource() {
		return journeySource;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public int getDiscountGiven() {
		return discountGiven;
	}

	public int getServiceFeeOfRecharge() {
		return serviceFeeOfRecharge;
	}

	public CheckIn(String cardNumber, String category, String journeySource, int amountPaid, int discountGiven,
			int serviceFeeOfRecharge) {
		super();
		this.cardNumber = cardNumber;
		this.category = category;
		this.journeySource = journeySource;
		this.amountPaid = amountPaid;
		this.discountGiven = discountGiven;
		this.serviceFeeOfRecharge = serviceFeeOfRecharge;
	}

	@Override
	public String toString() {
		return "CheckIn [cardNumber=" + cardNumber + ", category=" + category + ", journeySource=" + journeySource
				+ ", amountPaid=" + amountPaid + ", discountGiven=" + discountGiven + ", serviceFeeOfRecharge="
				+ serviceFeeOfRecharge + "]";
	}

	
	
	
}
