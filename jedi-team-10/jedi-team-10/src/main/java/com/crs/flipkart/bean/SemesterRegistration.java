/**
 * 
 */
package com.crs.flipkart.bean;

import java.util.Date;

/**
 * @author devanshugarg
 *
 */
public class SemesterRegistration {

	private int semesterId;
	private Date date;
	private int studentId;
	private int semester;
	
	/**
	 * Default Constructor
	 */
	public SemesterRegistration() {
		
	}

	/**
	 * @param semesterId
	 * @param date
	 * @param studentId
	 */
	public SemesterRegistration(int semesterId, Date date, int studentId, int semester) {
		super();
		this.semesterId = semesterId;
		this.date = date;
		this.studentId = studentId;
		this.semester = semester;
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

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the courseId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
}
