package com.bookclub.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.CustomReviewRepository;
import com.bookclub.bookstore.dao.ReviewRepository;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Review;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewDao;
	
	@Autowired
	private CustomReviewRepository customReviewDao;
	
	public void addReview(Review r) {
		this.reviewDao.save(r);
	}
	
	public List<Review> getReviewsByBook(Book book){
		return this.customReviewDao.getReviewsByBook(book);
	}
}
