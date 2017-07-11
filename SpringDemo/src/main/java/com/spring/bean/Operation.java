package com.spring.bean;

public class Operation {
	
	private String sign;
	
	public int calculate(Integer i, Integer j){
		return i + j;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
