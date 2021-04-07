package com.bookclub.bookstore.filters;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookclub.bookstore.authentication.UsernamePasswordAuth;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.service.JpaUserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JpaUserDetailsService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Object username = request.getHeader("username");
		Object password = request.getHeader("password");
		
		System.err.println("username: " + username.toString());
		System.err.println("password: " + password.toString());
		
		Authentication a = new UsernamePasswordAuth(username, password);
		a = authManager.authenticate(a);
		
		//send back the token in the response object.
		String key = "eecs4413finalprojectsecurejwtsigningkeyeecs4413finalprojectsecurejwtsigningkeyeecs4413finalprojectsecurejwtsigningkey";
		String token = Jwts.builder()
							.setSubject(a.getName())
							.claim("authorities", a.getAuthorities())
							.setIssuedAt(new Date())
							.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
							.signWith(Keys.hmacShaKeyFor(key.getBytes()))
							.compact();
		
		User u = this.userService.loadUserObjectByUsername(username.toString());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("role",u.getRole().toString());
		System.out.println("set expires to: " + java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)));
		response.setHeader("expires", java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)).toString());
		
		
		
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// should not filter requests that are not by the path /login.
		//so only filter the methods that call the /login path
		return (!request.getServletPath().equals("/login") || request.getServletPath().equals("/register"));
	}
	
	

	
}