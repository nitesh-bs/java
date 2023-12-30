package com.nitesh.saga.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.saga.common.dto.OrderRequestDto;
import com.nitesh.saga.common.dto.OrderStatus;
import com.nitesh.saga.order.entity.PurchaseOrder;
import com.nitesh.saga.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderStatusPublisher orderStatusPublisher;
	
	public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
		
		PurchaseOrder purchaseOrder = orderRepository.save(convertDtoToEntity(orderRequestDto));
		orderRequestDto.setOrderId(purchaseOrder.getId());
		// produce kafka event with status order_created
		
		orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
		return purchaseOrder;
	}
	
	
	public List<PurchaseOrder> getAllOrders(){
		return orderRepository.findAll();
	}
	
	private PurchaseOrder convertDtoToEntity(OrderRequestDto dto) {
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setProductId(dto.getProductId());
		purchaseOrder.setUserId(dto.getUserId());
		purchaseOrder.setPrice(dto.getAmount());
		purchaseOrder.setOrderStatus(OrderStatus.ORDER_CREATED);
		return purchaseOrder;
	}

}
