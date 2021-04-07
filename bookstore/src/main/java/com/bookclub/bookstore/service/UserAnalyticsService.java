package com.bookclub.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.CustomUserRepository;
import com.bookclub.bookstore.model.UserSpentOnBook;
import com.bookclub.bookstore.model.UserTotalSpent;

@Service
public class UserAnalyticsService {
	
	@Autowired
	private CustomUserRepository userDao;
	
	public List<UserTotalSpent> userTotalBuys(){
		return this.userDao.userTotalBuys();
	}
	
	public List<UserSpentOnBook> userBookBuys(){
		return this.userDao.userBookBuys();
	}

}
