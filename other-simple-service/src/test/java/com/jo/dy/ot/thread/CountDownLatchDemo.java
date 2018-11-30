package com.jo.dy.ot.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 当计数器为0的时候,激活所有等待的线程
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月28日 上午10:20:27
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch down = new CountDownLatch(2);// 计数器
		new Thread(() -> {
			System.out.println("1线程开始...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("1线程结束...");
			down.countDown();
		}).start();
		;
		new Thread(() -> {
			System.out.println("2线程开始...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("2线程结束...");
			down.countDown();
		}).start();
		;
		down.await();// 当计数器为零时,激活
		System.out.println("主线程结束...");

	}

}
