/**
 * 
 */
package com.crs.flipkart.exceptions;

/**
 * @author devanshugarg
 *
 */
@SuppressWarnings("serial")
public class CourseAlreadyRegisteredException extends Exception {

	private int courseId;

	/**
	 * @param courseId
	 */
	public CourseAlreadyRegisteredException(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * Getter Method
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	
	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "You have already registered for the course with Course Id: " + courseId + ".";
	}
}
