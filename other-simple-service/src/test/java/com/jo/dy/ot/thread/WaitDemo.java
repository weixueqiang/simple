package com.jo.dy.ot.thread;

class Person {
	String username;
	String sex;
	boolean flag = false;
}

class Produce extends Thread {

	Person person;
	int index;

	public Produce(Person person) {
		this.person = person;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (person) {
				if (person.flag) {
					try {
						person.wait();
						System.out.println("wait..over");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				if (index == 0) {
					person.username = "kangkang";
					person.sex = "男";
				} else {
					person.username = "marry";
					person.sex = "女";
				}
				index = (index + 1) % 2;
				person.flag = true;
				person.notify();
			}
		}
	}
}

class Consumer extends Thread {
	Person person;
	int index;

	public Consumer(Person person) {
		this.person = person;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (person) {

				if (!person.flag) {
					try {
						person.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println(person.username + ":" + person.sex);
				person.flag = false;
				person.notify();
			}
		}
	}

}

/**
 * 须在同步中
 * 
 * @author weixueqiang
 * @version 1.0.0
 * @date 2018年11月29日 下午2:14:56
 */
public class WaitDemo {

	public static void main(String[] args) throws InterruptedException {
		Person person = new Person();
		Produce produce = new Produce(person);
		Consumer consumer = new Consumer(person);
		produce.start();
		consumer.start();
	}
}
