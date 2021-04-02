package com.bookclub.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.CardRepository;
import com.bookclub.bookstore.dao.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private JpaUserDetailsService userService;
	
	@Autowired
	private JpaCardService cardService;
	
	@Autowired
	private JpaBookService bookService;

	public JpaUserDetailsService getUserService() {
		return userService;
	}

	public JpaCardService getCardService() {
		return cardService;
	}
	
	public JpaBookService getBookService() {
		return this.bookService;
	}

}
