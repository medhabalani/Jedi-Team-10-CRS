/**
 * 
 */
package com.crs.flipkart.utils;

import java.util.UUID;

/**
 * @author LENOVO
 *
 */
public class Utils {
	
	public enum UserType{
		
		ADMIN, STUDENT, PROFESSOR
	}
	
	public static int generateId() {
		
		UUID idOne = UUID.randomUUID();
	    String str = "" + idOne;        
	    int uid = str.hashCode();
	    String filterStr = "" + uid;
	    str = filterStr.replaceAll("-", "");
	    return Integer.parseInt(str);
 	}
}
