package com.bookclub.bookstore.io;

import java.util.List;

import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Shipping;



public class CheckoutRequest {

	String username;
	
	List<TransactionRequest> transactions;
	
	Shipping shippingInfo;
	
	Billing billingInfo;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<TransactionRequest> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionRequest> transactions) {
		this.transactions = transactions;
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
