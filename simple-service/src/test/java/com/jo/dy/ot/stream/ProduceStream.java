package com.jo.dy.ot.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class ProduceStream {

	@Test
	public void produce() {
		Stream<String> of = Stream.of("a", "b");
		String[] arr = { "c", "d" };
		Stream<String> stream = Arrays.stream(arr);
		List<String> asList = Arrays.asList(arr);
		Stream<String> stream2 = asList.stream();
		of.forEach(System.out::println);
		stream.forEach(s -> System.out.println(s));
		stream2.forEach(s -> System.out.println(s));
	}

	@Test
	public void intStream() {
		IntStream.of(new int[] { 1, 2, 3 }).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
	}

	@Test
	public void streamToOther() {
		// 1. Array
		Stream<String> stream = Stream.of("a", "b");
		String[] strArray1 = stream.toArray(String[]::new);
		for (int i = 0; i < strArray1.length; i++) {
			System.out.println(strArray1[i]);
		}
		// 2. Collection
		stream = Stream.of("a", "b");
		List<String> list1 = stream.collect(Collectors.toList());
		System.out.println(list1);
		stream = Stream.of("a", "b");
		List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
		stream = Stream.of("a", "b");
		System.out.println(list2);
		Set set1 = stream.collect(Collectors.toSet());
		stream = Stream.of("a", "b");
		System.out.println(set1);
		Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
		System.out.println(stack1);
		// 3. String
		stream = Stream.of("a", "b");
		String str = stream.collect(Collectors.joining(",")).toString();
		System.out.println(str);
	}

	@Test
	public void toUp() {
		Stream<String> of = Stream.of("faf", "fdsf");
		Stream<String> map = of.map(String::toUpperCase);
		map.forEach(System.out::println);
	}

	/**
	 * 可扁平化数据
	 * 
	 * @date 2018年10月30日 上午11:32:24
	 * @author weixueqiang
	 */
	@Test
	public void flapMap() {
		Stream<List<Integer>> inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
		inputStream.forEach(System.out::println);
		inputStream = Stream.of(Arrays.asList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6));
		Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());
		outputStream.forEach(System.out::println);
	}

	@Test
	public void peek() {
		Stream.of("one", "two", "three", "four").filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e)).map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
	}

	@Test
	public void asyn() {
		for (int i = 0; i < 100; i++) {
			System.out.println(tt());
		}
	}

	public List<String> tt() {
		List<String> asList = Arrays.asList("abc", "as");
		asList = asList.stream().map(t -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return t.toUpperCase();
		}).collect(Collectors.toList());
		return asList;
	}

	@Test
	public void reallyAsyn() {
		Stream<String> of = Stream.of("a", "b", "c");
		of.peek(t -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(t.toUpperCase());
		}).forEach(System.out::println);

	}

	/**
	 * 生成等差数列
	 * 
	 * @date 2018年10月30日 下午2:25:44
	 * @author weixueqiang
	 */
	@Test
	public void dengchai() {
		Stream<Integer> iterate = Stream.iterate(0, f -> f + 3).limit(10);
		iterate.forEach(System.out::println);
	}

	/**
	 * 
	 * @date 2018年10月30日 下午6:02:27
	 * @author weixueqiang
	 */
	@Test
	public void collectionEmpty() {
		List<Person> list = new ArrayList<>();
		list.add(new Person(12, "kk", 13));
		list.add(new Person(12, "kk_", 13));
		List<String> collect = Optional.ofNullable(list).orElseGet(ArrayList::new).stream().map(Person::getName)
				.collect(Collectors.toList());
		System.out.println(collect);
	}

	@Test
	public void create() {
		Person2 value = new Person2();
		Optional.ofNullable(value).orElse(new Person2());
		System.out.println("----------------");
		Optional.ofNullable(value).orElseGet(() -> new Person2());
		value = null;
		System.out.println("----------------");
		Optional.ofNullable(value).orElseGet(() -> new Person2());

	}

}

class Person2 implements Supplier<Person2> {

	public Person2() {
		System.out.println("create new Person");
	}

	@Override
	public Person2 get() {
		return new Person2();
	}

}
