/**
 * 
 */
package com.crs.flipkart.dao;

import java.util.Vector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crs.flipkart.bean.CardPayment;
import com.crs.flipkart.bean.Cheque;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.NetBanking;
import com.crs.flipkart.constants.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;
import com.crs.flipkart.utils.Utils;

/**
 * @author devanshugarg
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface {

	private static volatile RegistrationDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(RegistrationDaoOperation.class);
	Connection connection = DBUtils.getConnection();
	private PreparedStatement statement = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationDaoOperation() {
		
	}
	
	/**
	 * Method to make RegistrationDaoOperation Singleton
	 * @return
	 */
	public static RegistrationDaoOperation getInstance() {
		
		if(instance == null) {
			
			synchronized(RegistrationDaoOperation.class){
				
				instance = new RegistrationDaoOperation();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public double calculateFee(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		double fee = 0.0;

		try {
			String sql = SQLQueriesConstant.GET_REGISTERED_COURSE_FEE;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				fee += resultSet.getDouble("courseFee");
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
 			statement.close();
 			connection.close();
 		}
		return fee;
	}

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean getRegistrationStatus(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;

		try {
			String sql = SQLQueriesConstant.IS_APPROVED;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next() && resultSet.getInt("isApproved") == 1) {
				return true;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
		return false;
	}

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean getPaymentStatus(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;

		try {
			String sql = SQLQueriesConstant.PAYMENT_STATUS;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next() && resultSet.getInt("status") == 1) {
				return true;
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
		return false;
	}

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
	@Override
	public void paymentByCard(CardPayment card) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;

		try {
			String sql = SQLQueriesConstant.PAYMENT_CARD;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, card.getInvoiceId());
			statement.setString(2, card.getCardType());
			statement.setString(3, card.getCardNumber());
			statement.setString(4, card.getCardHolderName());
			statement.setInt(5, card.getCvv());
			statement.setString(6, card.getBankName());
			statement.setDate(7, card.getExpiryDate());
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
	}

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
	@Override
	public void paymentByCheque(Cheque cheque) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;

		try {
			String sql = SQLQueriesConstant.PAYMENT_CHEQUE;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, cheque.getInvoiceId());
			statement.setInt(2, cheque.getChequeNo());
			statement.setString(3, cheque.getBankAccountHolderName());
			statement.setString(4, cheque.getBankAccountNumber());
			statement.setString(5, cheque.getIfsc());
			statement.setString(6, cheque.getBankName());
			statement.setString(7, cheque.getBankBranchName());
			statement.setDate(8, cheque.getChequeDate());
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
	}
 
	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param bankAccountHolderName
	 * @param bankName
	 * @throws SQLException
	 */
	@Override
	public void paymentByNetBanking(NetBanking netBanking) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
		try {
			String sql = SQLQueriesConstant.PAYMENT_NETBANKING;
			statement = connection.prepareStatement(sql);
			statement.setString(1, netBanking.getBankName());
			statement.setString(2, netBanking.getBankAccountHolderName());
			statement.setInt(3, netBanking.getInvoiceId());
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
	}

	/**
	 * 
	 * @param studentId
	 * @param invoiceId
	 * @param amount
	 * @throws SQLException
	 */
	@Override
	public void setPaymentStatus(int studentId, int invoiceId, double amount) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
		try {
			String sql = SQLQueriesConstant.ADD_PAYMENT;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, invoiceId);
			statement.setInt(2, studentId);
			statement.setDouble(3, amount);
			statement.setBoolean(4, true);
			statement.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());	
		} finally {
 			statement.close();
 			connection.close();
 		}
	}

	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean isGenerated(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;

		try {
			String sql = SQLQueriesConstant.IS_GENERATED;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next() && resultSet.getInt("isGenerated") == 1) {
				return true;
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());				
		} finally {
 			statement.close();
 			connection.close();
 		}
		return false;
	}

	/**
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<GradeCard> viewGradeCard(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		Vector<GradeCard> grades = new Vector<>();

		statement = null;

		try {
			String sql = SQLQueriesConstant.VIEW_COURSES_GRADE;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, studentId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				GradeCard gradeCard = new GradeCard();
				gradeCard.setCourseId(resultSet.getInt("courseId"));
				gradeCard.setGpa(resultSet.getDouble("gpa"));
				gradeCard.setStudentId(studentId);
				grades.add(gradeCard);				
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());				
		} finally {
 			statement.close();
 			connection.close();
 		}
		return grades;
	}
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean addCourse(int studentId, int courseId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.ADD_COURSE;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			statement.setInt(2, courseId);
 			statement.executeUpdate();

 			sql = SQLQueriesConstant.DECREMENT_SEAT;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseId);
 			statement.executeUpdate();
 			return true;
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return false;
 	}
	
	/**
	 * 
	 * @param semester
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean semesterRegistration(int semester, int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.CHECK_STUDENT_AND_SEM;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			statement.setInt(2, semester);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return false;
 	}
	
	/**
	 * 
	 * @param semester
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean addSemester(int semester, int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.ADD_SEMESTER;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, Utils.generateId());
 			statement.setInt(2, studentId);
 			statement.setInt(3, semester);
 			statement.executeUpdate();
 			return true;
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return false;
 	}
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean dropCourse(int studentId, int courseId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.DROP_COURSE;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			statement.setInt(2, courseId);
 			statement.executeUpdate();

 			sql = SQLQueriesConstant.INCREMENT_SEAT;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseId);
 			statement.executeUpdate();
 			return true;
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return false;
 	}
	
	/**
	 * 
	 * @param courseId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean isSeatAvailable(int courseId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.AVAILABLE_SEATS;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				return resultSet.getInt("courseSeats") > 0;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return true;
 	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int totalRegisteredCourses(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		int totalcourse = 0;
 		statement = null;
 		
 		try {
 			String sql = SQLQueriesConstant.TOTAL_REGISTERED_COURSES;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				totalcourse++;
 			}
 			return totalcourse;
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return totalcourse;
 	}
	
	/**
	 * 
	 * @param courseId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean isSemesterRegistered(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		statement = null;

 		try{
 			String sql = SQLQueriesConstant.CHECK_SEMESTER_REGISTRATION;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()){
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error(e.getClass());
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return false;

 	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<Course> viewCourses(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		Vector<Course> availableCourses = new Vector<>();
 		statement = null;
 		
 		try {
 			String sql = SQLQueriesConstant.VIEW_AVAILABLE_COURSES;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				Course course = new Course();
 				course.setCourseId(resultSet.getInt(1));
  				course.setCourseName(resultSet.getString(2));
  				course.setCourseDescription(resultSet.getString(3));
  				course.setCourseFee(resultSet.getDouble(4));
  				course.setCourseSeats(resultSet.getInt(5));
  				availableCourses.add(course);
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return availableCourses;
 	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<Course> viewRegisteredCourses(int studentId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		Vector<Course> availableCourses = new Vector<>();
 		statement = null;
 		
 		try {
 			String sql = SQLQueriesConstant.VIEW_REGISTERED_COURSES;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, studentId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				Course course = new Course();
 				course.setCourseId(resultSet.getInt(1));
  				course.setCourseName(resultSet.getString(2));
  				course.setCourseDescription(resultSet.getString(3));
  				course.setCourseFee(resultSet.getDouble(4));
  				course.setCourseSeats(resultSet.getInt(5));
  				availableCourses.add(course);
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		} finally {
 			statement.close();
 			connection.close();
 		}
 		return availableCourses;
 	}
	
	/**
	 * 
	 * @param courseId
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean isRegistered(int courseId, int studentId) throws SQLException {
 		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		boolean check = false;
		statement = null;
		try
		{
			String sql = SQLQueriesConstant.IS_REGISTERED;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, courseId);
			statement.setInt(2, studentId);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				check = true;
			}
		}
		catch (SQLException e) {
			logger.error(e.getClass());
			logger.error(e.getMessage());
		} finally {
			statement.close();
			connection.close();
		}
		
		return check;
	}
}
