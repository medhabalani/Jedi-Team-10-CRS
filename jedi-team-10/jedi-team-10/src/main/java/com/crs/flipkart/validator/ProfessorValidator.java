/**
 * 
 */
package com.crs.flipkart.validator;

import java.util.Vector;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.EnrolledStudent;

/**
 * @author devanshugarg
 *
 */
public class ProfessorValidator {

	/**
	 * 
	 * @param students
	 * @param studentId
	 * @return
	 */
	public static boolean isValidStudent(Vector<EnrolledStudent> students, int studentId) {
		
		boolean result = false;
		for(int i = 0; i < students.size(); i++) {
			if(students.get(i).getStudentId() == studentId)
				result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @param courses
	 * @param courseId
	 * @return
	 */
	public static boolean isValidCourse(Vector<Course> assignedCourses, int courseId) {

		boolean result = false;
		for(int i = 0; i < assignedCourses.size(); i++) {
			if(assignedCourses.get(i).getCourseId() == courseId)
				result= true;
		}
		return result;
	}
}
