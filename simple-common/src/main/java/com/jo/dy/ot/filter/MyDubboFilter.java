package com.jo.dy.ot.filter;

import org.apache.log4j.Logger;
import org.apache.log4j.jmx.LoggerDynamicMBean;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

@Adaptive("myDubboFilter")
public class MyDubboFilter implements Filter{

	private static Logger logger=Logger.getLogger(MyDubboFilter.class);
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Result result = invoker.invoke(invocation);
		String className = invoker.getClass().getName();
		String methodName = invocation.getMethodName();
		logger.info("dubbo过滤器:"+className+"调用"+methodName);
		return result;
	}

}
