package com.jo.dy.ot.thread;

import java.util.Scanner;

public class ThreadTest {

	public static void main(String[] args) {
		System.out.println(canFinishMethodTest());
		System.out.println("main方法结束---");
	}
	
	public static String canFinishMethodTest() {
		ThreadPool instance = ThreadPool.getInstance();
		for(int i=0;i<10;i++) {
			instance.excute(new TestRun(i));
		}
		return "ok";
	}
	
}

class TestRun implements Runnable{
	
	private int index;
	
	public TestRun(int index) {
		super();
		this.index = index;
	}

	public void run() {
		try {
			System.out.println(index);
			String name = Thread.currentThread().getName();
//			if(name.equals("main")) {
//				Scanner scan = new Scanner(System.in);
//				System.out.println("策略阻塞:"+index);
//				scan.nextLine();
//			}
			Thread.sleep(2000);
			System.out.println(name+" :"+this.index);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		}
	}
}