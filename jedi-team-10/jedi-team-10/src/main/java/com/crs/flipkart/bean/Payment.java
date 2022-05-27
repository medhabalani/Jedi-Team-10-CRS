/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author LENOVO
 *
 */
public class Payment {
	
	private int studentId;
	private int invoiceId;
	private boolean status;
	private double amount;
	
	/**
	 * Default Constructor
	 */
	public Payment() {
		
	}

	/**
	 * @param studentId
	 * @param invoiceId
	 * @param status
	 * @param amount
	 */
	public Payment(int studentId, int invoiceId, boolean status, double amount) {
		super();
		this.studentId = studentId;
		this.invoiceId = invoiceId;
		this.status = status;
		this.amount = amount;
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
	 * @return the invoiceId
	 */
	public int getInvoiceId() {
		return invoiceId;
	}

	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
