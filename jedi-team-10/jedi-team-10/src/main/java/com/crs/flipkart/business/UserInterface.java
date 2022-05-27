package com.crs.flipkart.business;

import com.crs.flipkart.exceptions.ConfirmPasswordException;
import com.crs.flipkart.exceptions.OldPasswordNotValidException;
import com.crs.flipkart.exceptions.UserNotFoundException;

public interface UserInterface {

	/**
	 * @param userId
	 * @return
	 */
	String getRoleOfUser(String userEmailId);

	/**
	 * @param emailId
	 * @param password
	 * @return
	 * @throws UserNotFoundException 
	 */
	boolean validateUser(String emailId, String password) throws UserNotFoundException;

	/**
	 * 
	 * @param userEmailId
	 * @param oldPassword
	 * @param newPassword
	 * @param confirmNewPassword
	 * @return 
	 * @throws UserNotFoundException 
	 * @throws ConfirmPasswordException 
	 * @throws OldPasswordNotValidException 
	 */
	void updatePassword(String userEmailId, String oldPassword, String newPassword, String confirmNewPassword) throws UserNotFoundException, OldPasswordNotValidException, ConfirmPasswordException;

	/**
	 * 
	 * @param userEmailId
	 * @return
	 */
	int getUserId(String userEmailId);

}