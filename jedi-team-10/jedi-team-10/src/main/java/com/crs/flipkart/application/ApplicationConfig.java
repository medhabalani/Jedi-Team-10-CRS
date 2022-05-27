/**
 * 
 */

/**
 * @author LENOVO
 *
 */
package com.crs.flipkart.application;

import org.glassfish.jersey.server.ResourceConfig;

import com.crs.flipkart.restController.AdminRestAPI;
import com.crs.flipkart.restController.ProfessorRestAPI;
import com.crs.flipkart.restController.StudentRestAPI;
import com.crs.flipkart.restController.UserRestAPI;


/*
 * This is the ApplicationConfig file which extends ResourceConfig
 * and here we register all the REST API's class (inside com.flipkart.restController)
 */

public class ApplicationConfig extends ResourceConfig {

	public ApplicationConfig() {
		
		//Student Rest API register
		register(StudentRestAPI.class);
		
		//Professor Rest API register
		register(ProfessorRestAPI.class);
	
		//Admin Rest API register
		register(AdminRestAPI.class);
		
		//User Rest API register
		register(UserRestAPI.class);

	}

}