package com.nonobank.domain;

/**
 * @author YuXiaodan
 * @ClassName User
 * @Description 用户
 * @date 2018年02月13日 2018/2/13
 */
public class User {

	private String name;

	private String sex;

	private Integer age;

	private User(){}

	public static User newInstance(String name, String sex, Integer age) {

		User user = new User();
		user.setName(name);
		user.setSex(sex);
		user.setAge(age);

		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
