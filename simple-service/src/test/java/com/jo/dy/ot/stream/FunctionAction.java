package com.jo.dy.ot.stream;

import java.util.function.Function;

import org.junit.Test;

public class FunctionAction {

	@Test
	public void test() {
		Function<String, String> fun = s -> s + 3;
		Function<String, String> fun3 = String::toString;
		String apply = fun.apply("kangkang");
		System.out.println(fun3.apply("zhangs"));
		System.out.println(apply);
	}

}
