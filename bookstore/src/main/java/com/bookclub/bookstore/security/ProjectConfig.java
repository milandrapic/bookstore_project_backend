package com.bookclub.bookstore.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.bookclub.bookstore.filters.JwtRequestFilter;
import com.bookclub.bookstore.filters.UsernamePasswordAuthFilter;
import com.bookclub.bookstore.providers.UsernamePasswordAuthenticationProvider;



@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
	@Autowired
	private UsernamePasswordAuthFilter usernamePasswordAuthenticationFilter;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add authentication providers to the authentication manager
		auth.authenticationProvider(this.usernamePasswordAuthenticationProvider);
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.cors(c -> {
			CorsConfigurationSource source = request -> {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowedOrigins(List.of("http://localhost:3000"));
			config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
			config.setAllowedHeaders(List.of("*"));
			config.setExposedHeaders(List.of("*"));
			config.setAllowCredentials(true);
			config.applyPermitDefaultValues();

			return config;
			};
			c.configurationSource(source);
			});
		
		http.addFilterAt(usernamePasswordAuthenticationFilter, BasicAuthenticationFilter.class)
			.addFilterAfter(jwtRequestFilter, BasicAuthenticationFilter.class);
		
//		http.authorizeRequests()
//		.antMatchers("/register","/login")
//		.permitAll()
//		.anyRequest()
//		.authenticated();
		
		
		http.authorizeRequests().antMatchers("/checkout")
		.hasAnyAuthority("checkout")
		.antMatchers("/catalog")
		.hasAnyAuthority("rest")
		.antMatchers("/monthlyStats","/topSales","/topViews")
		.hasAuthority("report")
		.anyRequest()
		.permitAll();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
}
