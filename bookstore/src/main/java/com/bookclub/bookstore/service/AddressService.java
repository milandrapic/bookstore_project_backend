package com.bookclub.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.BillingRepository;
import com.bookclub.bookstore.dao.ShippingRepository;
import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Shipping;
import com.bookclub.bookstore.model.User;

@Service
public class AddressService {

	@Autowired
	private BillingRepository billingDao;
	
	@Autowired
	private ShippingRepository shippingDao;
	
	public void addShipping(Shipping s) {
		this.shippingDao.save(s);
	}
	
	public void addBilling(Billing b) {
		this.billingDao.save(b);
	}
	
	public void updateAddresses(Shipping s, Billing b) {
		this.addBilling(b);
		this.addShipping(s);
	}
	
	public Shipping getShippingByUser(User u) {
		return this.shippingDao.findByUser(u).orElse(null);
	}
	
	public Billing getBillingByUser(User u) {
		return this.billingDao.findByUser(u).orElse(null);
	}
	
	public void setBillingA(Billing b, Billing ub){
		ub.setFirstName(b.getFirstName());
		ub.setLastName(b.getLastName());
		ub.setCity(b.getCity());
		ub.setProvince(b.getProvince());
		ub.setCountry(b.getCountry());
		ub.setPostalCode(b.getPostalCode());
		ub.setPhoneNumber(b.getPhoneNumber());
	}
	public void setShippingA(Shipping s, Shipping us){
		us.setFirstName(s.getFirstName());
		us.setLastName(s.getLastName());
		us.setCity(s.getCity());
		us.setProvince(s.getProvince());
		us.setCountry(s.getCountry());
		us.setPostalCode(s.getPostalCode());
		us.setPhoneNumber(s.getPhoneNumber());
	}
}
