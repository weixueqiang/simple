package com.jo.dy.ot.service;

import com.jo.dy.ot.entity.SysWorkflow;
import com.jo.dy.ot.util.Result;

public interface SysWorkflowService {

	Result save(SysWorkflow model,String stepArr);

	Result simpleSave(SysWorkflow model, String stepArr);
	
}
