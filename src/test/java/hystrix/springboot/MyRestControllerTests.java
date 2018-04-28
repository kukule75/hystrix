package hystrix.springboot;

import hystrix.controller.MyRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author YuXiaodan
 * @ClassName MyRestControllerTests
 * @Description 测试
 * @date 2018年03月01日 2018/3/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MyRestControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new MyRestController()).build();
	}

	@Test
	public void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/app/getUser").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
	}
}
