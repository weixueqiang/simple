package com.jo.dy.ot.controller;

import java.io.File;
import java.io.IOException;

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
@RequestMapping("/flow")
public class FlowController {

	private Logger logger=Logger.getLogger(FlowController.class);
	
	/**
	 * 简单地保存在部署的目录下
	 * @date 2018年8月9日 上午11:10:57
	 * @author weixueqiang
	 */
	@RequestMapping("upload")
	public Result upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception, IOException {
		Result result = new Result();
		String path2 = request.getContextPath();
		ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
		String path = servletContext.getRealPath("/");
		logger.info(String.format("路径一:%s,路径二:%s", path2,path));
		String filename = file.getOriginalFilename();
		file.transferTo(new File(path+"/images/upload/"+filename));
		result.setData("/images/upload/"+filename);
		return result;
	}
	
	
}
