package com.nitesh.springBootwebfluxdemo.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.nitesh.springBootwebfluxdemo.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {
	
	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Customer> getAllCustomers(){
		return IntStream.rangeClosed(1, 50)
		.peek(CustomerDao::sleepExecution)	
		.peek(i->System.out.println("Processing count ::"+ i))
		.mapToObj(i->new Customer(i, "Customer ::"+i))
		.collect(Collectors.toList());
	}
	
	public Flux<Customer> getAllCustomersFlux(){
		return Flux.range(1, 50)
		.delayElements(Duration.ofSeconds(1))	
		.doOnNext(i->System.out.println("Processing count ::"+ i))
		.map(i->new Customer(i, "Customer ::"+i))
		;
	}
	
	
	public Flux<Customer> getCustomersFluxRoute(){
		return Flux.range(1, 50)
		.doOnNext(i->System.out.println("Processing count ::"+ i))
		.map(i->new Customer(i, "Customer ::"+i))
		;
	}
}
