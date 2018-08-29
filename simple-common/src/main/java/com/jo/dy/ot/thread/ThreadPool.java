package com.jo.dy.ot.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	private static ThreadPool instance;
	
	private static int corePoolSize = 3;
	private static int maximumPoolSize = 5;
	private static long keepAliveTime = 5L;
	/**
	 * 无界,此时最大的线程数不起作用,已core线程数执行
	 */
	//private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
	/**
	 * 该策略当没有线程使用时,会使用调用 该线程池的线程  来执行任务--很有可能造成当前线程也被阻塞
	 */
	private static RejectedExecutionHandler handler  = new ThreadPoolExecutor.CallerRunsPolicy();//new CallerRunsPolicy();
	
	private static ThreadPoolExecutor threads = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue,
			handler);
	
	public static ThreadPool getInstance() {
		if(instance==null) {
			synchronized (ThreadPool.class) {
				if(instance==null) {
					return new ThreadPool();
				}
			}
		}
		return instance;
	}

	public void excute(Runnable task) {
		threads.execute(task);
	}
	
	public void close() {
		threads.shutdown();
	}
}
