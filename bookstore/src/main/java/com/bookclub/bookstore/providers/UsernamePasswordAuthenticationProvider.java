package com.bookclub.bookstore.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bookclub.bookstore.authentication.UsernamePasswordAuth;
import com.bookclub.bookstore.service.JpaUserDetailsService;


@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		UserDetails user = userDetailsService.loadUserByUsername(username);
		
		if(passwordEncoder.matches(password, user.getPassword())) {
		return new UsernamePasswordAuth(username, password, user.getAuthorities());
		}
		throw new BadCredentialsException("Invalid Credentials Entered");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuth.class.equals(authentication);
	}

}
