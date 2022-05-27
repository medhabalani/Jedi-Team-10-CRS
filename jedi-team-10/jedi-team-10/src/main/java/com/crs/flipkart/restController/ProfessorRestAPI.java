/**
 * 
 */
package com.crs.flipkart.restController;

import java.util.Vector;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.EnrolledStudent;
import com.crs.flipkart.business.ProfessorInterface;
import com.crs.flipkart.business.ProfessorService;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.ProfessorAlreadyRegisteredException;
import com.crs.flipkart.validator.ProfessorValidator;

/**
 * @author devanshugarg
 *
 */
@Path("/professor")
public class ProfessorRestAPI {

	ProfessorInterface professorService = ProfessorService.getInstance();
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws ValidationException
	 */
	@GET
	@Path("/getEnrolledStudents")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<EnrolledStudent> viewEnrolledStudents(
			@NotNull
			@QueryParam("professorId") int professorId) throws ValidationException {
		
		Vector<EnrolledStudent> enrolledStudents = new Vector<>();
		try {
			enrolledStudents = professorService.viewEnrolledStudents(professorId);
			
		} catch(Exception e) {
			e.printStackTrace();
		}	
		return enrolledStudents;
	}
	
	/**
	 * 
	 * @param professorId
	 * @return
	 * @throws ValidationException
	 */
	@GET
	@Path("/getCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<Course> getCourses(
			@NotNull
			@QueryParam("professorId") int professorId) throws ValidationException {
		           
		Vector<Course> courses = new Vector<>();
		try {
			courses = professorService.viewCourses(professorId);	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	/**
	 * 
	 * @param studentId
	 * @param professorId
	 * @param grade
	 * @return
	 * @throws ValidationException
	 */
	@POST
	@Path("/addGrade")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGrade(
			@NotNull
			@QueryParam("studentId") int studentId,
			@NotNull
			@QueryParam("professorId") int professorId,
			@QueryParam("grade") double grade) throws ValidationException {
		
		try {
			Vector<EnrolledStudent> notGradedStudents = new Vector<EnrolledStudent>();
			notGradedStudents = professorService.viewEnrolledStudents(professorId);
			Vector<Course> coursesEnrolled = professorService.viewCourses(professorId);
			Course course = coursesEnrolled.get(0);        
			int courseId = course.getCourseId();
			coursesEnrolled	= professorService.viewCourses(professorId);
			if(ProfessorValidator.isValidStudent(notGradedStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseId)) {
				professorService.addGrade(studentId, courseId, grade);
				return Response.status(201).entity("Grade Added successfully for " + studentId).build();
			} else {
				return Response.status(409).entity("Grade cannot be added for " + studentId).build();
			}
		} catch(Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
	
	/**
	 * 
	 * @param professorId
	 * @param courseSelected
	 * @return
	 * @throws ValidationException
	 */
	@PUT
	@Path("/chooseCourses")
	@Produces(MediaType.APPLICATION_JSON)
	public Response chooseCourses(
			@NotNull
			@QueryParam("professorId") int professorId,
			@NotNull
			@QueryParam("courseSelected") int courseSelected) throws ValidationException {
		
		try {
			boolean status = professorService.addCourse(professorId, courseSelected);
			if (status) {
				return Response.status(201).entity("CourseId " + courseSelected + " is registered for ProfessorId " + professorId + " successfully.").build();
			} else {
				throw new ProfessorAlreadyRegisteredException(professorId);
			}
		} catch(CourseNotFoundException e) {
			return Response.status(409).entity(e.getMessage()).build();
 		} catch(ProfessorAlreadyRegisteredException e) {
 			return Response.status(409).entity(e.getMessage()).build();
		} catch(Exception e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
	}
}
