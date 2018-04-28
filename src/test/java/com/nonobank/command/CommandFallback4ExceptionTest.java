package com.nonobank.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandFallback4ExceptionTest {

	@Test
	public void test() {

		assertEquals("aaaa", new CommandFallback4Exception("Hlx").execute());
	}

}