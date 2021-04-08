package com.bookclub.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.UserRepository;

@Service
public class AppService {
	
	@Autowired
	private JpaUserDetailsService userService;
	
	
	@Autowired
	private JpaBookService bookService;
	
	@Autowired
	private ReviewService reviewService;

	public JpaUserDetailsService getUserService() {
		return userService;
	}

	
	public JpaBookService getBookService() {
		return this.bookService;
	}

	public ReviewService getReviewService() {
		return reviewService;
	}

	
}
