package com.bookclub.bookstore.io;



public class ReviewJsonResponse {
	

	private Integer id;
	

	private double rating;
	

	private String reviewText;

	private String username;
	
	private int bookId;
	
	

	public ReviewJsonResponse(Integer id, double rating, String reviewText, String username, int bookId) {
		super();
		this.id = id;
		this.rating = rating;
		this.reviewText = reviewText;
		this.username = username;
		this.bookId = bookId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

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
	
	

}
