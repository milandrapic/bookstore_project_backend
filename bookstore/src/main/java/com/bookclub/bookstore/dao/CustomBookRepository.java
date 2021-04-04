package com.bookclub.bookstore.dao;


import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Book;

@Repository
public class CustomBookRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public List<Book> findBookByGenre(String genre) {
		System.out.println("in custom repo looking for: "+ genre);
		List<Book> bl = this.entityManager.createQuery("from Book b where b.genre = :genre", Book.class).setParameter("genre", genre).getResultList();
		System.out.println(bl.size());
		for(Book b : bl) {
			System.err.println(b.getGenre());
		}
		return bl;
	}
	
	

}
