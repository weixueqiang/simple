package log.test;

import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;

public class FunctionTest {

	@Test
	public void test() {
		Function<String, Integer> num = Integer::parseInt;
		System.out.println(num.apply("34"));

	}

	@Test
	public void test1() {
		Stream<String> stream = Stream.generate(() -> "user").limit(20);
		stream.forEach(System.out::println);

	}
}
