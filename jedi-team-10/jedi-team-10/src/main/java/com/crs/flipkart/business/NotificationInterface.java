package com.crs.flipkart.business;

import java.sql.SQLException;

import com.crs.flipkart.constants.NotificationTypeConstant;

public interface NotificationInterface {

	/**
	 * @param type
	 * @param studentId
	 * @param modeOfPayment
	 * @param amount
	 * @return
	 * @throws SQLException 
	 */
	int sendPaymentNotification(NotificationTypeConstant type, int studentId, int modeOfPayment, double amount, int referenceId) throws SQLException;

	/**
	 * @param type
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	int sendApprovalNotification(NotificationTypeConstant type, int studentId) throws SQLException;

	/**
	 * 
	 * @param type
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	int sendRegistrationNotification(NotificationTypeConstant type, int studentId) throws SQLException;

}