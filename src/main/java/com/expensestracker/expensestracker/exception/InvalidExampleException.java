package com.expensestracker.expensestracker.exception;

public class InvalidExampleException extends RuntimeException {
    public InvalidExampleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}