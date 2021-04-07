package com.bookclub.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bookclub.bookstore.dao.UserRepository;
import com.bookclub.bookstore.model.Card;
import com.bookclub.bookstore.model.Role;
import com.bookclub.bookstore.model.User;
import com.bookclub.bookstore.model.UserPrincipal;

@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository dao;
	
	@Autowired
	private PasswordEncoder bcrypt;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> o = dao.findByUsername(username);
		
		User u = o.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
		return new UserPrincipal(u);
	}
	
	public User loadUserObjectByUsername(String username) throws UsernameNotFoundException {
		Optional<User> o = dao.findByUsername(username);
		
		User u = o.orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
		return u;
	}
	
	public User register(User user) {
        String encryptPW = bcrypt.encode(user.getPassword());
        user.setPassword(encryptPW);
        user.setRole(Role.ROLE_USER);
        User newUser = this.dao.save(user);
        System.err.println(newUser);
              
        return user;
    }
	
	public User updateUser(User u) {
		return this.dao.save(u);
	}

}
