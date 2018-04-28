package com.nonobank.command;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CommandKeysTest {

	/**
	 * f1/f2/f3执行之后，线程池threadKey1被占满f4/f5都直接进入fallback
	 * @throws Exception
	 */
	@Test
	public void testThreadPoolKey() throws Exception {

			Future f1 = new CommandKeys("groupKey1", "commandKey1", "threadKey1", "a", 3).queue();
			Future f2 = new CommandKeys("groupKey1", "commandKey1", "threadKey1", "b", 3).queue();
			Future f3 = new CommandKeys("groupKey1", "commandKey1", "threadKey1", "c", 3).queue();
			Future f4 = new CommandKeys("groupKey1", "commandKey1", "threadKey1", "d", 3).queue();
			Future f5 = new CommandKeys("groupKey2", "commandKey2", "threadKey1", "e", 3).queue();
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(f3.get());
			System.out.println(f4.get());
			System.out.println(f5.get());

	}

	/**
	 * 0-10执行后，commandKey1对应的断路器打开，后面的命令，虽然groupkey和threadKey都不一样，但是断路器是同一个，都走fallback
	 */
	@Test
	public void testCommandKey() {

		IntStream.range(0, 10).forEach(i -> {
			Future f = new CommandKeys("groupKey1", "commandKey1", "threadKey1", String.valueOf(i), 20).queue();
			try {
				System.out.println(f.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		System.out.println(new CommandKeys("groupKey2", "commandKey1", "threadKey2", "a", 20).execute());
	}
}