package com.nonobank.command;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class CommandCircuitBreakerTest {

	/**
	 * i大于15之后，休眠500ms，是为了能看清楚断路器进入半开状态
	 * @throws IOException
	 */
	@Test
	public void testSynchronous() throws IOException {

		IntStream.range(0, 10000).forEach(i -> {
			System.out.println("===========" + new CommandCircuitBreaker(String.valueOf(i)).execute());
			if (i > 15) {
				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}