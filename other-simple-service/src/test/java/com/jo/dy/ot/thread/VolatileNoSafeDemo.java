package com.jo.dy.ot.thread;

import java.util.ArrayList;
import java.util.List;

class num extends Thread {
	public volatile static int count;

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			count++;
		}
		System.out.println(getName() + ":" + count);
	}

}

/**
 * volatile只能保证多线程的有序性和可见性,不能保证原子性,所有其不能保证线程安全<br>
 * 一下demo,在多次运行时会出现最终结果不为10000的结果---即线程不安全<br>
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月27日 下午4:07:09
 */
public class VolatileNoSafeDemo {

	public static void main(String[] args) {
		List<num> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(new num());
		}
		list.forEach(Thread::start);

	}

}
