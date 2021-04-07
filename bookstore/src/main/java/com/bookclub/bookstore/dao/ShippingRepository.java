package com.bookclub.bookstore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookclub.bookstore.model.Shipping;
import com.bookclub.bookstore.model.User;

public interface ShippingRepository  extends JpaRepository<Shipping, Integer> {
	Optional<Shipping> findByUser(User user);
}
