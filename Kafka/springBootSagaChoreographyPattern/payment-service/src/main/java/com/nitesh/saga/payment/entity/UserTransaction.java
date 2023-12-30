package com.nitesh.saga.payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserTransaction {

	@Id
	private Integer orderId;
	private Integer userId;
	private Integer amount;
	
	public UserTransaction() {
	}
	
	public UserTransaction(Integer orderId, Integer userId, Integer amount) {
		this.orderId = orderId;
		this.userId = userId;
		this.amount = amount;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserTransaction [orderId=" + orderId + ", userId=" + userId + ", amount=" + amount + "]";
	}
	
	
	
}
