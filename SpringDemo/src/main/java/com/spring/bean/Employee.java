package com.spring.bean;

public class Employee {

	private Integer id;
	private String empName;
	private Address address;

	// now use constructor in bean
	// instead of : name value pairs as before
	/**Employee(Integer id, String empName, Address address) {
		this.id = id;
		this.empName = empName;
		this.address = address;
	}
*/
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
