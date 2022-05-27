/**
 * 
 */
package com.crs.flipkart.restController;

import java.sql.SQLException;
import java.util.Vector;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.crs.flipkart.bean.CardPayment;
import com.crs.flipkart.bean.Cheque;
import com.crs.flipkart.bean.Course;
import com.crs.flipkart.bean.GradeCard;
import com.crs.flipkart.bean.NetBanking;
import com.crs.flipkart.business.NotificationInterface;
import com.crs.flipkart.business.NotificationService;
import com.crs.flipkart.business.RegistrationInterface;
import com.crs.flipkart.business.RegistrationService;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.constants.NotificationTypeConstant;
import com.crs.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.crs.flipkart.exceptions.CourseLimitExceededException;
import com.crs.flipkart.exceptions.CourseNotFoundException;
import com.crs.flipkart.exceptions.SeatNotAvailableException;
import com.crs.flipkart.utils.Utils;

/**
 * @author LENOVO
 *
 */

@Path("/student")
public class StudentRestAPI {
	
	RegistrationInterface registrationInterface = RegistrationService.getInstance();
	StudentInterface studentInterface = StudentService.getInstance();
	NotificationInterface notificationInterface = NotificationService.getInstance();
 	double fee;
 	int invoiceId;
	
	
	@POST
	@Path("/semesterRegistration/{semester}/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response semesterRegistration(
			@PathParam("semester")  int semester,
			@PathParam("studentId")  int studentId
			) {
		boolean check = false;
		try {
			check = studentInterface.semesterRegistration(semester, studentId);
		}catch(SQLException se) {
			return Response.status(500).entity("Error : " + se).build();
		}
		if(check)
			return Response.status(201).entity("Semester Registration done Sucessfully").build();
		return Response.status(201).entity("Semester Registration is already done.").build();
	}
	
