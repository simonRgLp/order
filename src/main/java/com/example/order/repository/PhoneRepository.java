package com.example.order.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.dto.Phone;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PhoneRepository {

	@Autowired
	private WebClient wc;
	
	public Flux<Phone> getPhones() {
		return wc.get().uri("/v1/phone").retrieve().bodyToFlux(Phone.class);
	}
	
	public Mono<Phone> getPhone(Long id){
		return wc.get().uri("/v1/phone/"+ id).retrieve().bodyToMono(Phone.class);
	}
	
	
}
