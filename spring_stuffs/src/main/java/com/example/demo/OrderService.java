package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    CacheManager cacheManager;

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

    @Cacheable(
            cacheNames = "findAllCacheOrders",
            unless = "#result == null"
    )
    public List<Order> getOrders() {
        List<Order> result = new ArrayList<Order>();
        orderDAO.findAll().forEach(result::add);
        if(result.isEmpty()) throw new OrderListEmptyException("No Data Found");
        log.info("Fetch : getting all orders findAllOrders");
        return result;
    }

    @CacheEvict(cacheNames = "findAllCacheOrders", allEntries = true)
    public void insertOrder(Order order) {
        log.info("Insert : flushing cache with name findAllOrders");
        orderDAO.save(order);
    }

    @Caching(put = {
            @CachePut(value = "findAllCacheOrders"),
            @CachePut(value = "findByIdCache", key = "#result.name")
    })
    @CacheEvict(cacheNames = "findAllCacheOrders", allEntries = true)
    public Order updateOrder(Order order) {
        log.info("Update : updating cache with name findAllOrders & findByIdOrder");
        return orderDAO.save(order);
    }

    @Cacheable(
            cacheNames = "findByIdCache",
            key = "#name",
            unless = "#result == null"
    )
    public Order findOrder(String name) {
        log.info("Retrieve : adding the retrieved data to the cache");
        return orderDAO.findByName(name);
    }

    public void flushCache() {
        for (String cacheName : cacheManager.getCacheNames()) {
            cacheManager.getCache(cacheName).clear();
            log.info("Flushing Cache with name : "+ cacheName);
        }
    }

}
