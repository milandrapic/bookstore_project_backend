package com.bookclub.bookstore.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.bookstore.io.AddReviewRequest;
import com.bookclub.bookstore.io.CheckoutRequest;
import com.bookclub.bookstore.io.TransactionRequest;
import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.MonthlySales;
import com.bookclub.bookstore.model.Review;
import com.bookclub.bookstore.model.Shipping;
import com.bookclub.bookstore.model.TopBookSales;
import com.bookclub.bookstore.model.Transaction;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.model.UserSpentOnBook;
import com.bookclub.bookstore.model.UserTotalSpent;
import com.bookclub.bookstore.service.AddressService;
import com.bookclub.bookstore.service.JpaBookService;
import com.bookclub.bookstore.service.JpaUserDetailsService;
import com.bookclub.bookstore.service.ReviewService;
import com.bookclub.bookstore.service.TransactionService;
import com.bookclub.bookstore.service.UserAnalyticsService;



@RestController
public class TestController {

	@Autowired
	private UserAnalyticsService userAnalyticsService;
	
	@Autowired
	private JpaUserDetailsService userService;
	
	
	@Autowired
	private JpaBookService bookService;
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/getHello")
	public String getHello() {
		System.err.println("getHello called");
		return "get Hello";
	}
	
	@PostMapping("/test")
	public String postHello() {
		return "{\"message\": \"post Hello\"}";
	}

//	
//	@PostMapping("/addCard")
//	public String addCard(@RequestBody AddCardRequest request){
//		String username = request.getUsername();
//		Card c = request.getCard();
//		User u = this.userService.loadUserObjectByUsername(username);
//		c.setUser(u);
//		this.cardService.addCard(username, c);
//		return "added";
//	}
	
	@PostMapping("/addBook")
	public String addBook(@RequestBody Book book){
		System.err.println(book.getTitle());
		System.err.println(book.getAuthor());
		System.err.println(book.getPrice());
		System.err.println(book.getGenre());
		this.bookService.addBooks(Arrays.asList(book));
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
	
	@GetMapping("/catalog")
	public List<Book> catalog(){
		System.err.println("getting all books");
		List<Book> books =
				this.bookService.getAllBooks();

		return books;
	}
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBooks(){
		System.err.println("getting all books");
		List<Book> books =
				this.bookService.getAllBooks();

		return books;
	}
	
	@GetMapping("/getBookById")
	public Book getById(@RequestParam("id") int id){
		Book b = this.bookService.getBookById(id);

		return b;
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
	
	
	//to do***:
	@PostMapping("/checkout")
	public User checkout(@RequestBody CheckoutRequest request){
		List<Transaction> tl = new ArrayList<Transaction>();
		for(TransactionRequest t: request.getTransactions()) {
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
		
		Billing b = request.getBillingInfo();
		Shipping s = request.getShippingInfo();
		User u = this.userService.loadUserObjectByUsername(request.getUsername());
		Billing ub = this.addressService.getBillingByUser(u);
		Shipping us = this.addressService.getShippingByUser(u);
		if(ub != null && us != null) {
			this.addressService.setBillingA(b, ub);
			this.addressService.setShippingA(s, us);
			b= ub;
			s = us;
			
		}
		b.setUser(u);
		s.setUser(u);
		u.setBillingInfo(b);
		u.setShippingInfo(s);
		
		this.addressService.updateAddresses(s, b);
		
		
		return this.userService.updateUser(u);
	}
	
	@PostMapping("/register")
    public String register(@RequestBody User u){
		System.err.println(u);
		this.userService.register(u);
        return "registered";
    }
	
	@GetMapping("/monthlyStats")
	public List<MonthlySales> monthlyStats() {
		return this.bookService.getMonthlySales();
	}
	
	@GetMapping("/topSales")
	public List<TopBookSales> topSales() {
		return this.bookService.getTopSales();
	}
	
	@PostMapping("/addView")
	public void addBookView(@RequestParam("id") int id) {
		Book b = this.bookService.getBookById(id);
		this.bookService.addView(b);
	}
	
	@GetMapping("/topViews")
	public List<Book> topViews() {
		return this.bookService.getTopViews();
	}
	
	@GetMapping("/userTotalSpent")
	public List<UserTotalSpent> userTotalBuys(){
		return this.userAnalyticsService.userTotalBuys();
	}
	
	@GetMapping("/userTotalSpentOnBooks")
	public List<UserSpentOnBook> userBookBuys(){
		return this.userAnalyticsService.userBookBuys();
	}
	
}
