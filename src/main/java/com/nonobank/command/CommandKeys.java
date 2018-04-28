package com.nonobank.command;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @author YuXiaodan
 * @ClassName CommandKeys
 * @Description commandkey测试
 * @date 2018年04月20日 2018/4/20
 */
public class CommandKeys extends HystrixCommand<String>{

	private String name;

	public CommandKeys(String groupKey, String commandKey, String threadPoolKey, String name, int coreSize) {
		super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(groupKey))
				.andCommandKey(HystrixCommandKey.Factory.asKey(commandKey))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(threadPoolKey))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(coreSize))
				.andCommandPropertiesDefaults(
						HystrixCommandProperties.Setter()
								.withMetricsRollingStatisticalWindowInMilliseconds(1000)
								.withExecutionTimeoutInMilliseconds(100)
								.withCircuitBreakerRequestVolumeThreshold(3)
								.withCircuitBreakerErrorThresholdPercentage(20)
								.withCircuitBreakerSleepWindowInMilliseconds(100000)
				));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		System.out.println("run() " + name + " ...");
		TimeUnit.MILLISECONDS.sleep(500);
		return "run() " + name + " ...";

	}

	@Override
	protected String getFallback() {

		return "getFallback .... " + name;
	}
}
