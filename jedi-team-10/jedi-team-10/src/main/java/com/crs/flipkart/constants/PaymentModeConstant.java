/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author devanshugarg
 *
 */
public enum PaymentModeConstant {

	CARD, CHEQUE, NET_BANKING;
	
	/**
	 * Method to Return Payment Type in String
	 * @return Payment Type in String
	 */
	@Override
	public String toString() {
		
		final String name = name();
		return name;
	}
	
	/**
	 * Method to get Payment Mode depending upon User Input
	 * @param val_input
	 * @return Payment Mode
	 */
	public static PaymentModeConstant getPaymentMode(int val_input) {
		
		switch(val_input) {
		
		case 1:
			return PaymentModeConstant.CARD;
		case 2:
			return PaymentModeConstant.CHEQUE;
		case 3:
			return PaymentModeConstant.NET_BANKING;
		default:
			return null;
		}
	}
}
