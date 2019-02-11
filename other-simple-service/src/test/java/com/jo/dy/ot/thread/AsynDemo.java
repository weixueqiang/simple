package com.jo.dy.ot.thread;

public class AsynDemo {

	public static void main(String[] args) {
		System.out.println(getPerson().sex);
	}

	public static Person getPerson() {
		Person person = new Person();
		person.sex = "nan";
		new Asyn(person).start();
		return person;

	}
}

class Asyn extends Thread {

	private Person person;

	public Asyn(Person person) {
		super();
		this.person = person;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		person.sex = "nu";
		System.out.println(person.sex + "..........");
	}
}
