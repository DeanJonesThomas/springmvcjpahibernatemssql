package com.java.model;


/**
 * @author 
 *
 */

public class Payment {
	
	private String ccNumber;
	private String ccExpDate;
	private String amt;
	public String getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}
	public String getCcExpDate() {
		return ccExpDate;
	}
	public void setCcExpDate(String ccExpDate) {
		this.ccExpDate = ccExpDate;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	
}
