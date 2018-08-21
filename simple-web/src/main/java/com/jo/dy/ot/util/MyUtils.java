package com.jo.dy.ot.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.shiro.MyPrincipal;

public class MyUtils {

	public static MyPrincipal curMyPrincipal() {
		Subject subject = SecurityUtils.getSubject();
		return (MyPrincipal)subject.getPrincipal();
	}
	
	public static User getUser() {
		return curMyPrincipal().getUser();
	}
	
}
