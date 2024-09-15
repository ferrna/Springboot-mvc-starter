package com.expensestracker.expensestracker.exception;

public class ExampleNotFoundException extends RuntimeException {
    public ExampleNotFoundException(Long id) {
        super("Game not found with id: " + id);
    }
}
