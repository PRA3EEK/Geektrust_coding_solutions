package com.example.geektrust.beans;

public class Type {

   public String name;
	
   public Category category;

	public Type(String name, Category category) {
		super();
		this.name = name;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", category=" + category + "]";
	}
	
	
	
}
