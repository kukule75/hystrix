package com.nonobank.command;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @author YuXiaodan
 * @ClassName CommandThreadPoolKeys
 * @Description 各种key测试
 * @date 2018年04月20日 2018/4/20
 */
public class CommandThreadPoolKeys extends HystrixCommand<String> {

	private String name;

	public CommandThreadPoolKeys(String groupKey, String commandKey, String threadPoolKey, String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
				.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(3))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter()
								.withExecutionTimeoutInMilliseconds(5000)
				));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		System.out.println("run() .... " + name);

		TimeUnit.MILLISECONDS.sleep(2000);

		return "run() .... " + name;
	}

	@Override
	protected String getFallback() {

		return "getFallback .... " + name;
	}
}
