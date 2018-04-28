package hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandUsingRequestCacheTest {

	@Test
	public void testWithoutCacheHits() {

		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			assertTrue(new CommandUsingRequestCache(2).execute());
			assertFalse(new CommandUsingRequestCache(1).execute());
			assertTrue(new CommandUsingRequestCache(0).execute());
			assertTrue(new CommandUsingRequestCache(58672).execute());
		} finally {
			context.shutdown();
		}
	}

	@Test
	public void testWithCacheHits() {

		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
			CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);

			assertTrue(command2a.execute());

			assertFalse(command2a.isResponseFromCache());

			assertTrue(command2b.execute());

			assertTrue(command2b.isResponseFromCache());
		} finally {
			context.shutdown();
		}

		context = HystrixRequestContext.initializeContext();
		try {
			CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
			assertTrue(command3b.execute());

			assertFalse(command3b.isResponseFromCache());
		} finally {
			context.shutdown();
		}
	}

}