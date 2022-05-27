/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.constants.GenderConstant;
import com.crs.flipkart.constants.RoleConstant;

/**
 * @author LENOVO
 *
 */
public class Admin extends User{

	private String dateOfJoining;

	/**
	 * Default Constructor
	 */
	public Admin() {
		
	}
	
	/**
	 * @param userName
	 * @param userEmailId
	 * @param userPassword
	 * @param role
	 * @param userId
	 * @param phoneNo
	 * @param gender
	 * @param address
	 * @param dateOfJoining
	 */
	public Admin(String userName, String userEmailId, String userPassword, RoleConstant role, int userId, String phoneNo,
			GenderConstant gender, String address, String dateOfJoining) {
		super(userName, userEmailId, userPassword, role, userId, phoneNo, gender, address);
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the dateOfJoining
	 */
	public String getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
}
