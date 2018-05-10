package com.example.order.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.order.dto.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String>{

}
