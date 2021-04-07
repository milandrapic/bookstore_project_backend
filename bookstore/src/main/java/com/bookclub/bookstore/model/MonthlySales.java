package com.bookclub.bookstore.model;

public class MonthlySales {

	Integer bookSold;
	String title;
	Integer bookId;
	String month;
	
	public Integer getBookSold() {
		return bookSold;
	}
	public void setBookSold(Integer bookSold) {
		this.bookSold = bookSold;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	@Override
	public String toString() {
		return "MonthlySales [bookSold=" + bookSold + ", title=" + title + ", month=" + month + "]";
	}
	
	
}
