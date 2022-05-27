package com.crs.flipkart.business;

import java.sql.SQLException;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.StudentNotRegisteredException;

public interface StudentInterface {

	/**
	 * @param userId
	 * @return
	 */
	int getStudentId(int userId);

	/**
	 * @param studentId
	 * @return
	 */
	Boolean isApproved(int studentId);

	/**
	 * @param studentId
	 * @return
	 */
	Boolean isGenerated(int studentId);

	/**
	 * @param semester
	 * @param studentId
	 * @return
	 * @throws SQLException
	 */
	boolean semesterRegistration(int semester, int studentId) throws SQLException;

	/**
	 * 
	 * @param student
	 * @return
	 * @throws StudentNotRegisteredException 
	 */
	int register(Student student) throws StudentNotRegisteredException;

}