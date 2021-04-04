package com.bookclub.bookstore.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "email" )
	private String email;

	@Column(name = "username" )
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Card> cards = new HashSet<Card>();
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="shipping_id")
	private Shipping shippingInfo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="billing_id")
	private Billing billingInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void addCard(Card card) {
		card.setUser(this);
		this.cards.add(card);
	}
	
	public void setCards(Set<Card> cards) {
//		cards.forEach(c->this.cards.add(c));
		this.cards = cards;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", role=");
		builder.append(role);
		builder.append(", cards=");
		builder.append(cards);
		builder.append(", shippingInfo=");
		builder.append(shippingInfo);
		builder.append(", billingInfo=");
		builder.append(billingInfo);
		builder.append("]");
		return builder.toString();
	}
	
	
}