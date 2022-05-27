/**
 * 
 */
package com.crs.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.crs.flipkart.constants.SQLQueriesConstant;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author devanshugarg
 *
 */
public class UserDaoOperation implements UserDaoInterface {

	private static volatile UserDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	Connection connection = DBUtils.getConnection();
	private PreparedStatement statement = null;
	
	/**
	 * Default Constructor
	 */
	private UserDaoOperation() {
		
	}
	
	/**
	 * Method to make UserDaoOperation Singleton
	 * @return
	 */
	public static UserDaoOperation getInstance() {
		
		if(instance == null) {
			
			synchronized(UserDaoOperation.class) {
				
				instance = new UserDaoOperation();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @param userEmailId
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	@Override
	public boolean verifyCredentials(String userEmailId, String password) throws UserNotFoundException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.VERIFY_CREDENTIALS;
 			statement = connection.prepareStatement(sql);
 			statement.setString(1,userEmailId);
 			ResultSet resultSet = statement.executeQuery();
 			if(!resultSet.next()) {
 				throw new UserNotFoundException(userEmailId);
 			}
 			else if(password.equals(resultSet.getString("userPassword"))) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return false;
 	}
	
	/**
	 * 
	 * @param userEmailId
	 * @param newPassword
	 * @return
	 */
 	@Override
	public boolean updatePassword(String userEmailId, String newPassword) {
 		Connection connection = DBUtils.getConnection();
 		statement = null;
 		
 		try {
 			String sql = SQLQueriesConstant.UPDATE_PASSWORD;
 			statement = connection.prepareStatement(sql);
 			statement.setString(1, newPassword);
 			statement.setString(2, userEmailId);
 			int row = statement.executeUpdate();
 			if(row == 1) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: "+ e.getMessage());
 		}
 		return false;
 	}
 	
 	/**
 	 * 
 	 * @param userId
 	 * @return
 	 */
 	@Override
	public String getRole(String userEmailId) {
 		Connection connection = DBUtils.getConnection();
 		statement = null;
 		String role = "";
 		
 		try {
 			String sql = SQLQueriesConstant.GET_ROLE;
 			statement = connection.prepareStatement(sql);
 			statement.setString(1, userEmailId);
 			ResultSet resultSet = statement.executeQuery();
 			if(resultSet.next()) {
 				role = resultSet.getString("role");
 			}	
 		} catch(Exception e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return role;
 	}
 	
 	/**
 	 * 
 	 * @param userEmailId
 	 * @return
 	 */
 	@Override
 	public int getUserId(String userEmailId) {
 		Connection connection = DBUtils.getConnection();
 		statement = null;
 		int userId = 0;
 		
 		try {
 			String sql = SQLQueriesConstant.GET_USER_ID;
 			statement = connection.prepareStatement(sql);
 			statement.setString(1, userEmailId);
 			ResultSet resultSet = statement.executeQuery();
 			if(resultSet.next()) {
 				userId = resultSet.getInt("userId");
 			}
 		} catch (SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return userId;
 	}
}
