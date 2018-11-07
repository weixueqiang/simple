package log.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class SteamTest {

	Random random;
	List<Student> stuList;

	@Before
	public void init() {
		random = new Random();
		stuList = new ArrayList<Student>();
		for (int i = 0; i < 100; i++) {
			stuList.add(new Student("student" + i, random.nextInt(50) + 50));
		}
	}

	@Test
	public void functionTest() {
		Student student = new Student("zhangsan", 25);

		Function<String, Integer> i = s -> Integer.parseInt(s);
		System.out.println(i.apply("22"));
	}

	// 1列出班上超过85分的学生姓名，并按照分数降序输出用户名字
	@Test
	public void test1() {
		List<String> studentList = stuList.stream().filter(x -> x.getScore() > 85)
				.sorted(Comparator.comparing(Student::getScore).reversed()).map(Student::getName)
				.collect(Collectors.toList());
		System.out.println(studentList);
	}

	@Test
	public void test2() {
		List<Integer> collect = stuList.stream().filter(e -> e.getScore() > 90)
				.sorted(Comparator.comparing(Student::getScore).reversed()).map(Student::getScore)
				.collect(Collectors.toList());
		collect.forEach(e -> System.out.println(e));
	}

	@Test
	public void test3() {
		List<String> collect = stuList.stream().filter(e -> e.getScore() > 90).sorted((e1, e2) -> {
			return e1.getScore() - e2.getScore();
		}).map(Student::getName).collect(Collectors.toList());
		collect.forEach(e -> System.out.println(e));
	}

}

class Student {
	private String name;
	private Integer score;

	public Student(String name, Integer score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}