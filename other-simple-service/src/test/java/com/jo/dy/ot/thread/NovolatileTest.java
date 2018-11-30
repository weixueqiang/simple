package com.jo.dy.ot.thread;

class Test11 extends Thread {

	public boolean flag = true;

	@Override
	public void run() {
		System.out.println("...1");
		while (flag) {

		}
		System.out.println("...2");
	}
}

/**
 * 没有 使用volatile时,两个线程之间数据不可见,会出现程序不停止的情况,
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月27日 下午3:47:47
 */
public class NovolatileTest {

	public static void main(String[] args) throws InterruptedException {
		Test11 test = new Test11();
		test.start();
		Thread.sleep(1000);
		test.flag = false;
		Thread.sleep(1000);
		System.out.println(test.flag);
	}
}
