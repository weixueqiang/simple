package com.jo.dy.ot.exception;

import org.apache.shiro.ShiroException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(Exception.class)//可以直接写@EceptionHandler，IOExeption继承于Exception
    public ModelAndView allExceptionHandler(Exception ex){
    	ModelAndView view = new ModelAndView();
		if(ex instanceof ShiroException) {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "没有权限");
			view.setView(jsonView);
		}else if(ex instanceof BindException) {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "参数异常");
			view.setView(jsonView);
		}else if(ex instanceof RuntimeException) {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "发生异常,请稍后重试!");
			view.setView(jsonView);
		}else {
			MappingJackson2JsonView jsonView=new MappingJackson2JsonView();
			jsonView.addStaticAttribute("success", false);
			jsonView.addStaticAttribute("msg", "发生异常,请稍后重试!");
			view.setView(jsonView);
		}
		 return view;
    }
}