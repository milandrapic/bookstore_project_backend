package com.bookclub.bookstore.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UsernamePasswordAuth extends UsernamePasswordAuthenticationToken{

	public UsernamePasswordAuth(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public UsernamePasswordAuth(Object principal, Object credentials) {
		super(principal, credentials);
	}

	
}
