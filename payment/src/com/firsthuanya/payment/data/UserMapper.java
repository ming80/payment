package com.firsthuanya.payment.data;

import java.util.Map;

import com.firsthuanya.payment.domain.User;

public interface UserMapper {
	public User find(String id);
	public int count(Map<String,String> account);
}
