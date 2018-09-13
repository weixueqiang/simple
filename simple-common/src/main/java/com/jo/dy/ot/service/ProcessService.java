package com.jo.dy.ot.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;

import com.jo.dy.ot.util.Result;


public interface ProcessService {

	List<Map<String, Object>> listByAssignee(String id,String processDefinitionId);

	List<String> listFlow(String processInstanceId, String processDefinitionId);

	List<Comment> listComment(String processInstanceId);

	void saveComment(String taskId, String comment, Integer userId, String processInstanceId);

	Result complateTask(String taskId,String processDefintionId, String comment, Integer id, Boolean flag);

}
