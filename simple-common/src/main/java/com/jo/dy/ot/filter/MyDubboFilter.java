package com.jo.dy.ot.filter;

import org.apache.log4j.Logger;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * 需要在resources/META-INF/dubbo/com.alibaba.dubbo.rpc.Filter的文件
 * 
 * @author  weixueqiang
 * @version 1.0.0
 * @date 2018年8月23日 上午11:28:00
 */
@Activate(value="myDubboFilter")
public class MyDubboFilter implements Filter{

	private static Logger logger=Logger.getLogger(MyDubboFilter.class);
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		Result result = invoker.invoke(invocation);
		String simpleName = invoker.getInterface().getSimpleName();
		String methodName = invocation.getMethodName();
		logger.info("dubbo过滤器:"+simpleName+"调用了"+methodName+"方法");
		return result;
	}

}
