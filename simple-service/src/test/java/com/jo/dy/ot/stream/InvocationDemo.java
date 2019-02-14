package com.jo.dy.ot.stream;

import java.util.function.Function;

import org.junit.Test;

public class InvocationDemo {

	@Test
	public void invocation() {

	}

	public <T, R> void function(Function<T, R> fun, T t) {
		System.out.println(fun.apply(t));

	}

}

class Demo1 {
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Demo2 {
	Integer age;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
