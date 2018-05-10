package com.example.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.dto.Order;
import com.example.order.dto.Phone;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.PhoneRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public Mono<Order> create(Order order){
		
		BigDecimal orderPrice = new BigDecimal("0");
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("Order info:");
		System.out.println("User data: ");
		System.out.println("Name: " + order.getUser().getName());
		System.out.println("Surname: " + order.getUser().getSurname());
		System.out.println("Mail: " + order.getUser().getMail());
		System.out.println("Get the phone info from phone API, not from the request");
		
		List<Phone> phoneOrderList = new ArrayList<>();
		
		for(Phone phone: order.getPhoneList()) {
			Phone phoneInfo = phoneRepository.getPhone(phone.getId()).block();
			
			if(phoneInfo != null) {
				
				System.out.println("Phone data: ");
				System.out.println("Id: " + phoneInfo.getId());
				System.out.println("ImageId: " + phoneInfo.getImageId());
				System.out.println("Name: " + phoneInfo.getName());
				System.out.println("Description: " + phoneInfo.getDescription());
				System.out.println("Price: " + phoneInfo.getPrice());
				
				orderPrice = orderPrice.add(phoneInfo.getPrice());
				
				Phone orderPhone = new Phone(phoneInfo.getId(), phoneInfo.getImageId(), 
						phoneInfo.getName(), phoneInfo.getDescription(), phoneInfo.getPrice());
				phoneOrderList.add(orderPhone);
			}
			
		}
		
		System.out.println("Total price of the order: " + orderPrice);
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		
		Order orderCreated = new Order();
		orderCreated.setUser(order.getUser());
		orderCreated.setPhoneList(phoneOrderList);
			
		return orderRepository.save(orderCreated);
		
	}
	
	public Mono<Order> findById(String id){
		return orderRepository.findById(id);
	}
	
}
