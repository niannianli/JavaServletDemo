package com.spring.bean;

public class Clazz {
	
	// pojo class: is a bean
	// variable: property
	private Integer id;
	private String name;
	
	private User user;
			
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void callMe(){
		System.out.println("call me");
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
