package com.jo.dy.ot.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jo.dy.ot.entity.User;
import com.jo.dy.ot.service.UserService;
import com.jo.dy.ot.util.MyUtils;
import com.jo.dy.ot.util.PageUtils;
import com.jo.dy.ot.util.Result;

@Controller
public class IndexController {

//	@Resource
	private UserService userService;
	
	@PostConstruct
	public void init() {
		System.out.println("控制器初始化了！");
	}
	
	@RequestMapping("index.do")
	public String index(HttpServletRequest request) {
		System.out.println("woqu!");
		return "index";
	}
	
	@RequestMapping("center")
	public String center() {
		return "center";
	}
	
	@RequestMapping("myInfo")
	@ResponseBody
	public Result myInfo() {
		Result result = new Result();
		result.setData(MyUtils.getUser());
		return result;
	}
	
	@RequestMapping("showUser.do")
	@ResponseBody
	@RequiresPermissions("user:get")
	public Result showUser(Integer id) {
		Result result = new Result();
		if(id<=0) {
			result.fail("id不能小于0");
			return result;
		}
		//result.setData(userService.get(id));
		return result;
	}
	
	@RequestMapping("showPage.do")
	@ResponseBody
	public PageUtils<User> showPage(PageUtils<User> page) {
		return userService.showPage(page);
	}
	
	@RequestMapping("ex")
	@ResponseBody
	@RequiresPermissions("user:ex")
	public Result ex(Integer id) throws ClassNotFoundException {
		throw new ClassNotFoundException();
	}
	
}
