package com.jo.dy.ot.thread;

/**
 * 在本线程中等待join的线程执行完,本线程再执行,能保存线程的有序性执行<br>
 * join须在线程内部使用
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月27日 下午4:47:35
 */
public class JoinDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 30; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("我是t1...");
			}
		});
		t1.start();
		Thread t2 = new Thread(() -> {
			try {
				t1.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < 30; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("我是t2...");
			}
		});
		t2.start();
		Thread t3 = new Thread(() -> {
			try {
				t2.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for (int i = 0; i < 30; i++) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("我是t3...");
			}
		});
		t3.start();

	}

}
