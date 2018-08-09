package com.jo.dy.ot.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAop {

	private static Logger logger=Logger.getLogger(ControllerAop.class);
	
	@Pointcut("execution(* com.jo.dy.ot.controller.*Controller.*(..))")
	public void cutMethod() {
		
	}
	
	@Before("cutMethod()")
	public void around2(JoinPoint point) throws Throwable {
		logger.info("JoinPoint  before开始....");
		String className = point.getTarget().getClass().getName();
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		String methodName = methodSignature.getName();
		
		logger.info(className+": "+methodName);
		
	}
	
	
	@After("cutMethod()")
	public void after() {
		logger.info("aop  after测试...");
	}
	
	/**
	 * 需要参数ProceedingJoinPoint 执行并返回 ___ point.proceed();
	 * @date 2018年8月9日 上午10:09:28
	 * @author weixueqiang
	 */
	@Around("cutMethod()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		logger.info("ProceedingJoinPoint aspectj环绕开始....");
		String className = point.getTarget().getClass().getName();
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		String methodName = methodSignature.getName();
		Object result = point.proceed();
		logger.info("ProceedingJoinPoint aspectj环绕执行方法....");
		logger.info(className+": "+methodName);
		logger.info("ProceedingJoinPoint aspectj环绕结束....");
		return result;
	}
	
	
	
	
}
