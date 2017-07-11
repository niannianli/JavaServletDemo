package com.registration.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User { // bean or pojo
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	
	// one user has many addresses
	@OneToMany(mappedBy="user")
	private List<Address> addresses;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

// SessionFactory object: provide session object

// Sessions: every session will call sessionFactory

// sessionFactory will provide you the session object

//insert: sessionObject: db connection
//select
//delete

// singleton session Facotry: 
// same sessionFactory object

// now we can retrieve everything in session-factory

// SessionFacotry in an Interface