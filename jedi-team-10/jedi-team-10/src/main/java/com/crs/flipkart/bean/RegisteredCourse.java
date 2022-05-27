/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class RegisteredCourse {

	private int courseId;
	private int studentId;
	
	/**
	 * Default Constructor
	 */
	public RegisteredCourse() {
		
	}

	/**
	 * @param courseId
	 * @param studentId
	 */
	public RegisteredCourse(int courseId, String courseName, int studentId) {
		super();
		this.courseId = courseId;
		this.studentId = studentId;
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

}
