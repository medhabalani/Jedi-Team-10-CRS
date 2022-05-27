/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class Course {

	private int courseId;
	private String courseName;
	private String courseDescription;
	private double courseFee;
	private int courseSeats;
	
	/**
	 * Default Constructor
	 */
	public Course() {
		
	}

	/**
	 * @param courseId
	 * @param courseName
	 * @param courseDescription
	 * @param courseFee
	 * @param courseSeats
	 */
	public Course(int courseId, String courseName, String courseDescription, double courseFee, int courseSeats,
			int professorId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseFee = courseFee;
		this.courseSeats = courseSeats;
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
	 * @return the courseDescription
	 */
	public String getCourseDescription() {
		return courseDescription;
	}

	/**
	 * @param courseDescription the courseDescription to set
	 */
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	/**
	 * @return the courseFee
	 */
	public double getCourseFee() {
		return courseFee;
	}

	/**
	 * @param courseFee the courseFee to set
	 */
	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}

	/**
	 * @return the courseSeats
	 */
	public int getCourseSeats() {
		return courseSeats;
	}

	/**
	 * @param courseSeats the courseSeats to set
	 */
	public void setCourseSeats(int courseSeats) {
		this.courseSeats = courseSeats;
	}

}
