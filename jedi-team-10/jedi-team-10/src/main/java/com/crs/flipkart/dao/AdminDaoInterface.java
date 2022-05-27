package com.crs.flipkart.dao;

import java.util.Vector;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.Professor;
import com.crs.flipkart.bean.Student;
import com.crs.flipkart.bean.User;
import com.crs.flipkart.exceptions.CourseAlreadyExistsException;
import com.crs.flipkart.exceptions.CourseNotDeletedException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.ProfessorHasNotGradedException;
import com.crs.flipkart.exceptions.ProfessorNotAddedException;
import com.crs.flipkart.exceptions.ProfessorNotDeletedException;
import com.crs.flipkart.exceptions.ProfessorNotFoundException;
import com.crs.flipkart.exceptions.StudentNotFoundException;
import com.crs.flipkart.exceptions.StudentNotFoundForApprovalException;
import com.crs.flipkart.exceptions.UserIdAlreadyInUseException;
import com.crs.flipkart.exceptions.UserNotAddedException;

public interface AdminDaoInterface {

	/**
	 * 
	 * @param professor
	 * @throws ProfessorNotAddedException 
	 * @throws UserIdAlreadyInUseException 
	 */
	void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

	/**
	 * 
	 * @return
	 */
	Vector<Professor> viewProfessor();

	/**
	 * 
	 * @param professorId
	 * @throws ProfessorNotDeletedException 
	 * @throws ProfessorNotFoundException 
	 */
	void deleteProfessor(int professorId) throws ProfessorNotFoundException, ProfessorNotDeletedException;

	/**
	 * 
	 * @param studentId
	 * @throws StudentNotFoundForApprovalException 
	 */
	void approveStudentRegistration(int studentId) throws StudentNotFoundForApprovalException;

	/**
	 * 
	 * @param course
	 * @throws CourseAlreadyExistsException 
	 */
	void addCourse(Course course) throws CourseAlreadyExistsException;

	/**
	 * 
	 * @return
	 */
	Vector<Course> viewCourse();

	/**
	 * 
	 * @param courseId
	 * @throws CourseNotDeletedException 
	 * @throws CourseNotFoundException 
	 */
	void deleteCourse(int courseId) throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * 
	 * @param user
	 * @throws UserIdAlreadyInUseException 
	 * @throws UserNotAddedException 
	 */
	void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException;

	/**
	 * 
	 * @param studentId
	 */
	void setIsGenerateGrade(int studentId);

	/**
	 * 
	 * @return
	 */
	Vector<Student> viewPendingAdmissions();

	/**
	 * 
	 * @param studentId
	 * @param semesterId
	 * @throws StudentNotFoundException
	 * @throws ProfessorHasNotGradedException
	 * @return
	 */
	Vector<GradeCard> generateGradeCard(int studentId) throws StudentNotFoundException, ProfessorHasNotGradedException;

}