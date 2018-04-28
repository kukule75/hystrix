package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author YuXiaodan
 * @ClassName CommandHelloFailure
 * @Description
 * @date 2018年04月16日 2018/4/16
 */
public class CommandHelloFailure extends HystrixCommand<String> {

	private final String name;

	public CommandHelloFailure(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		throw new RuntimeException("this command always fails");
	}

	@Override
	protected String getFallback() {
		return "Hello Failure " + name + "!";
	}
}
