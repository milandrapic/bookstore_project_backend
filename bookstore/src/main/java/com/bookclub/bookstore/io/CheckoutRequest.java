package com.bookclub.bookstore.io;

import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.Shipping;

public class CheckoutRequest {
	
	public String username;
	
	public Card card;
	
	public Shipping shippingInfo;
	
	public Billing billingInfo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Shipping getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(Shipping shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public Billing getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(Billing billingInfo) {
		this.billingInfo = billingInfo;
	}
	
	

}
