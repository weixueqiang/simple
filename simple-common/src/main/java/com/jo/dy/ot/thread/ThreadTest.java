package com.jo.dy.ot.thread;

public class ThreadTest {

	public static void main(String[] args) {
		ThreadPool instance = ThreadPool.getInstance();
		for(int i=0;i<10;i++) {
			instance.excute(new TestRun(i));
		}
		instance.close();
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
			Thread.sleep(3000);
			System.out.println(Thread.currentThread().getName()+" :"+this.index);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			   e.printStackTrace();
		}
	}
}