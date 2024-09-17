package com.expensestracker.expensestracker.service.validation;

import com.expensestracker.expensestracker.exception.InvalidExampleException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.service.ExampleValidationService;

public abstract class BaseExampleValidationService implements ExampleValidationService {

    public static final String INVALID_NAME = "Name must be between 3 and 60 characters long";
    public static final String INVALID_PRICE = "Name must be between 3 and 60 characters long";


    protected void validateNameLength(Example example) throws InvalidExampleException {
        if (example.getName().length() < 3 || example.getName().length() > 60) {
            throw new InvalidExampleException(INVALID_NAME);
        }
    }

    protected void validatePriceNonNegative(Example example) throws InvalidExampleException {
        if (example.getPrice() == null || example.getPrice() < 0) {
            throw new InvalidExampleException(INVALID_PRICE);
        }
    }

    // Other shared validation logic can go here
}
