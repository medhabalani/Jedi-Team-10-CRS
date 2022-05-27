/**
 * 
 */
package com.crs.flipkart.bean;

/**
 * @author devanshugarg
 *
 */
public class NetBanking extends Payment {
	
	private String bankName;
	private String bankAccountHolderName;
	
	/**
	 * Default Constructor
	 */
	public NetBanking() {
		
	}

	/**
	 * @param studentId
	 * @param invoiceId
	 * @param status
	 * @param amount
	 * @param bankName
	 * @param bankAccountHolderName
	 */
	public NetBanking(int studentId, int invoiceId, boolean status, double amount, String bankName,
			String bankAccountHolderName) {
		super(studentId, invoiceId, status, amount);
		this.bankName = bankName;
		this.bankAccountHolderName = bankAccountHolderName;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankAccountHolderName
	 */
	public String getBankAccountHolderName() {
		return bankAccountHolderName;
	}

	/**
	 * @param bankAccountHolderName the bankAccountHolderName to set
	 */
	public void setBankAccountHolderName(String bankAccountHolderName) {
		this.bankAccountHolderName = bankAccountHolderName;
	}
	
}
