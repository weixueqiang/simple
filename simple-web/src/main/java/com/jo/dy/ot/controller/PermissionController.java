package com.jo.dy.ot.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.service.PermissionService;
import com.jo.dy.ot.util.Result;
import com.jo.dy.ot.vo.PermissionVO;

@RestController
@RequestMapping("/permission")
public class PermissionController {

//	@Resource
	private PermissionService permissionService;
	
	/**
	 * 数据校验测试,需注意BindingResult对象需要紧跟在VO后面
	 * @date 2018年8月17日 下午5:44:32
	 * @author weixueqiang
	 */
	@RequestMapping("/save")
	@ResponseBody
	public Result save(@Valid PermissionVO permissionVO,BindingResult bindingResult) {
		Result result = new Result();
		if(bindingResult.hasErrors()) {
			result.fail(bindingResult.getFieldError().getDefaultMessage());
			return result;
		}
		System.out.println(permissionVO.getCreateTime());
		result.setData(permissionVO);
		return result;
		
	}
	

	
}
