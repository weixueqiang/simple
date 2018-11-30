package com.jo.dy.ot.thread;

public class DemonDemo {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> {

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("子线程结束...");
		});
		t.setDaemon(true);// 设置为守护线程,当主线程结束,子线程也结束
		t.start();
		Thread.sleep(200);
		System.out.println("主线程结束....");
	}

}
