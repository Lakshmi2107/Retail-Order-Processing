package com.activemq.orderprocessing.services;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.activemq.orderprocessing.exceptions.ResourceNotFoundException;
import com.activemq.orderprocessing.models.Order;
import com.activemq.orderprocessing.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private ActiveMQQueue queue;

	@Autowired
	OrderRepository orderrepository;
	
	public Order publishMessage(Order order)
	{
		orderrepository.save(order);
		jmsTemplate.convertAndSend(queue, order);	
		return order;
	}
	
	public Order findOrderById(String id) throws ResourceNotFoundException
	{
		Order orderdetails = orderrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id"+id));
		return orderdetails;
		
	}

}
