package com.nitesh.saga.payment.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitesh.saga.common.dto.OrderRequestDto;
import com.nitesh.saga.common.dto.PaymentRequestDto;
import com.nitesh.saga.common.dto.PaymentStatus;
import com.nitesh.saga.common.event.OrderEvent;
import com.nitesh.saga.common.event.PaymentEvent;
import com.nitesh.saga.payment.entity.UserBalance;
import com.nitesh.saga.payment.entity.UserTransaction;
import com.nitesh.saga.payment.repository.UserBalanceRepository;
import com.nitesh.saga.payment.repository.UserTransactionRepository;

@Service
public class PaymentService {

	@Autowired
	private UserBalanceRepository userBalanceRepository;
	
	@Autowired
	private UserTransactionRepository userTransactionRepository;
	
	@PostConstruct
	public void initUserBalanceDB() {
		userBalanceRepository.saveAll(Stream.of(
				new UserBalance(101, 3000),
				new UserBalance(102, 4000),
				new UserBalance(103, 2000),
				new UserBalance(104, 5000),
				new UserBalance(105, 1000)
				).collect(Collectors.toList()));
	}
	
	@Transactional
	public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
		
		OrderRequestDto orderRequestDto = orderEvent.getOrderRequestDto();
		PaymentRequestDto paymentRequestDto = new PaymentRequestDto(orderRequestDto.getOrderId(), orderRequestDto.getUserId(),orderRequestDto.getAmount());
		
		return userBalanceRepository.findById(orderRequestDto.getUserId())
		.filter(ub->ub.getPrice()>orderRequestDto.getAmount())
		.map(ub->{
			ub.setPrice(ub.getPrice()-orderRequestDto.getAmount());
			userTransactionRepository.save(new UserTransaction(orderRequestDto.getOrderId(),orderRequestDto.getUserId(),orderRequestDto.getAmount()));
			return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);
		}).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
	
	}

	@Transactional
	public PaymentEvent cancelOrderEvent(OrderEvent orderEvent) {

		userTransactionRepository.findById(orderEvent.getOrderRequestDto().getOrderId())
		.ifPresent(ut->{
			userTransactionRepository.delete(ut);
			userTransactionRepository.findById(ut.getUserId())
			.ifPresent(ub->ub.setAmount(ub.getAmount()+ut.getAmount()));
		});
		return null;
	}

}
