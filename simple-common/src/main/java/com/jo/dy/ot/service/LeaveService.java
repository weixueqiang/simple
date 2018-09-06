package com.jo.dy.ot.service;

import java.util.List;

import com.jo.dy.ot.entity.Leave;
import com.jo.dy.ot.util.Result;

public interface LeaveService {

	Result save(Leave leave);
	
	List<Leave> listByUser(Integer id);
	
}
