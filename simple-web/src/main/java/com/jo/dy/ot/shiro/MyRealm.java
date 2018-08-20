package com.jo.dy.ot.shiro;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;


@Component("myRealm")
public class MyRealm extends AuthorizingRealm{

//	@Resource
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		MyPrincipal myPrincipal=(MyPrincipal)super.getAvailablePrincipal(arg0);
		User user = myPrincipal.getUser();
		List<Permission> permission = permissions.get(user.getId());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> stringPermissions=new HashSet<>();
		if(!CollectionUtils.isEmpty(permission)) {
			permission.forEach((e)->stringPermissions.add(e.getCode()));
		}
		info.setStringPermissions(stringPermissions);
		return info;
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
	private static Map<Integer,List<Permission>> permissions=new HashMap<>();
	static {
		String username="zhangsan";
		users.put(username, new User(1, username, "zs123", "dafd"));
		username="lisi";
		users.put(username, new User(2, username, "ls123", "dafd2"));
		username="wangwu";
		users.put(username, new User(3, username, "ww123", "dafd3"));
		
		List<Permission> list=new ArrayList<>();
		list.add(new Permission(1, "获取用户", "user:get"));
		permissions.put(1, list);
		
		
	}
	
	
}