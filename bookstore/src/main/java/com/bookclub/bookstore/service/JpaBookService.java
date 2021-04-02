package com.bookclub.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.BookRepository;
import com.bookclub.bookstore.dao.CustomBookRepository;
import com.bookclub.bookstore.model.Book;


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
	
	public Book getBookById(int id) {
		return this.bookDao.findById(id).orElse(null);
	}

}
