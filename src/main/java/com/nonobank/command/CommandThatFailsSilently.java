package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.Collections;

/**
 * @author YuXiaodan
 * @ClassName CommandThatFailsSilently
 * @Description
 * @date 2018年04月17日 2018/4/17
 */
public class CommandThatFailsSilently extends HystrixCommand<String> {

	private final boolean throwException;

	public CommandThatFailsSilently(boolean throwException) {
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

	@Override
	protected String getFallback() {
		return null;
//		return Collections.emptyList();
	}
}
