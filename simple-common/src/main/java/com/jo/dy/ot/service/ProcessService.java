package com.jo.dy.ot.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.jo.dy.ot.util.Result;


public interface ProcessService {

	List<Map<String, Object>> listByAssignee(String id,String processDefinitionKey);

	List<String> listFlow(String processInstanceId, String processDefinitionId);

	List<Comment> listComment(String processInstanceId);

	void saveComment(String taskId, String comment, Integer userId, String processInstanceId);

	Result complateTask(String taskId,String comment, Integer id, Boolean flag);
	/**
	 * 获取任务列表
	 * @date 2018年9月14日 下午2:21:45
	 * @author weixueqiang
	 */
	List<Map<String, Object>> listByUsers(String string, String processKey);

	ProcessInstance startProcess(String processKey,String string, String name, Integer id);

	Result getTask(String taskId);

}
