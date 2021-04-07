package com.bookclub.bookstore.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Transaction;
import com.bookclub.bookstore.model.User;

@Repository
public class CustomTransactionDao {

	@Autowired
	private EntityManager manager;
	
	public List<Transaction> getTransactionsByBook(Book b) {
		List<Transaction> transactions = 
				this.manager.createQuery("from Transaction t where t.book = :book",Transaction.class)
				.setParameter("book", b).getResultList();
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByUser(User u) {
		List<Transaction> transactions = 
				this.manager.createQuery("from Transaction t where t.user = :user",Transaction.class)
				.setParameter("user", u).getResultList();
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByBookPast30Days(Book b) {
		
		Date pastDate = java.sql.Date.valueOf(LocalDate.now().minusDays(30));
 
		List<Transaction> transactions = 
				this.manager.createQuery
				("from Transaction t where t.book = :book "
				+ "and t.date > :date"
						,Transaction.class)
				.setParameter("book", b)
				.setParameter("date", pastDate)
				.getResultList();
		
		System.out.println(transactions);
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByUserPast30Days(User u) {
		 
		Date pastDate = java.sql.Date.valueOf(LocalDate.now().minusDays(30));
		
		List<Transaction> transactions = 
				this.manager.createQuery
				("from Transaction t where t.user = :user "
				+ "and t.date > :date",Transaction.class)
				.setParameter("user", u)
				.setParameter("date", pastDate)
				.getResultList();
		
		System.out.println(transactions);
		
		return transactions;
	}
	
	
}
