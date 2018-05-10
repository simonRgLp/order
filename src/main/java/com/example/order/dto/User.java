package com.example.order.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

	@NotNull(message="User name cannot be null")
	@NotEmpty(message="User name cannot be empty")
	private String name;
	
	@NotNull(message="User surname cannot be null")
	@NotEmpty(message="User surname cannot be empty")
	private String surname;
	
	@NotNull(message="User mail cannot be null")
	@NotEmpty(message="User mail cannot be empty")
	private String mail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
