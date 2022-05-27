/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class Notification {

	private int notificationId;
	private int studentId;
	private String notificationType;
	private String referenceId;
	private String notificationContent;
	
	/**
	 * Default Constructor
	 */
	public Notification() {
		
	}

	/**
	 * @param notificationId
	 * @param studentId
	 * @param notificationType
	 * @param referenceId
	 * @param notificationContent
	 */
	public Notification(int notificationId, int studentId, String notificationType, String referenceId,
			String notificationContent) {
		super();
		this.notificationId = notificationId;
		this.studentId = studentId;
		this.notificationType = notificationType;
		this.referenceId = referenceId;
		this.notificationContent = notificationContent;
	}

	/**
	 * @return the notificationId
	 */
	public int getNotificationId() {
		return notificationId;
	}

	/**
	 * @param notificationId the notificationId to set
	 */
	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the notificationType
	 */
	public String getNotificationType() {
		return notificationType;
	}

	/**
	 * @param notificationType the notificationType to set
	 */
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the notificationContent
	 */
	public String getNotificationContent() {
		return notificationContent;
	}

	/**
	 * @param notificationContent the notificationContent to set
	 */
	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}
	
}
