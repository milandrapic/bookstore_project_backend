package com.bookclub.bookstore.model;

public class UserTotalSpent {

	String username;
	
	Integer itemsBought;
	
	String postalCode;
	
	Double spent;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getItemsBought() {
		return itemsBought;
	}

	public void setItemsBought(Integer itemsBought) {
		this.itemsBought = itemsBought;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Double getSpent() {
		return spent;
	}

	public void setSpent(Double spent) {
		this.spent = spent;
	}
	
	
}
