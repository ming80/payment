package com.firsthuanya.payment.action;


import java.util.Map;

import org.apache.log4j.Logger;

import com.firsthuanya.payment.domain.User;
import com.firsthuanya.payment.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private String userID;
	private String password;
	private static Logger logger = Logger.getLogger(LoginAction.class);
	
	public String login() throws Exception{
		Map<String,Object> session = ActionContext.getContext().getSession();
				
		UserService userService = new UserService();
		User user = userService.getUser(this.userID);
		session.put("user",user);
		
		logger.info(user.getName() + " login!");

		return SUCCESS;
	}
	
	public void validate(){
		if(userID == null || "".equals(userID.trim()))
			addFieldError("userID","�û�������Ϊ��!");
		
		if(password == null || "".equals(password.trim()))
			addFieldError("password","���벻��Ϊ��!");
		
		UserService service = new UserService();
		
		if(!hasFieldErrors() && !service.hasSuchUser(this.userID, this.password))
		addActionError("��¼ʧ��,�û������������!");			
		
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
