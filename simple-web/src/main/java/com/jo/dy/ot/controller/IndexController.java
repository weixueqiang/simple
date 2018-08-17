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
	
	/**
	 * 返回登录页面,未认证主动跳转该请求
	 * @date 2018年8月17日 下午5:39:00
	 * @author weixueqiang
	 */
	@RequestMapping("index.do")
	public String index(HttpServletRequest request) {
		return "index";
	}
	
	/**
	 * 用户中心,登录页面登录成功后自动加载该页面
	 * @date 2018年8月17日 下午5:40:23
	 * @author weixueqiang
	 */
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
	
	/**
	 * token登录演示
	 * @date 2018年8月17日 下午5:41:16
	 * @author weixueqiang
	 */
	@RequestMapping("token")
	@ResponseBody
	public Result tokenTest() {
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
	
	/**
	 * 分页演示
	 * @date 2018年8月17日 下午5:41:34
	 * @author weixueqiang
	 */
	@RequestMapping("showPage.do")
	@ResponseBody
	public PageUtils<User> showPage(PageUtils<User> page) {
		return userService.showPage(page);
	}
	
	/**
	 * 异常演示
	 * @date 2018年8月17日 下午5:41:47
	 * @author weixueqiang
	 */
	@RequestMapping("ex")
	@ResponseBody
	@RequiresPermissions("user:ex")
	public Result ex(Integer id) throws ClassNotFoundException {
		throw new ClassNotFoundException();
	}
	
}
