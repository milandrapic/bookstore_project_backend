package com.bookclub.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Review;

@Repository
public class CustomReviewRepository {
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public List<Review> getReviewsByBook(Book book){
		List<Review> reviews = 
				this.entityManager.createQuery("from Review r where r.book = :book",Review.class)
				.setParameter("book", book).getResultList();
		
		return reviews;
	}
}
