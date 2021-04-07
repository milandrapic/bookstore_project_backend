package com.bookclub.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Shipping {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "shipping_id")
	private Integer shippingId;
	
	@OneToOne
	@JoinColumn(name = "user")
	@JsonIgnore
	private User user;
	
	//billing or shipping, make into enum
	@Column(name = "type")
	private String type;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "province")
	private String province;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	

	public Integer getId() {
		return shippingId;
	}

	public Integer getShippingId() {
		return shippingId;
	}


	public void setShippingId(Integer shippingId) {
		this.shippingId = shippingId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shipping [shippingId=");
		builder.append(shippingId);
		builder.append(", username=");
		builder.append(user);
		builder.append(", type=");
		builder.append(type);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", city=");
		builder.append(city);
		builder.append(", province=");
		builder.append(province);
		builder.append(", country=");
		builder.append(country);
		builder.append(", postalCode=");
		builder.append(postalCode);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append("]");
		return builder.toString();
	}
	
}
