package com.at.tutorialrest.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super(String.format("Order Not Found id %d", id));
    }
}
