package com.activemq.orderprocessing.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="order")
public class Order{
	@Id
	private String id;
	
	private String OrderName;
	
	private String OrderStatus;
	
	@Override
	public String toString() {
		return "Order [id=" + this.id + ", OrderName=" + this.OrderName + ", OrderStatus=" + this.OrderStatus + "]";
	}
	public Order(@JsonProperty("id") String id, @JsonProperty("OrderName") String OrderName, @JsonProperty("OrderStatus") String OrderStatus) {
		this.id = id;
		this.OrderName = OrderName;
		this.OrderStatus = OrderStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderName() {
		return OrderName;
	}
	public void setOrderName(String orderName) {
		OrderName = orderName;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}
	
	
}
