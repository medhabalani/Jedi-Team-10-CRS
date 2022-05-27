package com.crs.flipkart.business;

import java.sql.SQLException;
import java.util.Vector;

import com.crs.flipkart.bean.CardPayment;
import com.crs.flipkart.bean.Cheque;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.NetBanking;
import com.crs.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.crs.flipkart.exceptions.CourseLimitExceededException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.SeatNotAvailableException;

public interface RegistrationInterface {

	/**
	 * @param courseId
	 * @param studentId
	 * @param availableCourses
	 * @return
	 * @throws SQLException 
	 * @throws SeatNotAvailableException 
	 * @throws CourseLimitExceededException 
	 * @throws CourseNotFoundException 
	 */
	boolean addCourse(int courseId, int studentId, Vector<Course> availableCourses) throws SQLException, CourseLimitExceededException, SeatNotAvailableException, CourseAlreadyRegisteredException, CourseNotFoundException;

	/**
	 * @param courseId
	 * @param studentId
	 * @param registeredCourseList
	 * @return
	 * @throws SQLException 
	 * @throws CourseNotFoundException 
	 */
	boolean dropCourse(int courseId, int studentId, Vector<Course> registeredCourseList) throws SQLException, CourseNotFoundException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean getRegistrationStatus(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @throws SQLException 
	 */
	void setRegistrationStatus(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> viewRegisteredCourses(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> viewCourses(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean getPaymentStatus(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	double calculateFee(int studentId) throws SQLException;

	/**
	 * @param studentId
	 * @param invoiceId
	 * @param amount
	 * @throws SQLException 
	 */
	void setPaymentStatus(int studentId, int invoiceId, double amount) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	boolean isGenerated(int studentId) throws SQLException;

	int totalRegisteredCourses(int studentId) throws SQLException;

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
	 * @param studentId
	 * @param invoiceId
	 * @param bankAccountHolderName
	 * @param bankName
	 * @throws SQLException 
	 */
	void paymentByNetBanking(NetBanking netBanking) throws SQLException;

	/**
	 * @param studentId
	 * @return
	 * @throws SQLException 
	 */
	Vector<GradeCard> viewGradeCard(int studentId) throws SQLException;

}