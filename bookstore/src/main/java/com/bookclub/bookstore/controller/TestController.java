package com.bookclub.bookstore.controller;




import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.bookstore.io.AddCardRequest;
import com.bookclub.bookstore.io.AddReviewRequest;
import com.bookclub.bookstore.io.TransactionRequest;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.MonthlySales;
import com.bookclub.bookstore.model.Review;
import com.bookclub.bookstore.model.Transaction;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.service.JpaBookService;
import com.bookclub.bookstore.service.JpaCardService;
import com.bookclub.bookstore.service.JpaUserDetailsService;
import com.bookclub.bookstore.service.ReviewService;
import com.bookclub.bookstore.service.TransactionService;

import javassist.expr.NewArray;



@RestController
public class TestController {

	@Autowired 
	DataSource ds;
	
	@Autowired
	private JpaUserDetailsService userService;
	
	@Autowired
	private JpaCardService cardService;
	
	@Autowired
	private JpaBookService bookService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/getHello")
	public String getHello() {
		System.err.println("getHello called");
		return "get Hello";
	}
	
	@PostMapping("/postHello")
	public String postHello() {
		return "{\"message\": \"post Hello\"}";
	}

	
	@PostMapping("/addCard")
	public String addCard(@RequestBody AddCardRequest request){
		String username = request.getUsername();
		Card c = request.getCard();
		User u = this.userService.loadUserObjectByUsername(username);
		c.setUser(u);
		this.cardService.addCard(username, c);
		return "added";
	}
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody Book book){
		System.err.println(book.getTitle());
		System.err.println(book.getAuthor());
		System.err.println(book.getPrice());
		System.err.println(book.getGenre());
		this.bookService.addBooks(List.of(book));
		return "added";
	}
	
	@PostMapping("/addBooks")
	public String addBooks(@RequestBody List<Book> books){

		this.bookService.addBooks(books);
		return "added";
	}
	
	@GetMapping("/getByGenre")
	public List<Book> getByGenre(@RequestParam("genre") String genre){
		System.err.println(genre);
		System.err.println("getting books");
		List<Book> books =
				this.bookService.getBookByGenre(genre);

		return books;
	}
	
	@PostMapping("/addReview")
	public void addReviewToBook(@RequestBody AddReviewRequest request) {
		int bookId = request.getBookId();
		String username = request.getUsername();
		Review r = request.getReview();
		User u = this.userService.loadUserObjectByUsername(username);
		Book b = this.bookService.getBookById(bookId);
		r.setBook(b);
		r.setUser(u);
		this.reviewService.addReview(r);
		
	}
	
	@GetMapping("/getReviews")
	public List<Review> getReviews(@RequestParam("bookId") int bookId){
		Book b = this.bookService.getBookById(bookId);
		return b.getReviews();
	}
	
	@PostMapping("addTransactions")
	public void addTransactions(@RequestBody List<TransactionRequest> transactions) {
		List<Transaction> tl = new ArrayList<Transaction>();
		for(TransactionRequest t: transactions) {
			Transaction nt = new Transaction();
			nt.setQuantity(t.getQuantity());
			Book b = this.bookService.getBookById(t.getBookId());
			User u = this.userService.loadUserObjectByUsername(t.getUsername());
			Date date = java.sql.Date.valueOf(LocalDate.now());
			nt.setBookId(b);
			nt.setUser(u);
			nt.setDate(date);
			tl.add(nt);
		}
		this.transactionService.addTransactions(tl);
	}
	
	@GetMapping("getUserTransactions")
	public List<Transaction> getUserTransactions(@RequestParam("username") String username){
		User u = this.userService.loadUserObjectByUsername(username);
		return this.transactionService.getTransactionsByUser(u);
	}
	
	@GetMapping("monthUserTransactions")
	public List<Transaction> monthUserTransactions(@RequestParam("username") String username){
		User u = this.userService.loadUserObjectByUsername(username);
		return this.transactionService.getTransactionsByUserPast30Days(u);
	}
	
	@GetMapping("getBookTransactions")
	public List<Transaction> getBookTransactions(@RequestParam("bookId") int bookId){
		Book b = this.bookService.getBookById(bookId);
		return this.transactionService.getTransactionsByBook(b);
	}
	
	@GetMapping("monthBookTransactions")
	public List<Transaction> monthBookTransactions(@RequestParam("bookId") int bookId){
		Book b = this.bookService.getBookById(bookId);
		return this.transactionService.getTransactionsByBookPast30Days(b);
	}
	
//	to do***:
//	@PostMapping("/checkout")
//	public String checkout(@RequestBody CheckoutRequest request){
//		String username = request.getUsername();
//		Card c = request.getCard();
//		Billing b = request.getBillingInfo();
//		Shipping s = request.getShippingInfo();
//		User u = this.userService.loadUserObjectByUsername(username);
//		
//		c.setUser(u);
//		
//		this.cardService.addCard(username, c);
//		return "added";
//	}
	
	@PostMapping("/register")
    public String register(@RequestBody User u){
		System.err.println(u);
		this.userService.register(u);
        return "registered";
    }
	
	@GetMapping("/stats")
	public List<MonthlySales> stats() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		List<MonthlySales> sales = jdbcTemplate.query("select sum(tr.quantity) as books_sold, b.title, DATE_FORMAT(tr.date, '%Y-%m') as mon \r\n"
				+ "from demo_db.transaction tr \r\n"
				+ "inner join demo_db.book b on b.book_id = tr.book_id\r\n"
				+ "group by b.title, DATE_FORMAT(date, '%Y-%m')\r\n"
				+ "order by mon;", (rs,row)->{
					
					MonthlySales monthlySales = new MonthlySales();
					
					Integer bookSold = rs.getInt("books_sold");
					monthlySales.setBookSold(bookSold);
					String title = rs.getString("title");
					monthlySales.setTitle(title);
					String month = rs.getString("mon");
					monthlySales.setMonth(month);
					return monthlySales;
				});
		return sales;
	}
	
//public String addInfo(@RequestBody  user){
//		
//		System.out.println("username: " + user.getUsername() + " controller");
//		System.out.println("password: " + user.getPassword() + " controller");
//		this.service.register(user);
//        
//        return "registered";
//    }
}
