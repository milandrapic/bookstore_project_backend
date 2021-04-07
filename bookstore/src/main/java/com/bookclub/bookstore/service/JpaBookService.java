package com.bookclub.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.BookRepository;
import com.bookclub.bookstore.dao.CustomBookRepository;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.MonthlySales;
import com.bookclub.bookstore.model.TopBookSales;


@Service
public class JpaBookService {
	
	@Autowired
	private BookRepository bookDao;
	
	@Autowired
	private CustomBookRepository customBookDao;
	
	public void addBooks(List<Book> books) {
		
		for(Book b : books) {
			this.bookDao.save(b);
		}
		
	}
	
	public List<Book> getBookByGenre(String genre){
		List<Book> bl =this.customBookDao.findBookByGenre(genre);
		return bl;
	}
	
	public List<Book> getAllBooks(){
		List<Book> bl =this.bookDao.findAll();
		return bl;
	}
	
	public Book getBookById(int id) {
		return this.bookDao.findById(id).orElse(null);
	}
	
	public List<MonthlySales> getMonthlySales() {
		return this.customBookDao.monthlyStats();
	}
	
	public List<TopBookSales> getTopSales() {
		return this.customBookDao.topSales();
	}
	
	public void addView(Book b) {
		this.customBookDao.addView(b);
	}
	
	public List<Book> getTopViews() {
		return this.customBookDao.topViews();
	}

}
