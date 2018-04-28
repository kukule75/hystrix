package hystrix.command;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE;

/**
 * @author YuXiaodan
 * @ClassName CommandCircuitBreaker
 * @Description 断路器
 * CircuitBreakerRequestVolumeThreshold设置为3，意味着10s内请求超过3次就触发熔断器
 * run()中无限循环使命令超时进入fallback，执行3次run后，将被熔断，进入降级，即不进入run()而直接进入fallback
 * 如果未熔断，但是threadpool被打满，仍然会降级，即不进入run()而直接进入fallback
 * @date 2018年04月18日 2018/4/18
 */
public class CommandCircuitBreaker extends HystrixCommand<String> {

	private final String name;

	public CommandCircuitBreaker(String name) {
		super(
				Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircuitBreakerGroup"))
						.andCommandKey(HystrixCommandKey.Factory.asKey("CircuitBreakKey"))
						.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircuitBreakThread"))
						.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(200))
						.andCommandPropertiesDefaults(
								HystrixCommandProperties.Setter()
								.withCircuitBreakerEnabled(true)
								.withMetricsRollingStatisticalWindowInMilliseconds(10000)
								.withCircuitBreakerRequestVolumeThreshold(3)
								.withCircuitBreakerErrorThresholdPercentage(80)
								.withExecutionTimeoutInMilliseconds(500)
								.withCircuitBreakerSleepWindowInMilliseconds(3000)
//								.withCircuitBreakerForceOpen(true)
//								.withCircuitBreakerForceClosed(true)
//								.withExecutionIsolationStrategy(SEMAPHORE)
//								.withExecutionTimeoutInMilliseconds(5000)
						)
		);
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		System.out.println("running run():" + name);
		int num = Integer.valueOf(name);
		if(num % 2 == 0 && num < 10) {
			return name;
		} else {
			TimeUnit.MILLISECONDS.sleep(800);
			return name;
		}
	}

	@Override
	protected String getFallback() {

		return "CircuitBreaker fallback: " + name;
	}
}
