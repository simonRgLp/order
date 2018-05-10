package com.example.order.service;

import com.example.order.dto.Order;

import reactor.core.publisher.Mono;

public interface OrderService {

	Mono<Order> create(Order order);
	
	public Mono<Order> findById(String id);
	
}
