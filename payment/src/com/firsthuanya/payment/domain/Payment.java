package com.firsthuanya.payment.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Payment {
	private String id;
	private String payer;
	private Date date;
	private BigDecimal amount;
	private List<License> license;
	
	private Payment(){		
	}
	
	public Payment(String id,
			String payer,
			Date date,
			BigDecimal amount,
			List<License> license){
		
		if(id == null && id.trim().equals(""))
			throw new IllegalArgumentException("id is empty!");
		if(payer == null && payer.trim().equals(""))
			throw new IllegalArgumentException("payer is empty!");
		if(date == null)
			throw new IllegalArgumentException("payment date is null!");
		if(amount == null)
			throw new IllegalArgumentException("amount is null!");
		if(license == null)
			throw new IllegalArgumentException("licenseNo list is null!");
		if(license.size() == 0)
			throw new IllegalArgumentException("licenseNo list is empty!");
		
		this.id = id;
		this.payer = payer;
		this.date = date;
		this.amount = amount;
		this.license = license;		
	}
	
	public String getId() {
		return id;
	}
	
	public String getPayer() {
		return payer;
	}
	
	public Date getDate() {
		return date;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public List<License> getLicense() {
		return license;
	}	
}
