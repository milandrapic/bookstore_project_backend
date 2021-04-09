package com.bookclub.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bookclub.bookstore.model.MonthlySales;
import com.bookclub.bookstore.model.UserSpentOnBook;
import com.bookclub.bookstore.model.UserTotalSpent;

@Repository
public class CustomUserRepository {
	
	
	@Autowired 
	DataSource ds;
	
	public List<UserSpentOnBook> userBookBuys(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<UserSpentOnBook> userSpent = jdbcTemplate.query(
				"select u.username, b.book_id, b.title, b.author, b.price, sum(t.quantity) as quantity,\r\n"
				+ " s.postal_code, sum(t.quantity * b.price) as spent\r\n"
				+ " from user u\r\n"
				+ "inner join shipping s on s.shipping_id = u.shipping_id\r\n"
				+ "inner join transaction t on t.user_id = u.id\r\n"
				+ "inner join book b on b.book_id = t.book_id\r\n"
				+ "group by u.username, b.book_id, b.title, b.author, b.price, s.postal_code", (rs,row)->{
					
					UserSpentOnBook userSpentOnBook = new UserSpentOnBook();
					
					String username = rs.getString("username");
					Integer bookId = rs.getInt("book_id");
					String title = rs.getString("title");
					String author = rs.getString("author");
					Double price = rs.getDouble("price");
					Integer quantity = rs.getInt("quantity");
					String postalCode = rs.getString("postal_code");
					Double spent = rs.getDouble("spent");
					
					userSpentOnBook.setUsername(this.annonymize(username));
					userSpentOnBook.setBookId(bookId);
					userSpentOnBook.setTitle(title);
					userSpentOnBook.setAuthor(author);
					userSpentOnBook.setPrice(price);
					userSpentOnBook.setQuantity(quantity);
					userSpentOnBook.setPostalCode(postalCode);
					userSpentOnBook.setSpent(spent);
					
					
					return userSpentOnBook;
				});
		return userSpent;
	}
	
	public List<UserTotalSpent> userTotalBuys(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<UserTotalSpent> userSpent = jdbcTemplate.query(
				"select u.username, sum(t.quantity) as items_bought,\r\n"
				+ " s.postal_code, sum(t.quantity * b.price) as spent\r\n"
				+ " from user u\r\n"
				+ "inner join shipping s on s.shipping_id = u.shipping_id\r\n"
				+ "inner join transaction t on t.user_id = u.id\r\n"
				+ "inner join book b on b.book_id = t.book_id\r\n"
				+ "group by u.username, s.postal_code", (rs,row)->{
					
					UserTotalSpent userSpentTotal = new UserTotalSpent();
					
					String username = rs.getString("username");
					Integer itemsBought = rs.getInt("items_bought");
					String postalCode = rs.getString("postal_code");
					Double spent = rs.getDouble("spent");
					
					userSpentTotal.setUsername(this.annonymize(username));
					userSpentTotal.setItemsBought(itemsBought);
					userSpentTotal.setPostalCode(postalCode);
					userSpentTotal.setSpent(spent);
					
					
					return userSpentTotal;
				});
		return userSpent;
	}
	
	private String annonymize(String username) {
		String res = "";
		for(int i = 0; i<username.length(); i++) {
			res+="*";
		}
		return res;
	}
	
	

}
