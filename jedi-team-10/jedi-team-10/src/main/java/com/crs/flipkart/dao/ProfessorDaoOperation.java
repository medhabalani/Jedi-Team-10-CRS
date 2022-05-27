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

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.EnrolledStudent;
import com.crs.flipkart.constants.SQLQueriesConstant;
import com.crs.flipkart.utils.DBUtils;

/**
 * @author devanshugarg
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static volatile ProfessorDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	Connection connection = DBUtils.getConnection();
	private PreparedStatement statement, semesterStatement = null;
	
	/**
	 * Default Constructor
	 */
	private ProfessorDaoOperation()
	{

	}
	
	/**
	 * Method to make ProfessorDaoOperation Singleton
	 * @return
	 */
	public static ProfessorDaoOperation getInstance() {
		
		if(instance == null) {
			
			synchronized(ProfessorDaoOperation.class) {
				
				instance = new ProfessorDaoOperation();
			}
		}
		return instance;
	}
	
	/**
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param grade
	 * @throws SQLException
	 */
	@Override
	public boolean addGrade(int studentId, int courseId, double gpa) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		statement = null;
		int semesterId = 0;
		
		try {
			
			String CheckSemestersql = SQLQueriesConstant.CHECK_SEMESTER_REGISTRATION;
			semesterStatement = connection.prepareStatement(CheckSemestersql);
			semesterStatement.setInt(1, studentId);
			ResultSet resultSet = semesterStatement.executeQuery();
			while (resultSet.next()) {
				semesterId = resultSet.getInt(1);
			}
			
			String sql = SQLQueriesConstant.ADD_GRADE;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseId);
 			statement.setInt(2, studentId);
 			statement.setInt(3, semesterId);
 			statement.setDouble(4, gpa);
 			int row = statement.executeUpdate();
 			if (row == 1) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return false;
	}
	
	/**
	 * 
	 * @param professorId
	 * @param courseSelectedId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean addCourse(int professorId, int courseSelectedId) throws SQLException {
 		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.ADD_COURSE_TO_PROFESSOR;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseSelectedId);
 			statement.setInt(2, professorId);
 			int row = statement.executeUpdate();
 			if (row == 1) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return false;
 	}

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean checkSelectedCourse(int professorId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		try {
 			String sql = SQLQueriesConstant.CHECK_SELECTED_COURSE;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, professorId);
 			ResultSet resultSet = statement.executeQuery();
 			if(resultSet.next() && resultSet.getInt("courseId") != 0) {
 				return false;
 			}
 			return true;
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}

 		return false;
 	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public boolean checkCourse(int courseId) throws SQLException {
		Connection connection = DBUtils.getConnection();
 		try {
 			String sql = SQLQueriesConstant.CHECK_COURSE;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, courseId);
 			ResultSet resultSet = statement.executeQuery();
 			if(resultSet.next()) {
 				return true;
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}

 		return false;
 	}

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<EnrolledStudent> getEnrolledStudents(int professorId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		statement = null;
		Vector<EnrolledStudent> enrolledStudents = new Vector<>();
		
 		try {
 			String sql = SQLQueriesConstant.GET_ENROLLED_STUDENTS;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, professorId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				EnrolledStudent enrolledStudent = new EnrolledStudent();
 				enrolledStudent.setCourseId(resultSet.getInt("courseId"));
 				enrolledStudent.setCourseName(resultSet.getString("courseName"));
 				enrolledStudent.setStudentId(resultSet.getInt("studentId"));
 				enrolledStudents.add(enrolledStudent);
 			}
 		} catch(SQLException e) {
 			logger.error("Couldn't find Enrolled Students: " + e.getMessage());
 		}
 		return enrolledStudents;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<Course> viewAvailableCourses() throws SQLException {
 		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
 		statement = null;
 		Vector<Course> availableCourses = new Vector<>();
 		
 		try {
 			String sql = SQLQueriesConstant.GET_AVAILABLE_COURSES;
 			statement = connection.prepareStatement(sql);
 			statement.executeQuery();
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
 		}
 		return availableCourses;
 	}

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<Course> getCoursesByProfessor(int professorId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		statement = null;
		Vector<Course> courseList=new Vector<>();
		
 		try {
 			String sql = SQLQueriesConstant.GET_COURSES;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, professorId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				Course course = new Course();
 				course.setCourseId(resultSet.getInt("courseId"));
 				course.setCourseName(resultSet.getString("courseName"));
 				course.setCourseSeats(resultSet.getInt("courseSeats"));
 				courseList.add(course);
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return courseList;
	}

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public String getProfessorById(int professorId) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DBUtils.getConnection();
		String professorName = null;
		statement = null;
		
 		try {
 			String sql = SQLQueriesConstant.GET_PROF_NAME;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, professorId);
 			ResultSet resultSet = statement.executeQuery();
 			while(resultSet.next()) {
 				professorName = resultSet.getString(1);
 			}
 		} catch(SQLException e) {
 			logger.error("Error: " + e.getMessage());
 		}
 		return professorName;
	}
	
	/**
	 * Retrieve Professor Id from User Id
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int getProfessorId(int userId) throws SQLException {
		Connection connection = DBUtils.getConnection();
		statement = null;
		int professorId = 0;
		
		try {
			String sql = SQLQueriesConstant.GET_PROFESSOR_ID;
 			statement = connection.prepareStatement(sql);
 			statement.setInt(1, userId);
 			ResultSet resultSet = statement.executeQuery();
 			if(resultSet.next()) {
 				professorId = resultSet.getInt(1);
 			}
		} catch (SQLException e) {
			logger.error("Error: " + e.getMessage());
		}
		return professorId;
	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<EnrolledStudent> getNotGradedStudents(int professorId) throws SQLException {
 		// TODO Auto-generated method stub
 		Connection connection = DBUtils.getConnection();
 		statement = null;
 		Vector<EnrolledStudent> notGradedStudents = new Vector<>();

  		try {
  			String sql = SQLQueriesConstant.GET_NOT_GRADED_STUDENTS;
  			statement = connection.prepareStatement(sql);
  			statement.setInt(1, professorId);
  			ResultSet resultSet = statement.executeQuery();
  			while(resultSet.next()) {
  				EnrolledStudent notGraded = new EnrolledStudent();
  				notGraded.setCourseId(resultSet.getInt("courseId"));
  				notGraded.setCourseName(resultSet.getString("courseName"));
  				notGraded.setStudentId(resultSet.getInt("studentId"));
  				notGradedStudents.add(notGraded);
  			}
  		} catch(SQLException e) {
  			logger.error("Couldn't find not yet Graded Students: " + e.getMessage());
  		}
  		return notGradedStudents;
 	}
}
