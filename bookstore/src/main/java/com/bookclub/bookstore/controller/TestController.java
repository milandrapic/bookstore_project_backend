package com.bookclub.bookstore.controller;



import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.bookclub.bookstore.io.AddCardRequest;
import com.bookclub.bookstore.io.AddReviewRequest;
import com.bookclub.bookstore.io.BookListJsonResponse;
import com.bookclub.bookstore.io.CheckoutRequest;
import com.bookclub.bookstore.io.GetReviewsResponse;
import com.bookclub.bookstore.io.ReviewJsonResponse;
import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Book;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.Review;
import com.bookclub.bookstore.model.Shipping;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.service.JpaBookService;
import com.bookclub.bookstore.service.JpaCardService;
import com.bookclub.bookstore.service.JpaUserDetailsService;
import com.bookclub.bookstore.service.ReviewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
//@CrossOrigin(originPatterns = "*")
public class TestController {
	
	//org.apache.catalina.filters.CorsFilter
	
	private Logger logger = Logger.getLogger(TestController.class.getName());
		
	@Autowired
	private JpaUserDetailsService userService;
	
	@Autowired
	private JpaCardService cardService;
	
	@Autowired
	private JpaBookService bookService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/getHello")
	public String getHello() {
		logger.info("getHello called");
		System.err.println("getHello called");
		return "get Hello";
	}
	
	@PostMapping("/postHello")
	public String postHello() {
//		logger.info("postHello called");
//		System.err.println("postHello called");
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
	public List<BookListJsonResponse> getByGenre(@RequestParam("genre") String genre){
		System.err.println(genre);
		System.err.println("getting books");
		List<Book> books =
				this.bookService.getBookByGenre(genre);
		List<BookListJsonResponse> bookList = new ArrayList<BookListJsonResponse>();
		
		for(Book b: books) {
			GetReviewsResponse reviews = new GetReviewsResponse();
			reviews.convertList(b.getReviews());
			BookListJsonResponse book = 
					new BookListJsonResponse(b.getId(), b.getTitle(),
							b.getGenre(), b.getAuthor(),
							b.getPrice(), reviews.getReviews());
			bookList.add(book);
			
		}
		
		return bookList;
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
	public List<ReviewJsonResponse> getReviews(@RequestParam("bookId") int bookId){
		Book b = this.bookService.getBookById(bookId);
		GetReviewsResponse response = new GetReviewsResponse();
		response.convertList(b.getReviews());
		for(Review r : b.getReviews()) {
		System.out.println(r.getUser().getUsername());
		System.out.println(r.getRating());
		System.out.println(r.getReviewText());
		}
		//return this.reviewService.getReviewsByBook(b);
		return response.getReviews();
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
	
//public String addInfo(@RequestBody  user){
//		
//		System.out.println("username: " + user.getUsername() + " controller");
//		System.out.println("password: " + user.getPassword() + " controller");
//		this.service.register(user);
//        
//        return "registered";
//    }
}
