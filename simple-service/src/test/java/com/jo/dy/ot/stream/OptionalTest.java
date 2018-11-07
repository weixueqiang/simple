package com.jo.dy.ot.stream;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

public class OptionalTest {
	@Test
	public void findFirst() {
		String strA = " abcd ", strB = null;
		print(strA);
		print("");
		print(strB);
		System.out.println(getLength(strA));
		System.out.println(getLength(""));
		System.out.println(getLength(strB));

	}

	public static void print(String text) {
		// Java 8
		Optional.ofNullable(text).ifPresent(System.out::println);
		// Pre-Java 8
		// if (text != null) {
		// System.out.println(text);
		// }
	}

	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	};

	@Test
	public void reduce() {
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
	}

	@Test
	public void te() {
		String a = "";
		String b = null;
		Integer orElse = Optional.ofNullable(a).map(String::length).orElse(-1);
		System.out.println(orElse);
	}

}
