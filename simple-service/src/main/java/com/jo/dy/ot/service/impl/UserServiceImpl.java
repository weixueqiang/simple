package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jo.dy.ot.dao.UserMapper;
import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.entity.UserExample;
import com.jo.dy.ot.service.OtherService;
import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.util.PageUtils;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	//@Resource
	private OtherService otherService;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void save(User user) {
		int insert = userMapper.insertSelective(user);
		System.out.println("Userservice--"+insert);
	}

	@Override
	public User get(int id) {
		String string = otherService.say("zhangsan");
		System.err.println(string);
		System.err.println();
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageUtils<User> showPage(PageUtils<User> page) {
		
		PageHelper.startPage(page.getPage(), page.getLimit());
	/*	List<User> selectByExample = userMapper.selectByExample(new UserExample());
		PageInfo<User> info=new PageInfo<User>(selectByExample);
		page.setData(selectByExample);
		page.setCount((int)info.getTotal());*/
		Page<User> list=(Page<User>)userMapper.selectByExample(new UserExample());
		return page.conver(list);
	}

	@Override
	public User getByName(String username) {
		return users.get(username);
	}
	
	private static Map<String,User> users=new HashMap<>();
	static {
		String username="zhangsan";
		users.put(username, new User(1, username, "zs123", "dafd"));
		username="lisi";
		users.put(username, new User(1, username, "ls123", "dafd2"));
		username="wangwu";
		users.put(username, new User(1, username, "ww123", "dafd3"));
	}
	

}
