package com.jo.dy.ot.thread;

class Test12 extends Thread {

	public volatile boolean flag = true;

	@Override
	public void run() {
		System.out.println("...1");
		while (flag) {

		}
		System.out.println("...2");
	}
}

/**
 * 共享数据更改后通知其它线程,程序能停止---依旧线程不安全,
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月27日 下午3:51:54
 */
public class VolatileDemo {

	public static void main(String[] args) throws InterruptedException {
		Test12 test = new Test12();
		test.start();
		Thread.sleep(1000);
		test.flag = false;
		Thread.sleep(1000);
		System.out.println(test.flag);
	}
}
