package safety.dal;

import safety.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import blog.dal.RestaurantsDao;
import blog.dal.UsersDao;
import blog.model.Restaurants;
import blog.model.Reviews;
import blog.model.Users;

public class RatingsDao {
	
	protected ConnectionManager connectionManager;

	// Single pattern: instantiation is limited to one object.
	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRating = "INSERT INTO Ratings(CreatedTime,Rating,Comment,UserName,MCPP) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRating);
			insertStmt.setTimestamp(1, new Timestamp(rating.getCreatedTime().getTime()));
			insertStmt.setDouble(2, rating.getRating());
			insertStmt.setString(3, rating.getComment());
			insertStmt.setString(4, rating.getUser().getUserName());
			insertStmt.setString(5, rating.getNeighborhood().getMCPP());
			
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			return rating;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Ratings getRatingByRatingId {
		String selectRating =
				"SELECT RatingID,CreatedTime,Rating,Comment,UserName,MCPP " +
				"FROM Ratings " +
				"WHERE RatingID=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectReview);
				selectStmt.setInt(1, reviewId);
				results = selectStmt.executeQuery();
				UsersDao usersDao = UsersDao.getInstance();
				RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
				if(results.next()) {
					int resultReviewId = results.getInt("ReviewId");
					String content = results.getString("Content");
					Time created =  new Time(results.getTimestamp("Created").getTime());
					double rating = results.getDouble("Rating");
					String userName = results.getString("UserName");
					int restaurantId = results.getInt("RestaurantId");
					
					Users user = usersDao.getUserByUserName(userName);
					Restaurants restaurant = restaurantsDao.getRestaurantById(restaurantId);
					Reviews review = new Reviews(resultReviewId, created,content,
						 rating, user,restaurant);
					return review;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(connection != null) {
					connection.close();
				}
				if(selectStmt != null) {
					selectStmt.close();
				}
				if(results != null) {
					results.close();
				}
			}
			return null;
		
	}
	public List<Ratings> getRatingByUserName;
	public List<Ratings> getRatingByMCPP;

}
