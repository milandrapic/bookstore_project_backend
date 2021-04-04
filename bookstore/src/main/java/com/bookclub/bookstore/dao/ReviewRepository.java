package com.bookclub.bookstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookclub.bookstore.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
}
