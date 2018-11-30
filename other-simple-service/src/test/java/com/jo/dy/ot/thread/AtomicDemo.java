package com.jo.dy.ot.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class num1 extends Thread {
	public static AtomicInteger count = new AtomicInteger(0);

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			count.incrementAndGet();
		}
		System.out.println(getName() + ":" + count.get());
	}

}

/**
 * 原子类,保证共享数据的原子性,多线程安全
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月27日 下午4:31:19
 */
public class AtomicDemo {

	public static void main(String[] args) {
		List<num1> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new num1());
		}
		list.forEach(Thread::start);

	}

}
