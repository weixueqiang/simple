package com.jo.dy.ot.service;

import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.util.Result;

public interface PermissionService {

	Result save(Permission permission);

	//事务测试..
	void saveWithTranction();
	void neverNeedTranction();
	void requiresNewTranction();
	void nestedTranction();
	void supportsTranction();
	void notSupportedTranction();
	
}
