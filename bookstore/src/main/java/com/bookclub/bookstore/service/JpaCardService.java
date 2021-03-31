package com.bookclub.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.CardRepository;
import com.bookclub.bookstore.dao.UserRepository;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.User;

@Service
public class JpaCardService {

	@Autowired
	private CardRepository cardDao;
	
	@Autowired
	private UserRepository userDao;
	
	public Card addCard(String username, Card c) throws UsernameNotFoundException {
		Optional<User> o = userDao.findByUsername(username);
		
		User u = o.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
		u.addCard(c);
		c = this.cardDao.save(c);
		
		return c;
	}
}
