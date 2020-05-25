package com.example.demo;

public class OrderListEmptyException extends RuntimeException {

    public OrderListEmptyException(String message) {
        super(message);
    }
}
