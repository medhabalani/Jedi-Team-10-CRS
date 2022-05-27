/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class Catalog {

	private int CourseId;
	private String courseName;
	private String professorName;
	
	/**
	 * Default Constructor
	 */
	public Catalog() {
		
	}
	
	/**
	 * @param courseId
	 * @param courseName
	 * @param professorName
	 */
	public Catalog(int courseId, String courseName, String professorName) {
		super();
		CourseId = courseId;
		this.courseName = courseName;
		this.professorName = professorName;
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return CourseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		CourseId = courseId;
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
	 * @return the professorName
	 */
	public String getProfessorName() {
		return professorName;
	}

	/**
	 * @param professorName the professorName to set
	 */
	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
}
