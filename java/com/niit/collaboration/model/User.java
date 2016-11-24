package com.niit.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table(name="C_USER")
@Component
public class User extends BaseDomain {

	@Id
	private String id;

	@NotNull
	private String name;
	@NotNull
	private String password;
	@NotNull
	private String email;
	@NotNull
	private String address;
	@NotNull
	private String mobile;
	private String role;
	private String isOnLine;
	

	public String getIsOnLine() {
		return isOnLine;
	}

	public void setIsOnLine(String isOnLine) {
		this.isOnLine = isOnLine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
