/**
 * 
 */
package com.crs.flipkart.constants;

/**
 * @author devanshugarg
 *
 */
public enum NotificationTypeConstant {

	REGISTRATION(1), APPROVAL(2), PAYMENT(3);
	
	@SuppressWarnings("unused")
	private final int notificationType;

	/**
	 * @param notificationType
	 */
	private NotificationTypeConstant(int notificationType) {
		this.notificationType = notificationType;
	}
	
	/**
	 * Method to Return Notification Type in String
	 * @return Notification Type in String
	 */
	@Override
	public String toString() {
		
		final String name = name();
		return name;
	}
	
	/**
	 * Method to obtain NotificationType Object depending upon User Input
	 * @param val_input
	 * @return NotificationType Object
	 */
	public static NotificationTypeConstant getNotificationType(int val_input) {
		
		NotificationTypeConstant notificationType = null;
		switch(val_input) {
		
		case 1:
			notificationType = NotificationTypeConstant.REGISTRATION;
			break;
		case 2:
			notificationType = NotificationTypeConstant.APPROVAL;
			break;
		case 3:
			notificationType = NotificationTypeConstant.PAYMENT;
			break;
		default:
			notificationType = null;
			break;
		}
		return notificationType;
	}
}
