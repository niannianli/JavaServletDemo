package com.spring.bean;

public class User {
	
	private String phoneNo;
	// changes

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	// create once only when loading class
	static Operation op = new Operation();
	
	// whenever user call this method,
	// tons of operation object will be created
	public void call(Integer i, Integer j){
		
		System.out.println(Clazz3.Operation().calculate(i, j));
	}

}
