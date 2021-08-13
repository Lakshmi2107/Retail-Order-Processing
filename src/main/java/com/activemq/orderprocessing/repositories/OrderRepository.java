package com.activemq.orderprocessing.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.activemq.orderprocessing.models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

}
