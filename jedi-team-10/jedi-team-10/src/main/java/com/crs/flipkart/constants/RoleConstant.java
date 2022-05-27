/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author devanshugarg
 *
 */
public enum RoleConstant {

	ADMIN, PROFESSOR, STUDENT;
	
	/**
	 * Method to Return Role Type in String
	 * @return Role Type in String
	 */
	@Override
	public String toString() {
		
		final String name = name();
		return name;
	}
	
	/**
	 * Method to convert String to RoleConstant Object
	 * @param gendr
	 * @return RoleConstant Object
	 */
	public static RoleConstant stringToRole(String role) {
		
		RoleConstant userRole = null;
		if (role.equalsIgnoreCase("ADMIN")) {
			userRole = RoleConstant.ADMIN;
		} else if (role.equalsIgnoreCase("PROFESSOR")) {
			userRole = RoleConstant.PROFESSOR;
		} else if (role.equalsIgnoreCase("STUDENT")) {
			userRole = RoleConstant.STUDENT;
		}
		return userRole;
	}
}
