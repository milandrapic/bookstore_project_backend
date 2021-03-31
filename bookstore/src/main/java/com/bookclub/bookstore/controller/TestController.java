package com.bookclub.bookstore.controller;



import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CorsFilter;

import com.bookclub.bookstore.io.AddCardRequest;
import com.bookclub.bookstore.io.CheckoutRequest;
import com.bookclub.bookstore.model.Billing;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.Shipping;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.service.JpaCardService;
import com.bookclub.bookstore.service.JpaUserDetailsService;
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
	
	@GetMapping("/getHello")
	public String getHello() {
		logger.info("getHello called");
		System.err.println("getHello called");
		return "get Hello";
	}
	
	@PostMapping("/postHello")
	public String postHello() {
		return "post Hello";
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
		
//		ObjectMapper mapper = new ObjectMapper();
//		Map<String,Object> shipping;
//		try {
//			shipping = mapper.readValue((u.get("shippingInfo")), Map.class);
//			System.out.println(u.get("card"));
//			System.out.println(u.get("shippingInfo"));
//			System.out.println(u.get("billingInfo"));
//			System.out.println("OBJECTS");
//			//System.out.println(ship.getFirstName());
//		} catch (Exception e) {
//			System.out.println("unlucky lad");
//			e.printStackTrace();
//		}
		
//		System.out.println("username: " + user.getUsername() + " controller");
//		System.out.println("password: " + user.getPassword() + " controller");
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
