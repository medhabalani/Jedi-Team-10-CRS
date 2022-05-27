/**
 * @author LENOVO
 *
 */
package com.crs.flipkart.restController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.crs.flipkart.bean.Student;
import com.crs.flipkart.exceptions.ConfirmPasswordException;
import com.crs.flipkart.exceptions.OldPasswordNotValidException;
import com.crs.flipkart.exceptions.UserNotFoundException;
import com.crs.flipkart.business.StudentInterface;
import com.crs.flipkart.business.StudentService;
import com.crs.flipkart.business.UserInterface;
import com.crs.flipkart.business.UserService;
import com.crs.flipkart.constants.RoleConstant;

/**
 * @author shubham
 *
 */

@Path("/user")
public class UserRestAPI {
	StudentInterface studentInterface=StudentService.getInstance();
	UserInterface userInterface =UserService.getInstance();
	
	
	/**
	 * 
	 * @param userId: email address of the user
	 * @param newPassword: new password to be stored in db.
	 * @return @return 201, if password is updated, else 500 in case of error
	 * @throws OldPasswordNotValidException 
	 * @throws UserNotFoundException 
	 */
	@PUT
	@Path("/updatePassword")
	public Response updatePassword(
			@NotNull
			@Email(message = "Invalid User ID: Not in email format")
			@QueryParam("userEmailId") String userEmailId,
			@NotNull
			@QueryParam("oldPassword") String oldPassword,
			@NotNull
			@QueryParam("newPassword") String newPassword,
			@NotNull
			@QueryParam("confirmNewPassword") String confirmNewPassword
		) throws ValidationException, UserNotFoundException, OldPasswordNotValidException {
		
		try {
			userInterface.updatePassword(userEmailId,oldPassword,newPassword,confirmNewPassword);
			return Response.status(201).entity("Password updated successfully! ").build();
		}
		catch(ConfirmPasswordException e) {
			return Response.status(409).entity("Error : " + e).build();
		}
		catch(OldPasswordNotValidException e) {
			return Response.status(409).entity("Error : " + e).build();
		}
		catch(UserNotFoundException e) {
			return Response.status(404).entity("Error : " + e).build();
		}
		
	}
	
	/**
	 * 
	 * @param userId
	 * @param password
	 * @return 
	 */
	
	@POST
	@Path("/login")
	public Response verifyCredentials(
			@NotNull
			@QueryParam("userEmailId") String userEmailId,
			@NotNull
			@QueryParam("password") String password) throws ValidationException {
		
		try 
		{
			boolean loggedin=userInterface.validateUser(userEmailId, password);
				if(loggedin)
				{
					String role=userInterface.getRoleOfUser(userEmailId);
					switch(role)
					{
					
					case "STUDENT":
						int userId = userInterface.getUserId(userEmailId);
						int studentId=studentInterface.getStudentId(userId);
						boolean isApproved=studentInterface.isApproved(studentId);
						if(!isApproved)	
						{
							return Response.status(200).entity("Login unsuccessful! Student "+userEmailId+" has not been approved by the administration!" ).build();
						}
						break;
						
					}
					return Response.status(200).entity("Login successful").build();
				}
				else
				{
					return Response.status(500).entity("Invalid credentials!").build();
				}
		}
		catch (UserNotFoundException e) 
		{
			return Response.status(500).entity(e.getMessage()).build();
		}		
		
}
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws ValidationException
	 */
	@GET
	@Path("/getRole")
	public String getRole(
			@NotNull
			@QueryParam("userEmailId") String userEmailId ) throws ValidationException{
		
		return "The Role of user is " + userInterface.getRoleOfUser(userEmailId);
	}
	
	/**
	 * 
	 * @param student object
	 * @return 201, if user is created, else 500 in case of error
	 */
	@POST
	@Path("/studentRegistration")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(@Valid Student student)
	{
		
		try
		{
			student.setRole(RoleConstant.STUDENT);
			studentInterface.register(student);
		}
		catch(Exception ex)
		{
			return Response.status(500).entity("Something went wrong! Please try again.").build(); 
		}
		
		
		return Response.status(201).entity("Registration Successful for studentId : "+ student.getStudentId()).build(); 
	}
	
	
}