package com.bookclub.bookstore.io;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.bookstore.model.Review;

public class GetReviewsResponse {
	
	private List<ReviewJsonResponse> reviews;
	
	

	public GetReviewsResponse() {
		this.reviews = new ArrayList<ReviewJsonResponse>();
	}

	public List<ReviewJsonResponse> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewJsonResponse> reviews) {
		this.reviews = reviews;
	}
	
	public void convertList(List<Review> reviews) {
		for(Review r : reviews) {
			this.reviews
			.add(new ReviewJsonResponse(r.getId(),
					r.getRating(), r.getReviewText(),
					r.getUser().getUsername(), r.getBook().getId()));
		}
	}

}
