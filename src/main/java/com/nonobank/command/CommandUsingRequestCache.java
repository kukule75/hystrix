package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author YuXiaodan
 * @ClassName CommandUsingRequestCache
 * @Description
 * @date 2018年04月16日 2018/4/16
 */
public class CommandUsingRequestCache extends HystrixCommand<Boolean> {

	private final int value;

	protected CommandUsingRequestCache(int value) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.value = value;
	}

	@Override
	protected Boolean run() {
		return value == 0 || value % 2 == 0;
	}

	@Override
	protected String getCacheKey() {
		return String.valueOf(value);
	}
}
