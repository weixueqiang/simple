package com.jo.dy.ot.stream;

public class Person {

	private Integer age;
	private String name;
	private Integer key;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public Person(Integer age, String name, Integer key) {
		super();
		this.age = age;
		this.name = name;
		this.key = key;
	}

}
