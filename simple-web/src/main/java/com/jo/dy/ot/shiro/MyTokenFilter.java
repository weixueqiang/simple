package com.jo.dy.ot.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.util.Constants;

public class MyTokenFilter extends AccessControlFilter {

	private static Logger logger=Logger.getLogger(MyTokenFilter.class);
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return true;
		}
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		String token = req.getHeader(Constants.TOKEN);
		if(StringUtils.isBlank(token)) {
			logger.warn("未发现token!");
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		User user = users.get(token);
		if(user==null) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		MyUsernamePasswordToken myToken = new MyUsernamePasswordToken(user.getUsername(),user.getPassword());
		try {
			subject.login(myToken);
		} catch (Exception e) {
			logger.error("token 登录失败",e);
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		return true;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		  return false;
	}
	
	private static Map<String,User> users=new HashMap<>();
	static {
		String username="zhangsan";
		users.put(username, new User(1, username, "zs123", "dafd"));
		username="lisi";
		users.put(username, new User(2, username, "ls123", "dafd2"));
		username="wangwu";
		users.put(username, new User(3, username, "ww123", "dafd3"));
	}

}
