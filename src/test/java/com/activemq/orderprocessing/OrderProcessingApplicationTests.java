package com.activemq.orderprocessing;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.activemq.orderprocessing.models.Order;
import com.activemq.orderprocessing.repositories.OrderRepository;
import com.activemq.orderprocessing.services.OrderService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = OrderProcessingApplication.class)
class OrderProcessingApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock private OrderRepository orderrepository; 
	@InjectMocks private OrderService orderservice;
	
	private List<Order> orderList;
	
	@Before
	public void setUp() {  
        MockitoAnnotations.initMocks(this);      
    }
	
	
	@Test
	void testPublishMessage()
	{
		Order mockorder = new Order("123","test2","PLACED");
		Mockito.when(orderrepository.save(Mockito.any(Order.class))).thenReturn(mockorder);
		
		Order order = orderservice.publishMessage(mockorder);
		Assert.assertEquals(order.getId(),mockorder.getId());
	}
	
	
	
	
}
