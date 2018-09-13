package com.jo.dy.ot.service;

public interface BasicProcess {

	String getProcessKey(String customeId);

	Object get(Integer id);

	void dealBusiness(Integer id, Boolean flag);
	
}
