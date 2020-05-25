package com.example.demo;

import antlr.debug.ParserTokenEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderDAO extends CrudRepository<Order, UUID> {
    Order findByName(String name);
}
