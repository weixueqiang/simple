package com.jo.dy.ot.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.ShiroException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * 无需其它配置,仅需作为组件扫描到即可
 * 
 * @author  weixueqiang
 * @version 1.0.0
 * @date 2018年8月6日 下午5:27:42
 */
@Component
public class DefaultExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView view = new ModelAndView();
		if(ex instanceof ShiroException) {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "没有权限");
			view.setView(jsonView);
		}else if(ex instanceof RuntimeException) {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "RuntimeException");
			view.setView(jsonView);
		}else {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "No  RuntimeException!");
			view.setView(jsonView);
		}
		 return view;
	}

}
