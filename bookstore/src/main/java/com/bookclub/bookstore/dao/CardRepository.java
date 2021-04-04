package com.bookclub.bookstore.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.User;

@Repository
@Transactional(readOnly = true)
public interface CardRepository extends JpaRepository<Card, Integer>{

	Optional<Card> findByCardNumber(String cardNumber);
}
