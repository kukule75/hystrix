package com.nonobank.controller;

import com.nonobank.ConfigBean;
import com.nonobank.MyConfigBean;
import com.nonobank.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YuXiaodan
 * @ClassName MyRestController
 * @Description rest风格
 * @date 2018年02月12日 2018/2/12
 */
@RestController
@RequestMapping(value = "/app")
public class MyRestController {

	@Value("${helloName}")
	private String helloName;

	@Autowired
	private ConfigBean configBean;

	@Autowired
	private MyConfigBean myConfigBean;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {

		return "hello " + helloName;
	}

	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public ConfigBean getConfigBean() {

		return configBean;
	}

	@RequestMapping(value = "/myConfig", method = RequestMethod.GET)
	public MyConfigBean getMyConfigBean() {

		return myConfigBean;
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public User getUser() {

		return User.newInstance("yuxiaodan", "1", 30);
	}

	@RequestMapping(value = "/getUser/{name}", method = RequestMethod.GET)
	public User getUser(@PathVariable String name) {

		return User.newInstance(name, "1", 30);
	}
}
