package com.example.geektrust.beans;

public class Metrocard {

	
	private String cardNumber;
	
	private int balance;

	public String getCardNumber() {
		return cardNumber;
	}

	public int getBalance() {
		return balance;
	}

	 public void setBalance(int balance)
	 {
		 this.balance = balance;
	 }

	public Metrocard(String cardNumber, int balance) {
		super();
		this.cardNumber = cardNumber;
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Metrocard [cardNumber=" + cardNumber + ", balance=" + balance + "]";
	}
	
	
}
