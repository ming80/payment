package com.firsthuanya.payment.data;

import java.util.List;

import com.firsthuanya.payment.domain.Payment;


public interface PaymentMapper {
	public void insert(Payment payment);
	public void delete(String seriesNo);
	public Payment find(String id);
//	public int count(String companyNo);
	public List<Payment> findAll();
}
