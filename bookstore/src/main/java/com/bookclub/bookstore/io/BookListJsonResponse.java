package com.bookclub.bookstore.io;

import java.util.List;


public class BookListJsonResponse {
		
	private Integer id;
	

	private String title;
	

	private String genre;
	

	private String author;
	

	private double price;
	

	private List<ReviewJsonResponse> reviews;
	
	


	public BookListJsonResponse(Integer id, String title, String genre, String author, double price,
			List<ReviewJsonResponse> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.author = author;
		this.price = price;
		this.reviews = reviews;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<ReviewJsonResponse> getReviews() {
		return reviews;
	}


	public void setReviews(List<ReviewJsonResponse> reviews) {
		this.reviews = reviews;
	}
	
	
}
