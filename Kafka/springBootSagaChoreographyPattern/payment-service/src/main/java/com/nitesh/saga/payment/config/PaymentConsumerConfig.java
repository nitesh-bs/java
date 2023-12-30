package com.nitesh.saga.payment.config;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nitesh.saga.common.dto.OrderStatus;
import com.nitesh.saga.common.event.OrderEvent;
import com.nitesh.saga.common.event.PaymentEvent;
import com.nitesh.saga.payment.service.PaymentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class PaymentConsumerConfig {
	
	@Autowired
	private PaymentService paymentService;

	@Bean
	public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor(){
		
		return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
	}
	
	private Mono<PaymentEvent> processPayment(OrderEvent orderEvent){
		if(OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
			return Mono.fromSupplier(()->this.paymentService.newOrderEvent(orderEvent));
		}else {
			return Mono.fromSupplier(()->this.paymentService.cancelOrderEvent(orderEvent));
		}
	}
}
