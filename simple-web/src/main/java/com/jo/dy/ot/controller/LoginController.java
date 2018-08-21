package com.jo.dy.ot.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jo.dy.ot.shiro.MyUsernamePasswordToken;
import com.jo.dy.ot.util.Result;

@RestController
public class LoginController {

	@RequestMapping("/login")
	public Result login(String username,String password) {
		Result result = new Result();
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			result.fail("密码或用户名不能为空!");
			return result;
		}
		MyUsernamePasswordToken token = new MyUsernamePasswordToken(username,password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()) {
			try {
				subject.login(token);
			}catch(LockedAccountException e) {
				result.fail("用户被锁定");
			}catch(UnknownAccountException e) {
				result.fail("不存在该账户");
			}catch(IncorrectCredentialsException e) {
				result.fail("凭证错误");
			}catch (Exception e) {
				result.fail(e.getMessage());
			}
		}
		result.setData(new Date());
		return result;
	}
	
}
