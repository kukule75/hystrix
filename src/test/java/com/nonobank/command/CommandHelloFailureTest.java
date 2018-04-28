package com.nonobank.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandHelloFailureTest {

	@Test
	public void testSynchronous() {

		assertEquals("Hello Failure World!", new CommandHelloFailure("World").execute());
		assertEquals("Hello Failure Bob!", new CommandHelloFailure("Bob").execute());
	}

}