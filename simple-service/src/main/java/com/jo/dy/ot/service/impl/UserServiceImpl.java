package com.jo.dy.ot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jo.dy.ot.dao.UserMapper;
import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save(User user) {
		int insert = userMapper.insertSelective(user);
		System.out.println("Userservice--"+insert);
	}

}
