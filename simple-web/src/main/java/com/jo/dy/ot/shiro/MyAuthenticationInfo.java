package com.jo.dy.ot.shiro;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.util.ByteSource;

public class MyAuthenticationInfo extends SimpleAuthenticationInfo {

	// 可扩展属性
	private String ex;

	public MyAuthenticationInfo() {
		
	}
	
	public MyAuthenticationInfo(Object principal, Object credentials, String realmName) {
		super(principal, credentials, realmName);
	}

	public MyAuthenticationInfo(Object principal, Object credentials, String realmName,String ex) {
		super(principal, credentials, realmName);
		this.ex=ex;
	}
	
	 public MyAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName) {
	       super(principal, hashedCredentials, credentialsSalt, realmName);
	    }
	
	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

}
