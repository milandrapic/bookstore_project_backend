package com.bookclub.bookstore.io;

import com.bookclub.bookstore.model.Card;

public class AddCardRequest {
	
	public Card card;
	
	public String username;

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
