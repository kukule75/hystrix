package hystrix.command;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CommandSemaphoreTest {

	@Test
	public void test() throws Exception{

		ExecutorService service = Executors.newFixedThreadPool(9);

		IntStream.range(0,9).forEach(i -> service.submit(
				() -> System.out.println(new CommandSemaphore("HLX" + i).execute())
		));

		System.in.read();
	}
}