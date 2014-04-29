package com.firsthuanya.payment.data;

import java.util.List;

import com.firsthuanya.payment.domain.License;



public interface PaymentLicenseMapper {
	public void insert(License license);
//	public void update(MonthlyInsurance monthlyInsurance);
//	public void delete(String policyNo,int year,int month);
	public void delete(String paymentId);
//	public List<MonthlyInsurance> find(String companyNo,int year,int month);
}
