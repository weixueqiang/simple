package com.jo.dy.ot.shiro;

import java.io.Serializable;

import com.jo.dy.ot.entity.User;

public class MyPrincipal implements Serializable{

	private User user;
	//...其它属性

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
