/**
 * 
 */
package com.crs.flipkart.business;

import java.sql.SQLException;
import java.util.Vector;

import com.crs.flipkart.bean.CardPayment;
import com.crs.flipkart.bean.Cheque;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.NetBanking;
import com.crs.flipkart.dao.RegistrationDaoInterface;
import com.crs.flipkart.dao.RegistrationDaoOperation;
import com.crs.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.crs.flipkart.exceptions.CourseLimitExceededException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.SeatNotAvailableException;
import com.crs.flipkart.validator.StudentValidator;

/**
 * @author devanshugarg
 *
 */
public class RegistrationService implements RegistrationInterface {
	
	private static volatile RegistrationService instance = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationService() {
		
	}
	
	/**
	 * Method to make RegistrationService Singleton
	 */
	public static RegistrationService getInstance() {
		
		if(instance == null) {
			
			synchronized(RegistrationService.class) {
				
				instance = new RegistrationService();
			}
		}
		return instance;
	}
	
	RegistrationDaoInterface registrationDaoOperation = RegistrationDaoOperation.getInstance();

	@Override
	public boolean addCourse(int courseId,int studentId, Vector<Course> availableCourses) throws SQLException, CourseAlreadyRegisteredException, CourseLimitExceededException, SeatNotAvailableException, CourseNotFoundException {
		
		if(registrationDaoOperation.totalRegisteredCourses(studentId) >= 6) {
			throw new CourseLimitExceededException(6);
		} else if (registrationDaoOperation.isRegistered(courseId, studentId)) {
			throw new CourseAlreadyRegisteredException(courseId);
 		} else if (!registrationDaoOperation.isSeatAvailable(courseId)) {
 			throw new SeatNotAvailableException(courseId);
 		} else if (!StudentValidator.isValidCourseCode(courseId, availableCourses)) {
 			throw new CourseNotFoundException(courseId);
 		} 
 		return registrationDaoOperation.addCourse(studentId, courseId);
	}
	
	@Override
	public int totalRegisteredCourses(int studentId) throws SQLException {
		
		return registrationDaoOperation.totalRegisteredCourses(studentId);
	}
	
	@Override
	public boolean isSemesterRegistered(int studentId) throws SQLException {
		
		return registrationDaoOperation.isSemesterRegistered(studentId);
	}
	
	@Override
	public boolean dropCourse(int courseId, int studentId, Vector<Course> registeredCourseList) throws SQLException, CourseNotFoundException {
		
		if(!StudentValidator.isRegistered(courseId, studentId, registeredCourseList)) {
 			throw new CourseNotFoundException(courseId);
 		}
		return registrationDaoOperation.dropCourse(studentId, courseId);
	}
		
	@Override
	public boolean getRegistrationStatus(int studentId) throws SQLException {
		
		return registrationDaoOperation.getRegistrationStatus(studentId);
	}
	
	@Override
	public void setRegistrationStatus(int studentId) throws SQLException {

	}
	
	@Override
	public Vector<Course> viewRegisteredCourses(int studentId) throws SQLException {
		
		return registrationDaoOperation.viewRegisteredCourses(studentId);
	}
	
	@Override
	public Vector<GradeCard> viewGradeCard(int studentId) throws SQLException {
		
		return registrationDaoOperation.viewGradeCard(studentId);
	}
	
	@Override
	public Vector<Course> viewCourses(int studentId) throws SQLException{
		
		return registrationDaoOperation.viewCourses(studentId);
	}
	
	@Override
	public boolean getPaymentStatus(int studentId) throws SQLException {
		
		return registrationDaoOperation.getPaymentStatus(studentId);
	}
	
	@Override
	public void setPaymentStatus(int studentId, int invoiceId, double amount) throws SQLException {
		
		registrationDaoOperation.setPaymentStatus(studentId, invoiceId, amount);
	}

	@Override
	public double calculateFee(int studentId) throws SQLException {

		double fee = registrationDaoOperation.calculateFee(studentId);
 		return fee;	
	}
	
	@Override
	public void paymentByCard(CardPayment card) throws SQLException {
		
		registrationDaoOperation.paymentByCard(card);
	}
	
	@Override
	public void paymentByCheque(Cheque cheque) throws SQLException {

		registrationDaoOperation.paymentByCheque(cheque);
 	}
	
	@Override
	public void paymentByNetBanking(NetBanking netBanking) throws SQLException {

		registrationDaoOperation.paymentByNetBanking(netBanking);
 	}
	
	@Override
	public boolean isGenerated(int studentId) throws SQLException {

 		return registrationDaoOperation.isGenerated(studentId);
 	}
}
