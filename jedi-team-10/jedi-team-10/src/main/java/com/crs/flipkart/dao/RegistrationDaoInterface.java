package com.crs.flipkart.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.crs.flipkart.bean.CardPayment;
import com.crs.flipkart.bean.Cheque;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.NetBanking;

public interface RegistrationDaoInterface {

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	double calculateFee(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean getRegistrationStatus(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean getPaymentStatus(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param amount
	 * @throws SQLException 
	 */
	void setPaymentStatus(int studentId, int invoiceId, double amount) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean isGenerated(int studentId) throws SQLException;
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws SQLException 
	 */
	boolean addCourse(int studentId, int courseId) throws SQLException;

	/**
	 * 
	 * @param semester
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean semesterRegistration(int semester, int studentId) throws SQLException;

	/**
	 * 
	 * @param semester
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean addSemester(int semester, int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws SQLException 
	 */
	boolean dropCourse(int studentId, int courseId) throws SQLException;

	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws SQLException 
	 */
	boolean isSeatAvailable(int courseId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	int totalRegisteredCourses(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> viewCourses(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> viewRegisteredCourses(int studentId) throws SQLException;

	/**
	 * 
	 * @param courseCode
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	boolean isRegistered(int courseId, int studentId) throws SQLException;

	/**
	 * 
	 * @param courseId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	boolean isSemesterRegistered(int studentId) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param cardType
	 * @param cardNumber
	 * @param cardHolderName
	 * @param cvv
	 * @param bankName
	 * @param expiryDate
	 * @throws SQLException
	 */
	void paymentByCard(CardPayment card) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param chequeNo
	 * @param bankAccountHolderName
	 * @param bankAccountNumber
	 * @param ifsc
	 * @param bankName
	 * @param bankBranchName
	 * @param chequeDate
	 * @throws SQLException
	 */
	void paymentByCheque(Cheque cheque) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param bankAccountHolderName
	 * @param bankName
	 * @throws SQLException
	 */
	void paymentByNetBanking(NetBanking netBanking) throws SQLException;

	/**
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return
	 * @throws SQLException
	 */
	Vector<GradeCard> viewGradeCard(int studentId) throws SQLException;

}