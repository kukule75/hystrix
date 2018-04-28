package com.nonobank.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;

/**
 * @author YuXiaodan
 * @ClassName CommandUsingRequestCacheInvalidation
 * @Description
 * @date 2018年04月17日 2018/4/17
 */
public class CommandUsingRequestCacheInvalidation {

	/* represents a remote data store */
	private static volatile String prefixStoredOnRemoteDataStore = "ValueBeforeSet_";

	public static class GetterCommand extends HystrixCommand<String> {

		private static final HystrixCommandKey GETTER_KEY = HystrixCommandKey.Factory.asKey("GetterCommand");
		private final int id;

		public GetterCommand(int id) {
			super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet"))
					.andCommandKey(GETTER_KEY));
			this.id = id;
		}

		@Override
		protected String run() {
			return prefixStoredOnRemoteDataStore + id;
		}

		@Override
		protected String getCacheKey() {
			return String.valueOf(id);
		}

		/**
		 * Allow the cache to be flushed for this object.
		 *
		 * @param id
		 *            argument that would normally be passed to the command
		 */
		public static void flushCache(int id) {
			HystrixRequestCache.getInstance(GETTER_KEY,
					HystrixConcurrencyStrategyDefault.getInstance()).clear(String.valueOf(id));
		}

	}

	public static class SetterCommand extends HystrixCommand<Void> {

		private final int id;
		private final String prefix;

		public SetterCommand(int id, String prefix) {
			super(HystrixCommandGroupKey.Factory.asKey("GetSetGet"));
			this.id = id;
			this.prefix = prefix;
		}

		@Override
		protected Void run() {
			// persist the value against the datastore
			prefixStoredOnRemoteDataStore = prefix;
			// flush the cache
			GetterCommand.flushCache(id);
			// no return value
			return null;
		}
	}
}
