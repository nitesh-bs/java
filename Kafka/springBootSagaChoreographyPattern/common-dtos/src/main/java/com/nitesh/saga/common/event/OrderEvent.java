package com.nitesh.saga.common.event;

import java.util.Date;
import java.util.UUID;

import com.nitesh.saga.common.dto.OrderRequestDto;
import com.nitesh.saga.common.dto.OrderStatus;

public class OrderEvent implements Event {

	private UUID eventId = UUID.randomUUID();
	private Date date = new Date();
	private OrderRequestDto orderRequestDto;
	private OrderStatus orderStatus;

	@Override
	public UUID getEventId() {
		// TODO Auto-generated method stub
		return eventId;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	public OrderEvent() {
	}

	public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
		this.orderRequestDto = orderRequestDto;
		this.orderStatus = orderStatus;
	}

	public OrderRequestDto getOrderRequestDto() {
		return orderRequestDto;
	}

	public void setOrderRequestDto(OrderRequestDto orderRequestDto) {
		this.orderRequestDto = orderRequestDto;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderEvent [eventId=" + eventId + ", date=" + date + ", orderRequestDto=" + orderRequestDto
				+ ", orderStatus=" + orderStatus + "]";
	}
	
	

}
