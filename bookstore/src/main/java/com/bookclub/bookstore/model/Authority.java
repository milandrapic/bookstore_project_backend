package com.bookclub.bookstore.model;

public class Authority {
	
	public static final String[] USER_AUTHORITIES = {"read","checkout", "review"};
	public static final String[] ADMIN_AUTHORITIES = {"read","checkout","report", "review"};
	public static final String[] PARTNER_AUTHORITIES = {"read","checkout","rest", "review"};

}
