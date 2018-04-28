package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;

/**
 * @author YuXiaodan
 * @ClassName CommandFallback4Exception
 * @Description
 *  以下四种情况将触发getFallback调用：
 *  1）run()方法抛出非HystrixBadRequestException异常
 *  2）run()方法调用超时
 *  3）熔断器开启拦截调用
 *  4）线程池/队列/信号量是否跑满
 *  实现getFallback()后，执行命令时遇到以上4种情况将被fallback接管，不会抛出异常或其他
 * @date 2018年04月18日 2018/4/18
 */
public class CommandFallback4Exception  extends HystrixCommand<String> {

	private final String name;


	public CommandFallback4Exception(String name) {

		super(HystrixCommandGroupKey.Factory.asKey("FallbackGroup"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		/*---------------会触发fallback的case-------------------*/
		 //1.无限循环，实际上属于超时
//    	int j = 0;
//    	while (true) {
//    		j++;
//    	}

		//2.除零异常
//    	int i = 1/0;
//		return "aaaa";

		//3.未捕获的异常
//		throw new HystrixTimeoutException();

		/*---------------不会触发fallback的case-------------------*/
		//4.被捕获的异常不会触发fallback
    	try {
    		throw new RuntimeException("this command never trigger fallback");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "aaaa";

		//5.HystrixBadRequestException异常由非法参数或非系统错误引起，不会触发fallback，也不会被计入熔断器
//		throw new HystrixBadRequestException("HystrixBadRequestException is never trigger fallback");
//		return null;
	}

	@Override
	protected String getFallback() {
		return "fallback " + name;
	}
}
