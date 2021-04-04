package com.bookclub.bookstore.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Transaction;
import com.bookclub.bookstore.model.User;

@Repository
public class CustomTransactionDao {
	

	//to do: 
	//get all transactions
	//get transactions by user
	//get transactions by book
	//get transactions by book and user for past month
	//get transactions by all transactions for past month
	
	//aggregate to get value sold per book
	

	@Autowired
	private EntityManager manager;
	
	public List<Transaction> getTransactionsByBook(Book b) {
		List<Transaction> transactions = 
				this.manager.createQuery("from Transaction t where t.book = :bookId",Transaction.class)
				.setParameter("bookId", b).getResultList();
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByUser(User u) {
		List<Transaction> transactions = 
				this.manager.createQuery("from Transaction t where t.user = :userId",Transaction.class)
				.setParameter("userId", u).getResultList();
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByBookPast30Days(Book b) {
		
		Date pastDate = java.sql.Date.valueOf(LocalDate.now().minusDays(30));
 
		List<Transaction> transactions = 
				this.manager.createQuery
				("from Transaction t where t.book = :bookId "
				+ "and t.date > :date"
						,Transaction.class)
				.setParameter("bookId", b)
				.setParameter("date", pastDate)
				.getResultList();
		
		System.out.println(transactions);
		
		return transactions;
	}
	
	public List<Transaction> getTransactionsByUserPast30Days(User u) {
		 
		Date pastDate = java.sql.Date.valueOf(LocalDate.now().minusDays(30));
		
		List<Transaction> transactions = 
				this.manager.createQuery
				("from Transaction t where t.user = :userId "
				+ "and t.date > :date",Transaction.class)
				.setParameter("userId", u)
				.setParameter("date", pastDate)
				.getResultList();
		
		System.out.println(transactions);
		
		return transactions;
	}
	
//	public List<Transaction> getTransactionsByMonth() {
//		 
//		Date pastDate = java.sql.Date.valueOf(LocalDate.now().minusDays(30));
//		
//		Root<Transaction> t = Criteria
//		
//		List<Transaction> transactions = 
//				this.manager.createQuery
//				("from Transaction t",Transaction.class).getResultList()
//				;
//
//		
//		System.out.println(transactions);
//		
//		return transactions;
//	}
	
	
}
