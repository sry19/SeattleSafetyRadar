package safety.model;

import java.util.Date;

public class Ratings {
	
	protected int ratingId;
	protected Date createdTime;
	protected double rating;
	protected String comment;
	protected Users user;
	protected Neighborhoods neighborhood;
	
	public Ratings(int ratingId, Date createdTime, double rating, String comment, Users user,
			Neighborhoods neighborhood) {
		this.ratingId = ratingId;
		this.createdTime = createdTime;
		this.rating = rating;
		this.comment = comment;
		this.user = user;
		this.neighborhood = neighborhood;
	}

	public Ratings(Date createdTime, double rating, String comment, Users user, Neighborhoods neighborhood) {
		super();
		this.createdTime = createdTime;
		this.rating = rating;
		this.comment = comment;
		this.user = user;
		this.neighborhood = neighborhood;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Neighborhoods getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhoods neighborhood) {
		this.neighborhood = neighborhood;
	}
	

}
