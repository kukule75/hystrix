package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author YuXiaodan
 * @ClassName CommandThatFailsFast
 * @Description
 * @date 2018年04月17日 2018/4/17
 */
public class CommandThatFailsFast extends HystrixCommand<String> {

	private final boolean throwException;

	public CommandThatFailsFast(boolean throwException) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.throwException = throwException;
	}

	@Override
	protected String run() {
		if (throwException) {
			throw new RuntimeException("failure from CommandThatFailsFast");
		} else {
			return "success";
		}
	}
}
