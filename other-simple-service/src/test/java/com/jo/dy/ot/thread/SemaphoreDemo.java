package com.jo.dy.ot.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);
		for (int i = 0; i < 10; i++) {
			new Toilet(semaphore, i).start();
		}
	}
}

class Toilet extends Thread {
	Semaphore semaphore;
	int index;

	public Toilet(Semaphore semaphore, int index) {
		this.semaphore = semaphore;
		this.index = index;
	}

	@Override
	public void run() {
		try {
			System.out.println("go......." + index);
			semaphore.acquire();
			Thread.sleep(2000);
			System.out.println("ok......." + index);
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
