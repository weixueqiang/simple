package com.jo.dy.ot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.OtherService;
import com.jo.dy.ot.service.UserService;

@Service("otherService")
public class OtherServiceImpl implements OtherService {

	@Resource
	private UserService userService;
	
	@Override
	public String say(String name) {
		User user = userService.getByName(name);
		System.out.println("hello i`m "+user.getId());
		return name;
	}

}
