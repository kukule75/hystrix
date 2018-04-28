package hystrix;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author YuXiaodan
 * @ClassName MyWebMvcConfigurerAdapter
 * @Description
 * @date 2018年03月05日 2018/3/5
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{

	/**
	 * 配置静态访问资源
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 以前要访问一个页面需要先创建个Controller控制类，再写方法跳转到页面
	 * 在这里配置后就不需要那么麻烦了，直接访问http://localhost:8080/toLogin就跳转到login.jsp页面了
	 * @param registry
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/toLogin").setViewName("login");
		super.addViewControllers(registry);
	}
}
