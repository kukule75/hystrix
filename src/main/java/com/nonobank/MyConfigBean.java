package com.nonobank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YuXiaodan
 * @ClassName MyConfigBean
 * @Description
 * @date 2018年03月05日 2018/3/5
 */
@Configuration
@ConfigurationProperties()
@PropertySource("classpath:MyApplication.properties")
public class MyConfigBean {

	private String type;

	private Integer number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
