package com.nitesh.saga.common.event;

import java.util.Date;
import java.util.UUID;

import com.nitesh.saga.common.dto.PaymentRequestDto;
import com.nitesh.saga.common.dto.PaymentStatus;

public class PaymentEvent implements Event {
	private UUID eventId = UUID.randomUUID();
	private Date date = new Date();
	private PaymentRequestDto paymentRequestDto;
	private PaymentStatus paymentStatus;

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

	public PaymentEvent() {
	}

	public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
		this.paymentRequestDto = paymentRequestDto;
		this.paymentStatus = paymentStatus;
	}

	public PaymentRequestDto getPaymentRequestDto() {
		return paymentRequestDto;
	}

	public void setPaymentRequestDto(PaymentRequestDto paymentRequestDto) {
		this.paymentRequestDto = paymentRequestDto;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "PaymentEvent [eventId=" + eventId + ", date=" + date + ", paymentRequestDto=" + paymentRequestDto
				+ ", paymentStatus=" + paymentStatus + "]";
	}
	
	

}
