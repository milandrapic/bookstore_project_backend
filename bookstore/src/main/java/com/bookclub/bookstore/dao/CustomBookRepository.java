package com.bookclub.bookstore.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.MonthlySales;
import com.bookclub.bookstore.model.TopBookSales;

@Repository
public class CustomBookRepository {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired 
	DataSource ds;
	
	@Transactional
	public List<Book> findBookByGenre(String genre) {
		System.out.println("in custom repo looking for: "+ genre);
		List<Book> bl = this.entityManager.createQuery("from Book b where b.genre = :genre", Book.class).setParameter("genre", genre).getResultList();
		System.out.println(bl.size());

		return bl;
	}
	public List<MonthlySales> monthlyStats(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<MonthlySales> sales = jdbcTemplate.query("select sum(tr.quantity) as books_sold,b.book_id, b.title, DATE_FORMAT(tr.date, '%Y-%m') as mon \r\n"
				+ "from transaction tr \r\n"
				+ "inner join book b on b.book_id = tr.book_id\r\n"
				+ "group by b.title, DATE_FORMAT(date, '%Y-%m')\r\n"
				+ "order by mon desc, books_sold desc;", (rs,row)->{
					
					MonthlySales monthlySales = new MonthlySales();
					
					Integer bookSold = rs.getInt("books_sold");
					monthlySales.setBookSold(bookSold);
					String title = rs.getString("title");
					monthlySales.setTitle(title);
					Integer bookId = rs.getInt("book_id");
					monthlySales.setBookId(bookId);
					String month = rs.getString("mon");
					monthlySales.setMonth(month);
					return monthlySales;
				});
		return sales;
	}
	
	public List<TopBookSales> topSales(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<TopBookSales> sales = jdbcTemplate.query(
				"select sum(tr.quantity) as books_sold, b.title, b.book_id\r\n"
				+ "from transaction tr\r\n"
				+ "inner join book b on b.book_id = tr.book_id\r\n"
				+ "group by b.title\r\n"
				+ "order by books_sold desc\r\n"
				+ "limit 10"
				, (rs,row)->{
					
					TopBookSales topBookSales = new TopBookSales();
					
					Integer bookSold = rs.getInt("books_sold");
					topBookSales.setBooksSold(bookSold);
					String title = rs.getString("title");
					topBookSales.setTitle(title);
					Integer id = rs.getInt("book_id");
					topBookSales.setBookId(id);
					return topBookSales;
				});
		System.out.println(sales);
		return sales;
	}
	
	@Transactional
	public void addView(Book b) {
		int v = 1;
		if(b.getUserViews() != null) {
			v = b.getUserViews() + 1;
		}
		
		String qryString = "update Book b set b.userViews = :views where b.bookId = :bId";
		Query query = entityManager
        		.createQuery(qryString);
        		
        
        
        int count = query.setParameter("views",v)
        		.setParameter("bId", b.getBookId())
        		.executeUpdate();
	}
	

	@Transactional
	public List<Book> topViews(){
		List<Book> bl = this.entityManager
							.createQuery("from Book b order by b.userViews desc", Book.class)
							.setMaxResults(10)
							.getResultList();
		
		return bl;
	}
	

}
