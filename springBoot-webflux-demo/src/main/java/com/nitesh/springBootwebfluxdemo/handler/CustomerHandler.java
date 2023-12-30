package com.nitesh.springBootwebfluxdemo.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nitesh.springBootwebfluxdemo.dao.CustomerDao;
import com.nitesh.springBootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao dao;

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerList = dao.getCustomersFluxRoute();
		return ServerResponse.ok().body(customerList, Customer.class);
	}

	public Mono<ServerResponse> loadCustomersStream(ServerRequest request) {
		Flux<Customer> customerList = dao.getAllCustomersFlux();
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customerList, Customer.class);
	}

	public Mono<ServerResponse> findCustomersInput(ServerRequest request) {
		int customerId = Integer.valueOf(request.pathVariable("input"));
		Mono<Customer> customerList = dao.getCustomersFluxRoute().filter(c -> c.getId() == customerId).next();
		return ServerResponse.ok().body(customerList, Customer.class);
	}

	public Mono<ServerResponse> saveCustomer(ServerRequest request) {
		Mono<Customer> customerMono = request.bodyToMono(Customer.class);
		Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
		return ServerResponse.ok().body(saveResponse, String.class);
	}
}
