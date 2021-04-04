package com.bookclub.bookstore.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.stream.Collectors;



public class UserPrincipal implements UserDetails{
	
	private final User user;
	
	public UserPrincipal(User u) {
		this.user=u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] authorities = user.getRole().getAuthorities();
		List<SimpleGrantedAuthority> alist = new ArrayList<SimpleGrantedAuthority>();
		for(String a : authorities) {
			alist.add(new SimpleGrantedAuthority(a));
		}
		return alist;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User getUser() {
		return user;
	}
	
	

}

