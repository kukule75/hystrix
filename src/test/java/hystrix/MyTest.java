package hystrix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YuXiaodan
 * @ClassName MyTest
 * @Description
 * @date 2018年04月20日 2018/4/20
 */
public class MyTest {

	@Test
	public void a() {

		Map<String, String> map = new HashMap<>();
		System.out.println(map.put("a", "a"));
		System.out.println(map.put("a", "b"));
	}
}
