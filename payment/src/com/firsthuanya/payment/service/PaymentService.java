package com.firsthuanya.payment.service;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import com.firsthuanya.payment.data.PaymentLicenseMapper;
import com.firsthuanya.payment.data.PaymentMapper;
import com.firsthuanya.payment.domain.License;
import com.firsthuanya.payment.domain.Payment;


public class PaymentService {
	private final static String RESOURCES =
			"com/firsthuanya/payment/data/Configuration.xml";	
		
	private SqlSession openSession() throws OpenSessionException{
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(RESOURCES);
		} catch (IOException e) {
			throw new OpenSessionException("get resource failed!",e);
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();	
		return builder.build(reader).openSession();
	}
	
	public void addPayment(Payment payment) throws OpenSessionException{
		if(payment == null)
			throw new IllegalArgumentException("payment is null!");
		
		SqlSession session = openSession();
		try{
			PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
			PaymentLicenseMapper paymentLicenseMapper = session.getMapper(PaymentLicenseMapper.class);
			
			paymentMapper.insert(payment);
			for(License paymentLicense:payment.getLicense())
				paymentLicenseMapper.insert(paymentLicense);
			
			session.commit();
		}
		finally{
			session.close();
		}
	}

	public void removePayment(String id) throws OpenSessionException{
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("payment id is null");
		
		SqlSession session = openSession();
		try{
			PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
			PaymentLicenseMapper paymentLicenseMapper = session.getMapper(PaymentLicenseMapper.class);
			
			paymentMapper.delete(id);
			paymentLicenseMapper.delete(id);
			session.commit();
		}
		finally{
			session.close();
		}
	}
	
	public Payment getPayment(String id) throws OpenSessionException{
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("payment id is null");
		
		SqlSession session = openSession();
		try{
			PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
			
			return paymentMapper.find(id);
		}
		finally{
			session.close();
		}
	}
	
	public List<Payment> getPayments(Map<String,Object> queryConditions) throws OpenSessionException{
		SqlSession session = null;
		try{
			session = openSession();
			PaymentMapper mapper = session.getMapper(PaymentMapper.class);
			return mapper.findPayments(queryConditions);
		}
		finally{
			if(session != null) session.close();
		}
	}
	
	public List<Payment> getAll() throws OpenSessionException{
		SqlSession session = openSession();
		try{
			PaymentMapper mapper = session.getMapper(PaymentMapper.class);
			return mapper.findAll();
		}
		finally{
			session.close();
		}
	}
	
	public boolean hasSuchPayment(String id) throws OpenSessionException{
		SqlSession session = openSession();
		try{
			PaymentMapper paymentMapper = session.getMapper(PaymentMapper.class);
			return (paymentMapper.find(id) != null);			
		}
		finally{
			session.close();
		}
	}
	
	public static void main(String[] args){
		PaymentService paymentService = new PaymentService();
		
		
		Payment payment;
		try {
//			List<License> license = new ArrayList();
//			license.add(new License("id3","aaa3"));
//			license.add(new License("id3","aaa33"));
//			license.add(new License("id3","aaa333"));
//			payment = new Payment("id3",
//					"payer3",
//					new Date(),
//					new BigDecimal("33"),
//					license);
//			paymentService.removePayment("id3");
			
			Map<String,Object> condition = new HashMap();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");			
			
			condition.put("paymentDateFrom", format.parse("2014-04-29"));
			condition.put("paymentDateTo", format.parse("2014-04-29 23:59:59"));
			List<Payment> list = paymentService.getPayments(condition);
			
			System.out.print(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
