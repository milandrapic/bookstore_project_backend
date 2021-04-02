package com.bookclub.bookstore.io;

import com.bookclub.bookstore.model.Review;

public class AddReviewRequest {
	
	private int bookId;
	
	private String username;
	
	private Review review;

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
	
	

}
