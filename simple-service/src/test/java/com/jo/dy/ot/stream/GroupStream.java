package com.jo.dy.ot.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GroupStream {

	@Test
	public void groupTest() {
		Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).limit(100)
				.collect(Collectors.groupingBy(Person::getAge));
		Iterator it = personGroups.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, List<Person>> persons = (Map.Entry) it.next();
			System.out.println("Age " + persons.getKey() + " = " + persons.getValue().size());
		}
	}

	@Test
	public void groupBy() {
		Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).limit(100)
				.collect(Collectors.partitioningBy(p -> p.getAge() < 18));
		System.out.println("Children number: " + children.get(true).size());
		System.out.println("Adult number: " + children.get(false).size());
	}

	@Test
	public void groupBy2() {
		List<Person> list = new ArrayList<>();
		list.add(new Person(12, "kk", 13));
		list.add(new Person(12, "kk_", 13));
		list.add(new Person(13, "kk_", 13));
		list.add(new Person(13, "kk_", 13));
		list.add(new Person(14, "kk_", 13));
		list.stream().distinct().forEach(s -> System.out.println(s.getAge()));
		// Map<Integer, List<Person>> collect =
		// list.stream().collect(Collectors.groupingBy(Person::getAge));

	}

}
