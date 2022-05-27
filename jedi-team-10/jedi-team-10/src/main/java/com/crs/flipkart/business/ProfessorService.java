package com.crs.flipkart.business;

import java.sql.SQLException;
import java.util.*;

import com.crs.flipkart.bean.*;
import com.crs.flipkart.dao.ProfessorDaoInterface;
import com.crs.flipkart.dao.ProfessorDaoOperation;
import com.crs.flipkart.exceptions.CourseNotFoundException;
/**
 * @author devanshugarg
 *
 */

public class ProfessorService implements ProfessorInterface {
	
	private static volatile ProfessorService instance = null;
	
	/**
	 * Default Constructor
	 */
	private ProfessorService() {
		
	}
	
	/**
	 * Method to make ProfessorService Singleton
	 */
	public static ProfessorService getInstance() {
		
		if(instance == null) {
			
			synchronized(ProfessorService.class) {
				
				instance = new ProfessorService();
			}
		}
		return instance;
	}
	
	ProfessorDaoInterface professorDaoOperation = ProfessorDaoOperation.getInstance();
	
	/**
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param gpa
	 * @param semesterId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public boolean addGrade(int studentId, int courseCode, double gpa) throws SQLException {
		
		try {
			professorDaoOperation.addGrade(studentId, courseCode, gpa);
		} catch (SQLException e) {
			throw e;
		}
		return true;
	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Vector<EnrolledStudent> viewEnrolledStudents(int professorId) throws SQLException {

		Vector<EnrolledStudent> enrolledStudents = new Vector<>();
		
		try {
			enrolledStudents = professorDaoOperation.getEnrolledStudents(professorId);
		} catch (SQLException e) {
			throw e;
		}
		return enrolledStudents;
	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Vector<Course> viewCourses(int professorId) throws SQLException {

		Vector<Course> coursesOffered = new Vector<>();
		
		try {
			coursesOffered = professorDaoOperation.getCoursesByProfessor(professorId);
		} catch (SQLException e) {
			throw e;
		}
		return coursesOffered;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Vector<Course> viewAvailableCourses() throws SQLException {

 		Vector<Course> availableCourses = new Vector<>();

 		try {
 			availableCourses = professorDaoOperation.viewAvailableCourses();
 		} catch (SQLException e) {
 			throw e;
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
	public String getProfessorById(int professorId) throws SQLException {
	
		return professorDaoOperation.getProfessorById(professorId);
	}
	
	/**
	 * 
	 * @param professorId
	 * @param courseSelectedId
	 * @return
	 * @throws SQLException 
	 * @throws CourseNotFoundException 
	 */
	@Override
	public boolean addCourse(int professorId, int courseSelectedId) throws SQLException, CourseNotFoundException {

 		try {
 			if(!professorDaoOperation.checkSelectedCourse(professorId)) {
 				return false;
 			}else if(!professorDaoOperation.checkCourse(courseSelectedId)) {
 				throw new CourseNotFoundException(courseSelectedId);
 			}
 			return professorDaoOperation.addCourse(professorId, courseSelectedId);
 		} catch (SQLException e) {
 			throw e;
 		}
 	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public int getProfessorId(int userId) throws SQLException {
	
		return professorDaoOperation.getProfessorId(userId);
	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Vector<EnrolledStudent> getNotGradedStudents(int professorId) throws SQLException {

 		Vector<EnrolledStudent> notGradedStudents = new Vector<>();

 		try {
 			notGradedStudents = professorDaoOperation.getNotGradedStudents(professorId);
 		} catch (SQLException e) {
 			throw e;
 		}
 		return notGradedStudents;
 	}
}
