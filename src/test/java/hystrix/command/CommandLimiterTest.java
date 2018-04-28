package hystrix.command;

import org.junit.Test;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class CommandLimiterTest {

	@Test
	public void testSynchronous() throws Exception{

		IntStream.range(0, 10).forEach(i -> {
			Future<String> future = new CommandLimiter("Hlx"+i).queue();
			if (i > 2) {
				try {
					System.out.println(future.get());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}