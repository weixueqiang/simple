package com.jo.dy.ot.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.jo.dy.ot.enums.LoginTypeEnmu;

public class MyUsernamePasswordToken extends UsernamePasswordToken{
	//TODO 任意扩展属性
	private LoginTypeEnmu type;
	
	
	public MyUsernamePasswordToken() {
		
	}
	
	public MyUsernamePasswordToken(String username,String password) {
		super(username, password);
	}

	public MyUsernamePasswordToken(String username,String password,LoginTypeEnmu type) {
		super(username, password);
		this.type=type;
	}
	
	public LoginTypeEnmu getType() {
		return type;
	}

	public void setType(LoginTypeEnmu type) {
		this.type = type;
	}
	
}
