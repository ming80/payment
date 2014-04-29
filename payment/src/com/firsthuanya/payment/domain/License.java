package com.firsthuanya.payment.domain;

public class License {
	private String paymentId;
	private String licenseNo;

	private License(){		
	}
	
	public License(String paymentId,String licenseNo){
		if(paymentId == null || paymentId.equals(""))
			throw new IllegalArgumentException("paymentId is empty!");
		if(licenseNo == null || licenseNo.equals(""))
			throw new IllegalArgumentException("licenseNo is empty!");
		
		this.paymentId = paymentId;
		this.licenseNo = licenseNo;
	}
	
	public String getLicenseNo() {
		return licenseNo;
	}
	
	public String getPaymentId(){
		return paymentId;
	}
}
