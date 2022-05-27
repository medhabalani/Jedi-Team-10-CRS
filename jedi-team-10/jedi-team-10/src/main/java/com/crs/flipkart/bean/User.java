/**
 * 
 */
package com.crs.flipkart.bean;

import com.crs.flipkart.constants.GenderConstant;
import com.crs.flipkart.constants.RoleConstant;

/**
 * @author devanshugarg
 *
 */
public class User {
	
	private String userName;
    private String userEmailId;
    private String userPassword;
    private RoleConstant role;
    private int userId;
    private String phoneNo;
    private GenderConstant gender;
	private String address;
	
	/**
	 * Default Constructor
	 */
	public User() {}
	
	/**
	 * @param userName
	 * @param userEmailId
	 * @param userPassword
	 * @param role
	 * @param userId
	 * @param phoneNo
	 * @param gender
	 * @param address
	 */
	public User(String userName, String userEmailId, String userPassword, RoleConstant role, int userId, String phoneNo,
			GenderConstant gender, String address) {
		super();
		this.userName = userName;
		this.userEmailId = userEmailId;
		this.userPassword = userPassword;
		this.role = role;
		this.userId = userId;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.address = address;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userEmailId
	 */
	public String getUserEmailId() {
		return userEmailId;
	}
	/**
	 * @param userEmailId the userEmailId to set
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return the role
	 */
	public RoleConstant getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(RoleConstant role) {
		this.role = role;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the gender
	 */
	public GenderConstant getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(GenderConstant gender) {
		this.gender = gender;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
}
