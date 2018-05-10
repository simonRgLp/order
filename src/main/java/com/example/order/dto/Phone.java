package com.example.order.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Phone {

	@NotNull(message="Phone id cannot be null")
	private Long id;
	
	private Long imageId;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;
	
	public Phone() {};
	
	public Phone(Long id, Long imageId, String name, String description, BigDecimal price) {
		super();
		this.id = id;
		this.imageId = imageId;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
