package hystrix.command;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandWithFallbackViaNetworkTest {

	@Test
	public void test() {


		assertEquals(null, new CommandWithFallbackViaNetwork(100).execute());
	}
}