package com.expensestracker.expensestracker.exception;

import org.springframework.dao.DataAccessException;

public class CustomDataAccessException extends DataAccessException {
    public CustomDataAccessException(String message) {
        super(message);
    }
}