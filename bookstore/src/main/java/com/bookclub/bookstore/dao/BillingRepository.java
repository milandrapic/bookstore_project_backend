package com.bookclub.bookstore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.User;


public interface BillingRepository  extends JpaRepository<Billing, Integer> {
	
	Optional<Billing> findByUser(User user);

}
