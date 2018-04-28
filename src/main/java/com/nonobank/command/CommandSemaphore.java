package com.nonobank.command;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;

/**
 * @author YuXiaodan
 * @ClassName CommandSemaphore
 * @Description 信号量隔离
 *  设置ExecutionIsolationSemaphoreMaxConcurrentRequests为3，意味着信号量最多允许执行run的并发数为3，超过则触发降级，即不执行run而执行getFallback
 *  设置FallbackIsolationSemaphoreMaxConcurrentRequests为1，意味着信号量最多允许执行fallback的并发数为1，超过则抛异常fallback execution rejected
 * @date 2018年04月18日 2018/4/18
 */
public class CommandSemaphore extends HystrixCommand<String> {

	private final String name;

	public CommandSemaphore(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SemaphoreTestGroup"))
						.andCommandKey(HystrixCommandKey.Factory.asKey("SemaphoreTestKey"))
						.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("SemaphoreTestThreadPoolKey"))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter()
										.withExecutionIsolationStrategy(SEMAPHORE)
										.withExecutionIsolationSemaphoreMaxConcurrentRequests(3)
										.withFallbackIsolationSemaphoreMaxConcurrentRequests(1)
						)
		);
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		return "run(): name="+name+"，线程名是" + Thread.currentThread().getName();
	}

	@Override
	protected String getFallback() {
		return "getFallback(): name="+name+"，线程名是" + Thread.currentThread().getName();
	}

}
