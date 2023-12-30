package com.nitesh.saga.payment.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserBalance {

	@Id
	private Integer userId;
	private Integer price;

	public UserBalance() {
	}

	public UserBalance(Integer userId, Integer price) {
		this.userId = userId;
		this.price = price;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "UserBalance [userId=" + userId + ", price=" + price + "]";
	}

}
