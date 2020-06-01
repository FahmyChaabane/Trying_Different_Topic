package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

}
