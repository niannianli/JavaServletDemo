package com.spring.bean;

//not good
//public class AddressUser extends User{

public class AddressUser{

	private String address;	
	
	// no user of User, just extends User, then we can access phoneNo
	// but why we did not extends User?
	
	// after get the address
	// check to which user
	// then check user's phoneNO
	// no changes affected
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
