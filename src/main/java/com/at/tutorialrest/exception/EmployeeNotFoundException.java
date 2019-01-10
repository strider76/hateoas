package com.at.tutorialrest.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super(String.format("Could not find Employee %d", id));
    }
}
