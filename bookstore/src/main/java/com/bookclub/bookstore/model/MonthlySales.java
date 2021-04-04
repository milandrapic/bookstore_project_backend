package com.bookclub.bookstore.model;

public class MonthlySales {

	Integer bookSold;
	String title;
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
	@Override
	public String toString() {
		return "MonthlySales [bookSold=" + bookSold + ", title=" + title + ", month=" + month + "]";
	}
	
	
}
