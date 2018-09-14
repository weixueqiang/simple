package com.jo.dy.ot.service;

import java.util.List;

import com.jo.dy.ot.entity.LeaveBill;
import com.jo.dy.ot.util.Result;

public interface LeaveBillService extends BasicProcess{

	Result save(LeaveBill leaveBill);

	List<LeaveBill> list(Integer userId);

	void updateByKey(LeaveBill leaveBill);

}
