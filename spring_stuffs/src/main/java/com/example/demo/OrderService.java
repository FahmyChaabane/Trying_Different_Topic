package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    @PostConstruct
    public void addOrders() {
        Order order1 = new Order();
        order1.setName("IBM");
        order1.setCategory("Bond");
        order1.setAmount(1800);
        Order order2 = new Order();
        order2.setName("DeutschBank");
        order2.setCategory("Equity");
        order2.setAmount(2350);

        List<Order> orders = Arrays.asList(order1, order2);
        orderDAO.saveAll(orders);
    }

    public double getPrice(String name) {
        double amount;
        try {
            Order order = orderDAO.findByName(name);
            amount = order.getAmount();
        } catch (Exception e) {
            throw new OrderNameNotFoundException("Order Name Not Found");
        }
        return amount;
    }

    public List<Order> getOrders() {
        List<Order> result = new ArrayList<Order>();
        orderDAO.findAll().forEach(result::add);
        if(result.isEmpty()) throw new OrderListEmptyException("No Data Found");
        return result;
    }
}
