package com.jo.dy.ot.shiro;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm{

//	@Resource
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		  return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		MyUsernamePasswordToken token=(MyUsernamePasswordToken)arg0;
		String username = token.getUsername();
		if(StringUtils.isBlank(username)) {
			throw new RuntimeException("用户名为空!");
		}
//		User user=userService.getByName(username);
		User user=users.get(username);
		if(user==null) {
			throw new RuntimeException("没有该用户!");
		}
		MyPrincipal principal = new MyPrincipal();
		principal.setUser(user);
		return new MyAuthenticationInfo(principal, user.getPassword(), this.getName());
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
