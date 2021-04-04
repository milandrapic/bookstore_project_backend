package com.bookclub.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.CustomTransactionDao;
import com.bookclub.bookstore.dao.TransactionRepository;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Transaction;
import com.bookclub.bookstore.model.User;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionDao;
	
	@Autowired
	private CustomTransactionDao customTransactionDao;
	
	public void addTransaction(Transaction t) {
		this.transactionDao.save(t);
	}
	
	public void addTransactions(List<Transaction> tlist) {
		for(Transaction t: tlist)
			this.transactionDao.save(t);
	}
	
	
	
	public List<Transaction> getTransactionsByBook(Book b) {
		
		return this.customTransactionDao.getTransactionsByBook(b);
	}
	
	public List<Transaction> getTransactionsByUser(User u) {
		
		return this.customTransactionDao.getTransactionsByUser(u);
	}
	
	public List<Transaction> getTransactionsByBookPast30Days(Book b) {
		
		return this.customTransactionDao.getTransactionsByBookPast30Days(b);
	}
	
	public List<Transaction> getTransactionsByUserPast30Days(User u) {
		
		return this.getTransactionsByUserPast30Days(u);
	}

}
