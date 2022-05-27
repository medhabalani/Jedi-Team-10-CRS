/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author devanshugarg
 *
 */
public enum GenderConstant {

	MALE(1), FEMALE(2), OTHER(3);
	
	@SuppressWarnings("unused")
	private final int gender;

	/**
	 * @param gender
	 */
	private GenderConstant(int gender) {
		this.gender = gender;
	}
	
	/**
	 * Method to Return Gender Type in String
	 * @return Gender Type in String
	 */
	@Override
	public String toString() {
		
		final String name = name();
		return name;
	}
	
	/**
	 * Method to obtain GenderConstant Object depending upon User Input
	 * @param val_input
	 * @return GenderConstant Object
	 */
	public static GenderConstant getName(int val_input) {
		
		GenderConstant gender = GenderConstant.OTHER;
		switch(val_input) {
		
		case 1:
			gender = GenderConstant.MALE;
			break;
		case 2:
			gender = GenderConstant.FEMALE;
			break;
		}
		return gender;
	}
	
	/**
	 * Method to convert String to GenderConstant Object
	 * @param gendr
	 * @return GenderConstant Object
	 */
	public static GenderConstant stringToGender(String gendr) {
		
		GenderConstant gender = GenderConstant.OTHER;
		if (gendr.equalsIgnoreCase("MALE")) {
			gender = GenderConstant.MALE;
		} else if (gendr.equalsIgnoreCase("FEMALE")) {
			gender = GenderConstant.FEMALE;
		} else if (gendr.equalsIgnoreCase("OTHER")) {
			gender = GenderConstant.OTHER;
		}
		return gender;
	}
	
}
