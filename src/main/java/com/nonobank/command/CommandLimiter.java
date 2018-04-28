package com.nonobank.command;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @author YuXiaodan
 * @ClassName CommandLimiter
 * @Description 限流
 * @date 2018年04月18日 2018/4/18
 */
public class CommandLimiter extends HystrixCommand<String> {

	private final String name;

	public CommandLimiter(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ThreadPoolTestGroup"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolTest"))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)
				)
				.andThreadPoolPropertiesDefaults(
						HystrixThreadPoolProperties.Setter().withCoreSize(3)
//						.withMaximumSize(12)
//						.withAllowMaximumSizeToDivergeFromCoreSize(true)
				)
		);
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		System.out.println("===========" + name);
		TimeUnit.MILLISECONDS.sleep(2000);
		return name;
	}

	@Override
	protected String getFallback() {
		return "fallback: " + name;
	}
}
