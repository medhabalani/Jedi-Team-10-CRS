/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class EnrolledStudent {

	private int courseId;
	private String courseName;
	private int studentId;
	
	/**
	 * Default Constructor
	 */
	public EnrolledStudent() {
		
	}

	/**
	 * @param courseId
	 * @param courseName
	 * @param studentId
	 */
	public EnrolledStudent(int courseId, String courseName, int studentId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentId = studentId;
	}

	/**
	 * @return the courseCode
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
