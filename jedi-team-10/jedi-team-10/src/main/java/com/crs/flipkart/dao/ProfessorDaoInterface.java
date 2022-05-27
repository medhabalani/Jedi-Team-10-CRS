package com.crs.flipkart.dao;

import java.sql.SQLException;
import java.util.Vector;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.EnrolledStudent;

public interface ProfessorDaoInterface {

	/**
	 * 
	 * @param studentId
	 * @param courseCode
	 * @param grade
	 * @throws SQLException 
	 */
	boolean addGrade(int studentId, int courseId, double gpa) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @param courseSelectedId
	 * @return
	 * @throws SQLException 
	 */
	boolean addCourse(int professorId, int courseSelectedId) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	boolean checkSelectedCourse(int professorId) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	Vector<EnrolledStudent> getEnrolledStudents(int professorId) throws SQLException;

	/**
	 * 
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> viewAvailableCourses() throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	Vector<Course> getCoursesByProfessor(int professorId) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException 
	 */
	String getProfessorById(int professorId) throws SQLException;

	/**
	 * Retrieve Professor Id from User Id
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	int getProfessorId(int userId) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	boolean checkCourse(int professorId) throws SQLException;

	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws SQLException
	 */
	Vector<EnrolledStudent> getNotGradedStudents(int professorId) throws SQLException;

}