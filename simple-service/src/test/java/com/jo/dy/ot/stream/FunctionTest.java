package com.jo.dy.ot.stream;

import java.util.function.Function;

public class FunctionTest implements Function<String, String> {

	@Override
	public String apply(String t) {
		return t + "调用--";
	}

	public String tt() {
		return "";
	}

}
