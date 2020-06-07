package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "Order API", version = "1.0.0", description = "the 1993 1994 black buster VG Technology"))
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/amount/{orderName}")
    public String getOrderAmount(@PathVariable  String orderName) {
        double amount = orderService.getPrice(orderName);
        return "it's price is : " + amount;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/order/{orderName}")
    public Order getOrders(@PathVariable  String orderName) {
        return orderService.findOrder(orderName);
    }

    @PutMapping("/update_Order")
    public Order updateOrders(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @PostMapping("/insert_Order")
    public void insertOrders(@RequestBody Order order) {
        orderService.insertOrder(order);
    }

    @GetMapping("/flush")
    public String flushCache() {
        orderService.flushCache(); return "Done.";
    }

}
