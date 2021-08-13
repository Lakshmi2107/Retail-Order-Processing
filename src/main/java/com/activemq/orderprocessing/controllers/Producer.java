package com.activemq.orderprocessing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.orderprocessing.exceptions.ResourceNotFoundException;
import com.activemq.orderprocessing.models.Order;
import com.activemq.orderprocessing.repositories.OrderRepository;
import com.activemq.orderprocessing.services.OrderService;

@RestController
@RequestMapping("/order")
public class Producer {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	OrderRepository orderrepository;
	
	@Autowired
	OrderService orderservice;
	
	@PostMapping("/publishStatus")
	public ResponseEntity publishMessage(@RequestBody Order order) {
			orderservice.publishMessage(order);
			return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity findOrderById(@PathVariable String id) throws ResourceNotFoundException
	{
		return ResponseEntity.ok().body(orderservice.findOrderById(id));
	}

}
