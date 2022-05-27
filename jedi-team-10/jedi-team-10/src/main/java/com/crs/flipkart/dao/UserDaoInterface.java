package com.crs.flipkart.dao;

import com.crs.flipkart.exceptions.UserNotFoundException;

public interface UserDaoInterface {

	/**
	 * 
	 * @param userEmailId
	 * @param password
	 * @return
	 * @throws UserNotFoundException 
	 */
	boolean verifyCredentials(String userEmailId, String password) throws UserNotFoundException;

	/**
	 * 
	 * @param userEmailId
	 * @param newPassword
	 * @return
	 */
	boolean updatePassword(String userEmailId, String newPassword);

	/**
	 * 
	 * @param userId
	 * @return
	 */
	String getRole(String userEmailId);

	/**
	 * 
	 * @param userEmailId
	 * @return
	 */
	int getUserId(String userEmailId);

}