package com.jo.dy.ot.service;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.util.PageUtils;

public interface UserService {

	void save(User user);

	User get(int id);

	PageUtils<User> showPage(PageUtils<User> page);
	
}
