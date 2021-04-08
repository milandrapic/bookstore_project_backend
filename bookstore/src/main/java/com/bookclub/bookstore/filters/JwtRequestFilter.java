package com.bookclub.bookstore.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bookclub.bookstore.authentication.UsernamePasswordAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//this handles the requests after the user has already authenticated
		//checks the validity of the Jwt with the username and
		
		String authorizationHeader = request.getHeader("Authorization");
//		Iterator<String> it = request.getHeaderNames().asIterator();
//		while(it.hasNext()) {
//			String header = it.next();
//			String headerVal = request.getHeader(header);
//			System.err.println(header+":"+headerVal);
//			
//		}
//		if(!authorizationHeader.startsWith("Bearer ") || Strings.isNullOrEmpty(authorizationHeader) ){
//			
//			filterChain.doFilter(request, response);
//			return;
//		}
		String token = authorizationHeader.replace("Bearer ", "");
		try {
			
			String secretKey = "eecs4413finalprojectsecurejwtsigningkeyeecs4413finalprojectsecurejwtsigningkeyeecs4413finalprojectsecurejwtsigningkey";
			Jws<Claims> claimsJws = Jwts.parserBuilder()
										.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
										.build()
										.parseClaimsJws(token);
			Claims body = claimsJws.getBody();
			
			String username = body.getSubject();
			
			String userSubmitted = request.getHeader("usernameSubmitted");
			
			System.out.println(username.equals(userSubmitted));
			
			if(userSubmitted != null && !username.equals(userSubmitted)) {
				throw new IllegalStateException("the username entered doesn't match the token");
			}
			
			List<Map<String,String>> authorities = (List<Map<String,String>>) body.get("authorities");
			System.out.println(authorities.toString());
			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = 
					authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority")))
					.collect(Collectors.toSet());
			
			Authentication a = new UsernamePasswordAuth(username, null, simpleGrantedAuthorities);
			
			SecurityContextHolder.getContext().setAuthentication(a);
			filterChain.doFilter(request, response);
			
		} catch (JwtException e) {
			throw new IllegalStateException("Token " + token + " cannot be trusted");
		}
		
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getServletPath();
		List<String> filteredPaths = Arrays.asList("/checkout", "/monthlyStats",
										"/topSales","/topViews", "/catalog","/userTotalSpent",
										"/userTotalSpentOnBooks","/addReview");
		
		return !filteredPaths.contains(path);
	}
	
	

}
