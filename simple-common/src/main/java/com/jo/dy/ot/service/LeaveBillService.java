package com.jo.dy.ot.service;

import java.util.List;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.util.Result;

public interface LeaveBillService {

	Result save(LeaveBill leaveBill);
	
	Result submitLeave(Integer id,Integer userId);

	List<LeaveBill> list(int i);

	void updateByKey(LeaveBill leaveBill);
	
}
