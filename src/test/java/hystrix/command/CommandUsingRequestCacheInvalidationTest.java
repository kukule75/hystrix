package hystrix.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandUsingRequestCacheInvalidationTest {

	@Test
	public void getGetSetGet() {
		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			assertEquals("ValueBeforeSet_1", new CommandUsingRequestCacheInvalidation.GetterCommand(1).execute());
			CommandUsingRequestCacheInvalidation.GetterCommand commandAgainstCache = new CommandUsingRequestCacheInvalidation.GetterCommand(1);
			assertEquals("ValueBeforeSet_1", commandAgainstCache.execute());

			assertTrue(commandAgainstCache.isResponseFromCache());

			new CommandUsingRequestCacheInvalidation.SetterCommand(1, "ValueAfterSet_").execute();

			CommandUsingRequestCacheInvalidation.GetterCommand commandAfterSet = new CommandUsingRequestCacheInvalidation.GetterCommand(1);

			assertFalse(commandAfterSet.isResponseFromCache());
			assertEquals("ValueAfterSet_1", commandAfterSet.execute());
		} finally {
			context.shutdown();
		}
	}
}