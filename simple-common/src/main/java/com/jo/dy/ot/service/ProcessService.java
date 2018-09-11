package com.jo.dy.ot.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;


public interface ProcessService {

	List<Map<String, Object>> list(Integer id,String processDefinitionKey);

	List<String> listFlow(String processInstanceId, String processDefinitionId);

	List<Comment> listComment(String processInstanceId);

	void saveComment(String taskId, String comment, Integer userId, String processInstanceId);

}
