package com.jo.dy.ot.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken{

	//TODO 任意扩展属性
	public MyUsernamePasswordToken() {
		
	}
	
	public MyUsernamePasswordToken(String username,String password) {
		super(username, password);
	}
	
}
