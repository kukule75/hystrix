package hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author YuXiaodan
 * @ClassName CommandWithFallbackViaNetwork
 * @Description
 * @date 2018年04月17日 2018/4/17
 */
public class CommandWithFallbackViaNetwork extends HystrixCommand<String> {

	private final int id;

	protected CommandWithFallbackViaNetwork(int id) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GetValueCommand")));
		this.id = id;
	}

	@Override
	protected String run() {
		throw new RuntimeException("force failure for example");
	}

	@Override
	protected String getFallback() {
		return new FallbackViaNetwork(id).execute();
	}

	private static class FallbackViaNetwork extends HystrixCommand<String> {
		private final int id;

		public FallbackViaNetwork(int id) {
			super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceX"))
					.andCommandKey(HystrixCommandKey.Factory.asKey("GetValueFallbackCommand"))
					.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("RemoteServiceXFallback")));
			this.id = id;
		}

		@Override
		protected String run() {
			throw new RuntimeException("FallbackViaNetwork failure ...");
		}


		@Override
		protected String getFallback() {
			return null;
		}
	}
}
