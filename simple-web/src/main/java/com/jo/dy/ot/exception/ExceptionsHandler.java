package com.jo.dy.ot.exception;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.shiro.ShiroException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jo.dy.ot.util.Result;
import com.jo.dy.ot.util.SendEmailUtils;
/**
 * @ControllerAdvice是一个@Component，用于定义@ExceptionHandler，@InitBinder和@ModelAttribute方法，适用于所有使用@RequestMapping方法
 * 能捕获控制前框架抛出的错误,避免给前端展示不友好的界面,推荐使用
 * @author  weixueqiang
 * @version 1.0.0
 * @date 2018年8月21日 上午10:03:11
 */
@ControllerAdvice
public class ExceptionsHandler {

	private static final String ERROR_MSG="发生异常,请稍后重试!";
	
	@ExceptionHandler(value=Exception.class)
	@ResponseBody
	public Result exceptionHandler(Exception ex) {
		Result result = new Result();
		ex.printStackTrace();
		if(ex instanceof ShiroException) {
			result.fail("没有权限!");
		}else if(ex instanceof BindException) {
			result.fail("参数异常!");
		}else if(ex instanceof RuntimeException) {
			result.fail(ERROR_MSG);
			/*MimeMessage message = SendEmailUtils.getMimeMessage();
			try {
				message.setSubject("程序出现异常!");
				message.setText(ex.getMessage());
				SendEmailUtils.send(message, "903690574@qq.com");
			} catch (Exception e) {
				   e.printStackTrace();
			}*/
		}else {
			result.fail(ERROR_MSG);
		}
		return result;
	}
	
	
//    @ExceptionHandler(Exception.class)
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