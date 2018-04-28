package com.nonobank.command;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class CommandHelloWorldTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommandHelloWorldTest.class);

	@Test
	public void a() {

		String s = new CommandHelloWorld("Bob").execute();

		LOGGER.info("s is " + s);
	}

	@Test
	public void testSynchronous() {

		assertEquals("hello Bob!", new CommandHelloWorld("Bob").execute());
		assertEquals("hello World!", new CommandHelloWorld("World").execute());

	}

	@Test
	public void testAsynchronous1() throws Exception{

		assertEquals("hello World!", new CommandHelloWorld("World").queue().get());
		assertEquals("hello Bob!", new CommandHelloWorld("Bob").queue().get());
	}

	@Test
	public void testAsynchronous2() throws Exception{

		Future<String> fWorld = new CommandHelloWorld("World").queue();
		Future<String> fBob = new CommandHelloWorld("Bob").queue();

		assertEquals("hello World!", fWorld.get());
		assertEquals("hello Bob!", fBob.get());
	}

	@Test
	public void testObservable() throws Exception{

		Observable<String> fWorld = new CommandHelloWorld("World").observe();
		Observable<String> fBob = new CommandHelloWorld("Bob").observe();

		assertEquals("hello World!", fWorld.toBlocking().single());
		assertEquals("hello Bob!", fBob.toBlocking().single());

		fWorld.subscribe((v) -> {
			System.out.println("onNext: " + v);
		});

		fBob.subscribe((v) -> {
			System.out.println("onNext: " + v);
		}, exception -> {
			exception.printStackTrace();
		});
	}
}