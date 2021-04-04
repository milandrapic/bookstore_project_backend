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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class UsernamePasswordAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	AuthenticationManager authManager;

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
		
		response.addHeader("Authorization", "Bearer " + token);
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