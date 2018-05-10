package com.example.order;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.example.order.dto.Order;
import com.example.order.dto.Phone;
import com.example.order.dto.User;
import com.example.order.service.OrderService;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest
public class OrderApplicationTests {

	@Autowired
    private WebTestClient webClient;
	
	@MockBean
	private OrderService orderService;
	
	
	@Test
    public void getOrder() throws Exception {
    	
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		order.setId("1");
		
		when(orderService.findById(Mockito.anyString())).thenReturn(Mono.just(order));
		
        webClient.get().uri("/v1/order/{id}", 2).accept(MediaType.APPLICATION_JSON)
        		.exchange()
		        .expectStatus().isOk()
		        .expectBody().json("{\n" + 
		        		"  \"id\": \"1\",\n" + 
		        		"  \"user\": {\n" + 
		        		"    \"name\": \"Name\",\n" + 
		        		"    \"surname\": \"Surname\",\n" + 
		        		"    \"mail\": \"mail\"\n" + 
		        		"  },\n" + 
		        		"  \"phoneList\": [\n" + 
		        		"    {\n" + 
		        		"      \"id\": 2,\n" + 
		        		"      \"imageId\": 2,\n" + 
		        		"      \"name\": \"Iphone 9\",\n" + 
		        		"      \"description\": \"Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM\",\n" + 
		        		"      \"price\": 950\n" + 
		        		"    },\n" + 
		        		"    {\n" + 
		        		"      \"id\": 3,\n" + 
		        		"      \"imageId\": 3,\n" + 
		        		"      \"name\": \"Xiaomi MI 5\",\n" + 
		        		"      \"description\": \"Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM\",\n" + 
		        		"      \"price\": 500.99\n" + 
		        		"    }\n" + 
		        		"  ]\n" + 
		        		"}");
        
    }
	
	@Test
    public void createOrder_KO() throws Exception {
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
        		.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO2() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO3() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setSurname("Surname");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO4() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("");
		user.setSurname("Surname");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO5() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO6() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO7() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO8() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		user.setMail("");
		order.setUser(user);
		order.setPhoneList(phoneList);
		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}
	
	@Test
    public void createOrder_KO9() throws Exception {
		
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		user.setMail("mail");
		order.setUser(user);

		
		webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
		        .body(BodyInserters.fromObject(order))
				.exchange()
		        .expectStatus().is4xxClientError();
		
	}

	@Test
    public void createOrder() throws Exception {
    	
		Phone phone1 = new Phone(new Long(2), new Long(2), "Iphone 9", "Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM", new BigDecimal("950"));
		Phone phone2 = new Phone(new Long(3), new Long(3), "Xiaomi MI 5", "Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM", new BigDecimal("500.99"));
		List<Phone> phoneList = new ArrayList<>();
		phoneList.add(phone1);
		phoneList.add(phone2);
		Order order = new Order();
		User user = new User();
		user.setName("Name");
		user.setSurname("Surname");
		user.setMail("mail");
		order.setUser(user);
		order.setPhoneList(phoneList);
		order.setId("1");
		
		when(orderService.create(Mockito.any(Order.class))).thenReturn(Mono.just(order));
		
        webClient.post().uri("/v1/order").accept(MediaType.APPLICATION_JSON)
        		.body(BodyInserters.fromObject(order))
        		.exchange()
		        .expectStatus().isOk()
		        .expectBody().json("{\n" + 
		        		"  \"id\": \"1\",\n" + 
		        		"  \"user\": {\n" + 
		        		"    \"name\": \"Name\",\n" + 
		        		"    \"surname\": \"Surname\",\n" + 
		        		"    \"mail\": \"mail\"\n" + 
		        		"  },\n" + 
		        		"  \"phoneList\": [\n" + 
		        		"    {\n" + 
		        		"      \"id\": 2,\n" + 
		        		"      \"imageId\": 2,\n" + 
		        		"      \"name\": \"Iphone 9\",\n" + 
		        		"      \"description\": \"Movil con 6 pulgadas de pantalla, 4 GB RAM, 128 GB ROM\",\n" + 
		        		"      \"price\": 950\n" + 
		        		"    },\n" + 
		        		"    {\n" + 
		        		"      \"id\": 3,\n" + 
		        		"      \"imageId\": 3,\n" + 
		        		"      \"name\": \"Xiaomi MI 5\",\n" + 
		        		"      \"description\": \"Movil con 5 pulgadas de pantalla FullHD, 3 GB RAM, 32 GB ROM\",\n" + 
		        		"      \"price\": 500.99\n" + 
		        		"    }\n" + 
		        		"  ]\n" + 
		        		"}");
        
    }

}
