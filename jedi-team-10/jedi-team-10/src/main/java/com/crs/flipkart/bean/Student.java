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
public class Student extends User{

	private String branchName;
	private int studentId;
	private int batch;
	private boolean isApproved;
	
	/**
	 * Default Constructor
	 */
	public Student() {
		
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
	 * @param branchName
	 * @param studentId
	 * @param batch
	 * @param isApproved
	 */
	public Student(String userName, String userEmailId, String userPassword, RoleConstant role, int userId, String phoneNo,
			GenderConstant gender, String address, String branchName, int studentId, int batch, boolean isApproved) {
		super(userName, userEmailId, userPassword, role, userId, phoneNo, gender, address);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isApproved = isApproved;
	}

	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the batch
	 */
	public int getBatch() {
		return batch;
	}
	/**
	 * @param batch the batch to set
	 */
	public void setBatch(int batch) {
		this.batch = batch;
	}
	/**
	 * @return the isApproved
	 */
	public boolean isApproved() {
		return isApproved;
	}
	/**
	 * @param isApproved the isApproved to set
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}
