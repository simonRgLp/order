package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.dto.Order;
import com.example.order.service.OrderService;

import reactor.core.publisher.Mono;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.http.MediaType;


@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@RequestMapping(value = "v1/order", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Order> create(@RequestBody @Valid Order order) {
		return orderService.create(order);
	}
	
	@RequestMapping(value = "v1/order/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Mono<Order> findById(@PathVariable String id){
		return orderService.findById(id);
	}
	
}