	@GET
	@Path("/totalRegisteredCourses/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response totalRegisteredCourses(
			@PathParam("studentId") int studentId
		) {
		int totalcourses = 0;
		try {
			totalcourses = registrationInterface.totalRegisteredCourses(studentId);
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return Response.status(200).entity("Total Registered Course of StudentId : " + studentId + " is " + totalcourses + ".").build();
	}
	
	@POST
	@Path("/checkSemesterRegistration/{studentId}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkSemesterRegistration(
			@PathParam("studentId")  int studentId
			) {
		boolean check = false;
		try {
			check = registrationInterface.isSemesterRegistered(studentId);
		}catch(SQLException se) {
			return Response.status(500).entity("Error : " + se).build();
		}
		if(check)
			return Response.status(201).entity("Semester Registration is already done for studentId : " + studentId).build();
		return Response.status(201).entity("Semester Registration is not yet done for studentId : " + studentId).build();
	}
	
	@POST
	@Path("/registerCourses")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerCourses(
			@QueryParam("course1") int course1,
			@QueryParam("course2") int course2,
			@QueryParam("course3") int course3,
			@QueryParam("course4") int course4,
			@QueryParam("course5") int course5,
			@QueryParam("course6") int course6,
			@NotNull
			@QueryParam("studentId") int studentId)	throws SQLException, CourseLimitExceededException, SeatNotAvailableException, CourseNotFoundException{
						
		try
		{
			Vector<Course> availableCourseList = registrationInterface.viewCourses(studentId);
			Vector<Integer> courseList = new Vector<Integer>();
			
			courseList.add(course1);
			courseList.add(course2);
			courseList.add(course3);
			courseList.add(course4);
			courseList.add(course5);
			courseList.add(course6);

			for(int courseCode:courseList)
				registrationInterface.addCourse(courseCode, studentId, availableCourseList);
			
			registrationInterface.setRegistrationStatus(studentId);
			
		} catch (SQLException e) {
			return Response.status(500).entity("Error : " + e).build();
		} catch (SeatNotAvailableException e) {
			return Response.status(500).entity("Error : " + e).build();
		} catch (CourseLimitExceededException e) {
			return Response.status(500).entity("Error : " + e).build();
		} catch (CourseNotFoundException e) {
			return Response.status(500).entity("Error : " + e).build();
		} catch (CourseAlreadyRegisteredException e) {
			return Response.status(500).entity("Error : " + e).build();
		}
					
		
		return Response.status(201).entity( "Registration Successful").build();
		
	}
	
	
	@PUT
	@Path("/addCourse/{courseId}/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(
			@NotNull
			@PathParam("courseId") int courseId,
			@NotNull
			@PathParam("studentId") int studentId
		) throws CourseLimitExceededException, SeatNotAvailableException, CourseNotFoundException, CourseAlreadyRegisteredException {
		
		Vector<Course> availableCourses = null;
		try {
			availableCourses = registrationInterface.viewCourses(studentId);
			if(availableCourses.size() == 0) {
				return Response.status(200).entity("There are no courses available.").build();
			}
			registrationInterface.addCourse(courseId, studentId, availableCourses);
		} catch(SQLException se) {
			return Response.status(500).entity( "Error : " + se).build();
		} catch (SeatNotAvailableException e) {
			return Response.status(409).entity("Error : " + e).build();
		} catch (CourseLimitExceededException e) {
			return Response.status(409).entity("Error : " + e).build();
		} catch (CourseNotFoundException e) {
			return Response.status(404).entity("Error : " + e).build();
		} catch (CourseAlreadyRegisteredException e) {
			return Response.status(409).entity("Error : " + e).build();
		}
			return Response.status(201).entity( "You have Successfully added Course : " + courseId).build();
		
	}
	
	@DELETE
	@Path("/dropCourse/{courseId}/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dropCourse(
			@NotNull
			@PathParam("courseId") int courseId,
			@NotNull
			@PathParam("studentId") int studentId
		) throws CourseNotFoundException {
		
		Vector<Course> registeredCourses = null;
		try {
			registeredCourses = registrationInterface.viewRegisteredCourses(studentId);
			if(registeredCourses.size() == 0) {
				return Response.status(200).entity("There are no registered courses available.").build();
			}
			registrationInterface.dropCourse(courseId, studentId, registeredCourses);
		} catch(SQLException se) {
			return Response.status(500).entity( "Error : " + se).build();
		} catch (CourseNotFoundException e) {
			return Response.status(404).entity("Error : " + e).build();
		}
			return Response.status(201).entity( "You have Successfully droped Course : " + courseId).build();
		
	}
	
	
	@GET
	@Path("/viewAvailableCourses/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<Course> viewAvailableCourses(
			@PathParam("studentId") int studentId
		) {
		Vector<Course> availableCourses = null;
		try {
			availableCourses = registrationInterface.viewCourses(studentId);
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return availableCourses;
	}
	
	
	@GET
	@Path("/viewRegisteredCourses/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vector<Course> viewRegisteredCourses(
			@PathParam("studentId") int studentId
		) {
		Vector<Course> registeredCourses = null;
		try {
			registeredCourses = registrationInterface.viewRegisteredCourses(studentId);
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		return registeredCourses;
	}
	
	
	/**
	 * Make Payment
	 * @param studentId
	 */
	@GET
	@Path("/make_payment/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response makePayment(
			@NotNull
			@PathParam("studentId") int studentId
			) {

		try {
			fee = registrationInterface.calculateFee(studentId);
	
	 		int totalRegistered = registrationInterface.totalRegisteredCourses(studentId);
	 		if(totalRegistered != 6) {
	 			return Response.status(409).entity("You have not registered for 6 courses!!").build();
	 		}
	 		
	 		boolean ispaid = false;
	
	 		boolean checkStatus = false;
			try {
				checkStatus = registrationInterface.isSemesterRegistered(studentId);
			}catch(SQLException se){
	 			return Response.status(409).entity(se.getMessage()).build();
			}
			
	 		ispaid = registrationInterface.getPaymentStatus(studentId);
	
	 		if(!checkStatus) {
	 			return Response.status(409).entity("You have not registered yet.").build();
	 		}
	 		else if(ispaid) {
	 			return Response.status(409).entity("You have already paid the fees.").build();
	 		}
	 		else if(checkStatus && !ispaid) {
	 			return Response.status(201).entity("You have to pay total Fees = " + fee).build();
	 		}
		} catch (SQLException e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
		return null;
	}
	
	
	@POST
	@Path("/make_payment/card")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response paymentByCard(
			CardPayment card) {
		try {
			fee = registrationInterface.calculateFee(card.getStudentId());
			int totalRegistered = registrationInterface.totalRegisteredCourses(card.getStudentId());
	 		if(totalRegistered != 6) {
	 			return Response.status(409).entity("You have not registered for 6 courses!!").build();
	 		}
	 		
	 		boolean ispaid = false;
	
	 		boolean checkStatus = false;
			try {
				checkStatus = registrationInterface.isSemesterRegistered(card.getStudentId());
			}catch(SQLException se){
	 			return Response.status(409).entity(se.getMessage()).build();
			}
			
	 		ispaid = registrationInterface.getPaymentStatus(card.getStudentId());
	
	 		if(!checkStatus) {
	 			return Response.status(409).entity("You have not registered yet.").build();
	 		}
	 		else if(ispaid) {
	 			return Response.status(409).entity("You have already paid the fees.").build();
	 		}
			invoiceId = Utils.generateId();
			int selected_mode =1;
			
	 		card.setInvoiceId(invoiceId);
	 		registrationInterface.paymentByCard(card);
	 		registrationInterface.setPaymentStatus(card.getStudentId(), invoiceId, fee);
	 		
	 		int notificationId=0;
	 		
			notificationId = notificationInterface.sendPaymentNotification(NotificationTypeConstant.PAYMENT, card.getStudentId(), selected_mode, fee, invoiceId);
			return Response.status(201).entity("Your Payment is done Successfully! \nNotification Id: " + notificationId+"\n"+"Keep it safe for future references.").build();

		}
		catch(SQLException e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/make_payment/cheque")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response paymentByCheque(
			Cheque cheque) {
		try {
			fee = registrationInterface.calculateFee(cheque.getStudentId());
			int totalRegistered = registrationInterface.totalRegisteredCourses(cheque.getStudentId());
	 		if(totalRegistered != 6) {
	 			return Response.status(409).entity("You have not registered for 6 courses!!").build();
	 		}
	 		
	 		boolean ispaid = false;
	
	 		boolean checkStatus = false;
			try {
				checkStatus = registrationInterface.isSemesterRegistered(cheque.getStudentId());
			}catch(SQLException se){
	 			return Response.status(409).entity(se.getMessage()).build();
			}
			
	 		ispaid = registrationInterface.getPaymentStatus(cheque.getStudentId());
	
	 		if(!checkStatus) {
	 			return Response.status(409).entity("You have not registered yet.").build();
	 		}
	 		else if(ispaid) {
	 			return Response.status(409).entity("You have already paid the fees.").build();
	 		}
			invoiceId = Utils.generateId();
			int selected_mode =2;
			
	 		cheque.setInvoiceId(invoiceId);
	 		registrationInterface.paymentByCheque(cheque);
	 		registrationInterface.setPaymentStatus(cheque.getStudentId(), invoiceId, fee);
	 		
	 		int notificationId=0;
	 		
			notificationId = notificationInterface.sendPaymentNotification(NotificationTypeConstant.PAYMENT, cheque.getStudentId(), selected_mode, fee, invoiceId);
			return Response.status(201).entity("Your Payment is done Successfully! \nNotification Id: " + notificationId+"\n"+"Keep it safe for future references.").build();

		}
		catch(SQLException e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/make_payment/netbanking")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response paymentByNetBanking(
			NetBanking netBanking) {
		try {
			fee = registrationInterface.calculateFee(netBanking.getStudentId());
			int totalRegistered = registrationInterface.totalRegisteredCourses(netBanking.getStudentId());
	 		if(totalRegistered != 6) {
	 			return Response.status(409).entity("You have not registered for 6 courses!!").build();
	 		}
	 		
	 		boolean ispaid = false;
	
	 		boolean checkStatus = false;
			try {
				checkStatus = registrationInterface.isSemesterRegistered(netBanking.getStudentId());
			}catch(SQLException se){
	 			return Response.status(409).entity(se.getMessage()).build();
			}
			
	 		ispaid = registrationInterface.getPaymentStatus(netBanking.getStudentId());
	
	 		if(!checkStatus) {
	 			return Response.status(409).entity("You have not registered yet.").build();
	 		}
	 		else if(ispaid) {
	 			return Response.status(409).entity("You have already paid the fees.").build();
	 		}
			invoiceId = Utils.generateId();
			int selected_mode =3;
			
			netBanking.setInvoiceId(invoiceId);
	 		registrationInterface.paymentByNetBanking(netBanking);
	 		registrationInterface.setPaymentStatus(netBanking.getStudentId(), invoiceId, fee);
	 		
	 		int notificationId=0;
	 		
			notificationId = notificationInterface.sendPaymentNotification(NotificationTypeConstant.PAYMENT, netBanking.getStudentId(), selected_mode, fee, invoiceId);
			return Response.status(201).entity("Your Payment is done Successfully! \nNotification Id: " + notificationId+"\n"+"Keep it safe for future references.").build();

		}
		catch(SQLException e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
	}

	/**
	 * View Grade Card
	 * @param studentId
	 * @param semesterId
	 */
	@GET
	@Path("/viewGradeCard/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)	
	public Vector<String> viewGradeCard(
			@NotNull
			@PathParam("studentId") int studentId
			) {
		// TODO Auto-generated method stub
		
		Vector<String>response=new Vector<String>();
		
		try {
			boolean isGenerated = registrationInterface.isGenerated(studentId);
	
			if(!isGenerated) {
				
				response.addElement("GradeCard is not generated yet.");
				return response;
			}
			else {
	
				try {
					Vector<GradeCard> grades = registrationInterface.viewGradeCard(studentId);
		
					if(grades.isEmpty()) {
						response.addElement("You haven't registered for any course.");
						return response;

					}
					
					double overallgpa = 0.0;
		
					for(GradeCard course_grade : grades) {
						response.add("Course Code:" + course_grade.getCourseId() + " GPA:" + course_grade.getGpa());
						overallgpa += course_grade.getGpa();
					}
		
					overallgpa /= (double)grades.size();
					response.add("Overall GPA: " + overallgpa);
		
					return response;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
