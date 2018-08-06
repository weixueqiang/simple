package com.jo.dy.ot.aop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAop {

	private static Logger logger=Logger.getLogger(ControllerAop.class);
	
	@Before("execution(* com.jo.dy.ot.controller.*Controller.*(..))")
	public void before() {
		logger.info("aop测试...");
	}
	
	@Around("execution(* com.jo.dy.ot.controller.*Controller.*(..))")
	public void around(ProceedingJoinPoint point) {
		String className = point.getTarget().getClass().getName();
		Signature signature = point.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		String methodName = methodSignature.getName();
		logger.info(className+": "+methodName);
	}
	
	
}
