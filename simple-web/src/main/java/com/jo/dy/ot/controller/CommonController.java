package com.jo.dy.ot.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import com.jo.dy.ot.util.Result;

@RestController
@RequestMapping("common")
public class CommonController {

	private Logger logger=Logger.getLogger(CommonController.class);
	
	@RequestMapping("upload")
	public Result upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
		Result result = new Result();
		String path2 = request.getContextPath();
		ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		String path = servletContext.getRealPath("/");
		logger.info(String.format("路径一:%s,路径二:%s", path2,path));
		logger.info("");
		result.setData("hehe");
		return result;
	}
	
	
}
