package com.nitesh.saga.order.config;

import java.util.function.Consumer;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.nitesh.saga.common.dto.OrderRequestDto;
import com.nitesh.saga.common.dto.OrderStatus;
import com.nitesh.saga.common.dto.PaymentStatus;
import com.nitesh.saga.order.entity.PurchaseOrder;
import com.nitesh.saga.order.repository.OrderRepository;
import com.nitesh.saga.order.service.OrderStatusPublisher;

@Configuration
public class OrderStatusUpdateHandler {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStatusPublisher publisher;
	
	@Transactional
	public void updateOrder(int id,Consumer<PurchaseOrder> consumer) {
		orderRepository.findById(id)
		.ifPresent(consumer.andThen(this::updateOrder));
	}
	
	private void updateOrder(PurchaseOrder purchaseOrder) {
		boolean isPaymentComplete = PaymentStatus.PAYMENT_COMPLETED.equals(purchaseOrder.getPaymentStatus());
		OrderStatus orderStatus =  (isPaymentComplete)?OrderStatus.ORDER_COMPLETED:OrderStatus.ORDER_CANCELLED;
		purchaseOrder.setOrderStatus(orderStatus);
		if(!isPaymentComplete) {
			publisher.publishOrderEvent(convertEntityToDto(purchaseOrder), orderStatus);
		}
	}

	private OrderRequestDto convertEntityToDto(PurchaseOrder purchaseOrder) {
		
		OrderRequestDto orderRequestDto = new OrderRequestDto();
		orderRequestDto.setOrderId(purchaseOrder.getId());
		orderRequestDto.setUserId(purchaseOrder.getUserId());
		orderRequestDto.setProductId(purchaseOrder.getProductId());
		orderRequestDto.setAmount(purchaseOrder.getPrice());
		return orderRequestDto;
	}
}
