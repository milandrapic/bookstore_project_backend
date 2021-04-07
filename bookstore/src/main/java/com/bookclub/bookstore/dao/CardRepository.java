package com.bookclub.bookstore.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Card;


@Repository
@Transactional(readOnly = true)
public interface CardRepository extends JpaRepository<Card, Integer>{


}
