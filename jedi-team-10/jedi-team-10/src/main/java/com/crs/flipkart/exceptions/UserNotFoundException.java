/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author devanshugarg
 *
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	
	private String userEmailId;

	/**
	 * @param userEmailId
	 */
	public UserNotFoundException(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	/**
	 * Getter Method
	 * @return the userEmailId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}
	
	/**
	 * Message returned when Exception is thrown
	 */
	@Override
	public String getMessage() {
		return "User with User Email Id: " + userEmailId + " not found.";
	}
}
