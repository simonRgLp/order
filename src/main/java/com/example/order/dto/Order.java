package com.example.order.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

public class Order {

	@Id
	private String id;
	
	@NotNull(message="User cannot be null")
	@Valid
	private User user;
	
	@NotNull(message="Phone list cannot be null")
	@Valid
	private List<Phone> phoneList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}
	
}
