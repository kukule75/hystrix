package com.nonobank.command;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.junit.Test;

import java.util.concurrent.Future;

/**
 * @author YuXiaodan
 * @ClassName CommandThreadPoolKeysTest
 * @Description key测试
 * @date 2018年04月20日 2018/4/20
 */
public class CommandThreadPoolKeysTest {

	@Test
	public void testThreadPool() throws Exception {

		HystrixRequestContext context = HystrixRequestContext.initializeContext();

		try {
			new CommandThreadPoolKeys("groupKey1", "commandKey1", "threadKey1", "a").queue();
			new CommandThreadPoolKeys("groupKey1", "commandKey1", "threadKey1", "b").queue();
			new CommandThreadPoolKeys("groupKey1", "commandKey1", "threadKey1", "c").queue();
//			new CommandThreadPoolKeys("groupKey1", "commandKey1", "threadKey2Hys", "d").queue();
			Future f = new CommandThreadPoolKeys("groupKey2", "commandKey2", "threadKey1", "d").queue();
			System.out.println(f.get());
		} finally {
			context.shutdown();
		}

	}
}
