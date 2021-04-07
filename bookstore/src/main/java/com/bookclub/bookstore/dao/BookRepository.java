package com.bookclub.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bookclub.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {


	
}
