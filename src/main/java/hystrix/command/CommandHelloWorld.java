package hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * @author YuXiaodan
 * @ClassName CommandHelloWorld
 * @Description
 * @date 2018年04月12日 2018/4/12
 */
public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("groupKey"),
				HystrixThreadPoolKey.Factory.asKey("threadPoolKey"));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {

		return "hello " + name + "!";
	}
}
