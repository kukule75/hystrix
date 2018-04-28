package hystrix;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author YuXiaodan
 * @ClassName ConfigBean
 * @Description 配置对象
 * @date 2018年03月05日 2018/3/5
 */
@ConfigurationProperties
public class ConfigBean {

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
