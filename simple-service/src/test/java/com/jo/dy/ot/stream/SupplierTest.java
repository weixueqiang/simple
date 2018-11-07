package com.jo.dy.ot.stream;

import java.util.function.Supplier;

import org.junit.Test;

public class SupplierTest {

	int index = 1;

	public SupplierTest() {
		System.out.println("create new Object-->>" + index++);
	}

	@Test
	public void test() {
		Supplier<SupplierTest> supplier = SupplierTest::new;
		System.out.println("-----");
		supplier.get();
		supplier.get();

	}

	public static void main(String[] args) {
		Supplier<SupplierTest> supplier = SupplierTest::new;
		System.out.println("-----");
		supplier.get();
		supplier.get();
	}

}
