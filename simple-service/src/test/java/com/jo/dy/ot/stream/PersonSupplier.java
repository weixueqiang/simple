package com.jo.dy.ot.stream;

import java.util.Random;
import java.util.function.Supplier;

public class PersonSupplier implements Supplier<Person> {
	private int index = 0;
	private Random random = new Random();

	public PersonSupplier() {
	}

	@Override
	public Person get() {
		return new Person(random.nextInt(100), "StormTestUser" + index, index++);
	}
}