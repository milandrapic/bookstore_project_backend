package com.bookclub.bookstore.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_id")
	private Integer cardId;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	@JsonIgnore
	private User user;
	
	@Column(name="card_number")
	private int cardNumber;
	
	@Column(name="expiry_date")
	private Date expiryDate;
	
	@Column(name="security_number")
	private int securityNumber;

	public Integer getCardId() {
		return cardId;
	}
	
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}



	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(int securityNumber) {
		this.securityNumber = securityNumber;
	}

	@Override
	public String toString() {
		return "Card [cardId=" + cardId + ", cardNumber=" + cardNumber + ", expiryDate=" + expiryDate
				+ ", securityNumber=" + securityNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardId == null) ? 0 : cardId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardId == null) {
			if (other.cardId != null)
				return false;
		} else if (!cardId.equals(other.cardId))
			return false;
		return true;
	}
	
}
