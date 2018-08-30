package com.jo.dy.ot.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	private static ThreadPool instance;
	
	private static int corePoolSize = 3;
	private static int maximumPoolSize = 5;
	private static long keepAliveTime = 5L;
	/**
	 * 无界,此时最大的线程数maximumPoolSize不起作用,以corePoolSize线程数执行
	 */
	//private static BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	/**
	 * 有界,初始化需要指定长度,当队列已满,则会创建新线程,最大数量到maximumPoolSize
	 */
	private static BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(3);
	/**
	 * 直接提交策略表示线程池不对任务进行缓存,新进任务直接提交给线程池，当线程池中没有空闲线程时，创建一个新的线程处理此任务,这种策略需要线程池具有无限增长的可能性
	 */
	//private static BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();
	/**
	 * 该策略当没有线程使用时,会使用调用执行 该线程池的线程  来执行任务--很有可能造成调用线程池的线程被阻塞
	 */
	private static RejectedExecutionHandler handler  = new ThreadPoolExecutor.CallerRunsPolicy();//new CallerRunsPolicy();
	
	/**
	 * jdk默认策略，队列满并线程满时直接拒绝添加新任务，并抛出异常
	 */
	//private static RejectedExecutionHandler handler=new ThreadPoolExecutor.AbortPolicy();
	/**
	 * 队列满并线程满时丢弃新任务，不抛出异常,不能执行的任务将被删除 
	 */
	//private static RejectedExecutionHandler handler=new ThreadPoolExecutor.DiscardPolicy();
	/**
	 * 删除队列头部任务
	 */
	//private static RejectedExecutionHandler handler=new ThreadPoolExecutor.DiscardOldestPolicy();
	
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
