package com.activemq.orderprocessing.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.activemq.orderprocessing.models.Order;
import com.activemq.orderprocessing.repositories.OrderRepository;

@Service
public class ConsumeOrderService {

		@Autowired
		private JmsTemplate jmsTemplate;
		
		private static final String queueName="order-processing-queue";

		@Autowired
		OrderRepository orderrepository;
		
		@JmsListener(destination=queueName)
		public void consumeMessage(Order order)
		{
			if(order.getOrderStatus().equals("PLACED"))
			{
				order.setOrderStatus("PROCESSED");
				orderrepository.save(order);
				System.out.println("Order message received");
			}
		}
		
	}
