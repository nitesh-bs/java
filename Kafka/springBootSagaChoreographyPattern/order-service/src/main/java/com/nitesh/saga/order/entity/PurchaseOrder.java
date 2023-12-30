package com.nitesh.saga.order.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nitesh.saga.common.dto.OrderStatus;
import com.nitesh.saga.common.dto.PaymentStatus;

@Entity
@Table(name = "PURCHASE_ORDER_TBL")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	private Integer id;
	private Integer userId;
	private Integer productId;
	private Integer price;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;

	public PurchaseOrder() {
	}

	public PurchaseOrder(Integer id, Integer userId, Integer productId, Integer price, OrderStatus orderStatus,
			PaymentStatus paymentStatus) {
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.price = price;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", userId=" + userId + ", productId=" + productId + ", price=" + price
				+ ", orderStatus=" + orderStatus + ", paymentStatus=" + paymentStatus + "]";
	}

}
