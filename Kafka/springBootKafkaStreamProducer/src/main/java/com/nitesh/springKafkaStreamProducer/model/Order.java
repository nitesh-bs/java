package com.nitesh.springKafkaStreamProducer.model;

public class Order {

	private String item;
	private int quantity;
	private String deliveryType;
	
	public Order() {
		
	}
	
	public Order(String item, int quantity, String deliveryType) {
		this.item = item;
		this.quantity = quantity;
		this.deliveryType = deliveryType;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	
}
