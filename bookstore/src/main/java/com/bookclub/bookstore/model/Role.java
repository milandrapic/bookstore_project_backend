package com.bookclub.bookstore.model;

public enum Role {
		
	ROLE_USER(Authority.USER_AUTHORITIES),
	ROLE_ADMIN(Authority.ADMIN_AUTHORITIES),
	ROLE_PARTNER(Authority.PARTNER_AUTHORITIES);
	
	private String[] authorities;
	
	Role(String[] authorities){
		this.authorities = authorities;
	}

	public String[] getAuthorities() {
		return authorities;
	}
	
	
}
