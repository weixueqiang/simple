package com.jo.dy.ot.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jo.dy.ot.dao.UserMapper;
import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.entity.UserExample;
import com.jo.dy.ot.service.OtherService;
import com.jo.dy.ot.service.PermissionService;
import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.util.PageUtils;

@Service("userService")
// @Component("userService")
// @Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	// @Resource
	private OtherService otherService;
	@Resource
	private PermissionService permissionService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(User user) {
		int insert = userMapper.insertSelective(user);
		System.out.println("Userservice--" + insert);
	}

	@Override
	public User get(int id) {
		// String string = otherService.say("zhangsan");
		// System.err.println(string);
		// System.err.println();
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public PageUtils<User> showPage(PageUtils<User> page) {

		PageHelper.startPage(page.getPage(), page.getLimit());
		/*
		 * List<User> selectByExample = userMapper.selectByExample(new UserExample());
		 * PageInfo<User> info=new PageInfo<User>(selectByExample);
		 * page.setData(selectByExample); page.setCount((int)info.getTotal());
		 */
		Page<User> list = (Page<User>) userMapper.selectByExample(new UserExample());
		return page.conver(list);
	}

	@Override
	public User getByName(String username) {
		return users.get(username);
	}

	private static Map<String, User> users = new HashMap<>();
	static {
		String username = "zhangsan";
		users.put(username, new User(1, username, "zs123", "dafd"));
		username = "lisi";
		users.put(username, new User(1, username, "ls123", "dafd2"));
		username = "wangwu";
		users.put(username, new User(1, username, "ww123", "dafd3"));
	}

	/**
	 * 无事务报错前的都能保存
	 */
	@Override
	public void saveNoTranction() {
		User record = new User(null, "test01", "mima01", "salt01");
		userMapper.insertSelective(record);
		User record2 = new User(null, "test01", "mima01", "salt01");
		userMapper.insertSelective(record2);

		// 开启一个事务
		// permissionService.saveWithTranction();
		// 未开启事务,本类中的方法只支持事务传递,并不支持其它的传播行为!!
		// withTranction();
		// saveWithTranction();
		// 调用的方法的传播行为Propagation.REQUIRES_NEW,若当前存在事务则将当前事务挂起,当前事务回滚,自身不回滚;若不存在则开启一个事务.
		// permissionService.requiresNewTranction();
		// 调用的方法的传播行为Propagation.NESTED,作为当前事务的嵌套事务执行,当前事务回滚,自身也回滚;若不存在事务则开启事务.
		// permissionService.nestedTranction();
		// 调用的方法的传播行为Propagation.SUPPORTS,当前存在事务则加入该事务,当前没事务则以非事务方式运行
		// permissionService.supportsTranction();
		int i = 1 / 0;
	}

	/**
	 * 被调用时未开启新事务..............
	 */
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void withTranction() {
		User record = new User(null, "test012", "mima012", "salt012");
		userMapper.insertSelective(record);
		int i = 1 / 0;
	}

	/**
	 * 默认值propagation=Propagation.REQUIRED需要事务,若有事务则加入该事务,若没事务则开启一个事务
	 * 
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveWithTranction() {
		// 事务传递,报错回滚
		// saveNoTranction();
		// 事务传递,报错回滚
		// withTranction();
		// 事务传递,调用的方法的传播行为Propagation.NEVER,不支持事务直接抛出异常
		// permissionService.neverNeedTranction();
		// 调用的方法的传播行为Propagation.REQUIRES_NEW,若当前存在事务则将当前事务挂起,当前事务回滚,自身不回滚;若不存在则开启一个事务.
		// permissionService.requiresNewTranction();
		// 调用的方法的传播行为Propagation.NESTED,作为当前事务的嵌套事务执行,当前事务回滚,自身也回滚;若不存在事务则开启事务.
		// permissionService.nestedTranction();
		// 调用的方法的传播行为Propagation.SUPPORTS,当前存在事务则加入该事务,当前没事务则以非事务方式运行
		// permissionService.supportsTranction();
		// 调用的方法的传播行为Propagation.NOT_SUPPORTED,以非事务方式运行,当前存在事务则将当前事务挂起
		// permissionService.notSupportedTranction();
		// User record=new User(null,"test01","mima01","salt01");
		// userMapper.insertSelective(record);
		// User record2=new User(null,"test01","mima01","salt01");
		// userMapper.insertSelective(record2);
		// int i=1/0;

	}

}
