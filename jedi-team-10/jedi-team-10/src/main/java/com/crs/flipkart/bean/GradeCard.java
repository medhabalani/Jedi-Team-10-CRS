/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class GradeCard {

	private int courseId;
	private int studentId;
	private double gpa;
	private int semesterId;
	
	/**
	 * Default Constructor
	 */
	public GradeCard() {
		
	}

	/**
	 * @param courseId
	 * @param studentId
	 * @param gpa
	 * @param semesterId
	 */
	public GradeCard(int courseId, String courseName, int studentId, double gpa, int semesterId) {
		super();
		this.courseId = courseId;
		this.studentId = studentId;
		this.gpa = gpa;
		this.semesterId = semesterId;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * @return the semesterId
	 */
	public int getSemesterId() {
		return semesterId;
	}

	/**
	 * @param semesterId the semesterId to set
	 */
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	
}
