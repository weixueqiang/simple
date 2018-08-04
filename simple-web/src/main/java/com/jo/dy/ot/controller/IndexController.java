package com.jo.dy.ot.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;

@Controller
public class IndexController {

	@Resource
	private UserService userService;
	
	@PostConstruct
	public void init() {
		System.out.println("控制器初始化了！");
	}
	
	@RequestMapping("index.do")
	public String index(HttpServletRequest request) {
		User user=userService.get(1);
		request.setAttribute("user", user);
		return "index";
	}
	
}
