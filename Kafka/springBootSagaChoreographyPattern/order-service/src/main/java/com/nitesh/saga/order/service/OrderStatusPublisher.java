package com.nitesh.saga.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.saga.common.dto.OrderRequestDto;
import com.nitesh.saga.common.dto.OrderStatus;
import com.nitesh.saga.common.event.OrderEvent;

import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

	@Autowired
	private Sinks.Many<OrderEvent> orderSlinks;
	
	public void publishOrderEvent(OrderRequestDto orderRequestDto,OrderStatus orderStatus) {
		OrderEvent orderEvent = new OrderEvent(orderRequestDto, orderStatus);
		orderSlinks.tryEmitNext(orderEvent);
	}
}
