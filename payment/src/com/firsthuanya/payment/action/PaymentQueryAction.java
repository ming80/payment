package com.firsthuanya.payment.action;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.firsthuanya.payment.domain.Payment;
import com.firsthuanya.payment.service.OpenSessionException;
import com.firsthuanya.payment.service.PaymentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PaymentQueryAction  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PaymentQueryAction.class);
	
	private JSONObject results;
	private long rows;//ÿҳ��ʾ�ļ�¼��,datagrid���ṩ��������    
    private long page;//��ǰ�ڼ�ҳ,datagrid���ṩ��������        
    
    private String sort;
    private String order;

    private String paymentDateFrom;
    private String paymentDateTo;
    
    private long queryTimestamp;	//ʱ���
    
	public String queryPayment() throws Exception{		
		Map<String, Object> session = ActionContext.getContext().getSession();
		
		List<Payment> results = doResults(session);
		
		//����
		sortResults(results,this.sort);
		
		//server�˷�ҳ
		List<Payment> currentPageRows = new ArrayList<Payment>();
		Iterator<Payment> iter = results.iterator();
		for(int index = 0;index < (this.page - 1) * this.rows;index++)
			iter.next();
		for(int i = 0;i < this.rows;i++)
			if(iter.hasNext())
				currentPageRows.add((Payment)iter.next());
			else
				break;
		
		Map<String,Object> jsonMap = new HashMap<String,Object>();
			
		jsonMap.put("rows", currentPageRows);	//rowsΪdatagrid������,��ʾҪ��ʾ�ļ�¼		
		jsonMap.put("total", results.size());	//totalΪdatagrid������,��ʾ���ж��ټ�¼	
//		jsonMap.put("footer", summarize(results));
		this.setResults(JSONObject.fromObject(jsonMap));
//		System.out.print(jsonMap);
		
		session.put("results", results);
		return SUCCESS;
	}
	
	private List<Payment> doResults(Map<String, Object> session) throws ParseException, OpenSessionException{		
				
		Long paymentQueryTimestamp = (Long)session.get("paymentQueryTimestamp");
		//��������е�ʱ�����session�б����ʱ�����һ��˵����һ���µĲ�ѯ����,ִ��һ�β�ѯ
		if(paymentQueryTimestamp == null || paymentQueryTimestamp != this.queryTimestamp){			
			session.put("paymentQueryTimestamp", this.queryTimestamp);
			
			//��ѯ��������
			Map<String,Object> queryConditions = new HashMap();
			
			if(!isEmpty(getPaymentDateFrom()))
				queryConditions.put("paymentDateFrom", parseDate(getPaymentDateFrom() + " 00:00:00"));
			if(!isEmpty(getPaymentDateTo()))
				queryConditions.put("paymentDateTo", parseDate(getPaymentDateTo() + " 23:59:59"));
			
			PaymentService service = new PaymentService();
			List<Payment> results = service.getPayments(queryConditions);
			
			return results;
		}
		
		if(session.get("results") != null)
			return (List<Payment>)session.get("results");
		
		return new ArrayList<Payment>();
	}

	//����
	private void sortResults(List<Payment> list,final String sort){
		if(this.sort == null || this.sort.trim().equals(""))
			return;
		if(this.order == null || this.order.trim().equals(""))
			return;
		
		//�������
		Comparator<Payment> comparator = new 
			Comparator<Payment>(){
				@Override
				public int compare(Payment payment1, Payment payment2) {
//					if("policyNo".equals(sort))
//						return doCompare(policy1.getPolicyNo(),policy2.getPolicyNo());
					
//					if("brokerNo".equals(sort))
//						return doCompare(policy1.getBrokerNo(),policy2.getBrokerNo());
//					if("insured".equals(sort))
//						return doCompare(policy1.getInsured(),policy2.getInsured());	
//					if("premium".equals(sort))
//						return doCompare(policy1.getPremium(),policy2.getPremium()); 
//					if("cost".equals(sort))
//						return doCompare(policy1.getCost(),policy2.getCost()); 
//					if("insuranceCompany".equals(sort))
//						return doCompare(policy1.getInsuranceCompany(),policy2.getInsuranceCompany()); 
//					if("factorageRate".equals(sort))
//						return doCompare(policy1.getFactorageRate(),policy2.getFactorageRate()); 
//					if("factorage".equals(sort))
//						return doCompare(policy1.getFactorage(),policy2.getFactorage()); 
//					if("insuranceType".equals(sort))
//						return doCompare(policy1.getInsuranceType(),policy2.getInsuranceType()); 
//					if("invoiceNo".equals(sort))
//						return doCompare(policy1.getInvoiceNo(),policy2.getInvoiceNo());
//					if("inputDate".equals(sort))
//						return doCompare(policy1.getInputDate(),policy2.getInputDate());
//					if("remarkInfo".equals(sort))
//						return doCompare(policy1.getRemarkInfo(),policy2.getRemarkInfo());
//					if("remarkInfo.operationDate".equals(sort))
//						return doCompare(policy1.getRemarkInfo(),policy2.getRemarkInfo());	//RemarkInfo�����������������
//					if("settlementInfo".equals(sort))
//						return doCompare(policy1.getSettlementInfo(),policy2.getSettlementInfo());
//					if("settlementInfo.operationDate".equals(sort))
//						return doCompare(policy1.getSettlementInfo(),policy2.getSettlementInfo());	//SettlementInfo�����������������			
					
					return payment1.getDate().compareTo(payment2.getDate());
				}
				
				private int doCompare(Comparable comp1,Comparable comp2){
					if(comp1 == null) return -1;
					if(comp2 == null) return 1;
					return comp1.compareTo(comp2);
				}
			};
		
		//���ݸ��������������������
		if("asc".equals(this.getOrder()))
			Collections.sort(list,comparator);
		//���ݸ������������������
		if("desc".equals(this.getOrder()))
			Collections.sort(list,Collections.reverseOrder(comparator));
	}
	
	public JSONObject getResults() {
		return results;
	}

	public void setResults(JSONObject results) {
		this.results = results;
	}

	public long getRows() {
		return rows;
	}

	public void setRows(long rows) {
		this.rows = rows;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	private boolean isEmpty(String str){
		if(str == null) return true;
		if(str.trim().equals("")) return true;
		return false;
	}


	
	public String getPaymentDateFrom() {
		return paymentDateFrom;
	}

	public void setPaymentDateFrom(String paymentDateFrom) {
		this.paymentDateFrom = paymentDateFrom;
	}

	public String getPaymentDateTo() {
		return paymentDateTo;
	}

	public void setPaymentDateTo(String paymentDateTo) {
		this.paymentDateTo = paymentDateTo;
	}
	
	private Date parseDate(String aDate) throws ParseException{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //��дH��ʾ24Сʱ��ʾ,����0�����ʾ��12
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.parse(aDate);
	}
	


	public long getQueryTimestamp() {
		return queryTimestamp;
	}

	public void setQueryTimestamp(long queryTimestamp) {
		this.queryTimestamp = queryTimestamp;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
