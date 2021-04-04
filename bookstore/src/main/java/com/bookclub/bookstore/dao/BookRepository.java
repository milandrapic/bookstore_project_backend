package com.bookclub.bookstore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<Book> findByTitle(String title);
	
	@Transactional
    Optional<List<Book>> findByGenre(String genre);
}
