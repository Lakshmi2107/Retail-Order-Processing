package com.activemq.orderprocessing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.activemq.orderprocessing.models.Order;
import com.activemq.orderprocessing.services.ConsumeOrderService;

@Component
public class Consumer {
	@Autowired
	ConsumeOrderService consumeorderservice;
	
	public void receiveMessage(Order order) {
		consumeorderservice.consumeMessage(order);
}
}
