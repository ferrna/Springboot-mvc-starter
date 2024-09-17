package com.expensestracker.expensestracker.service;

import com.expensestracker.expensestracker.exception.InvalidExampleException;
import com.expensestracker.expensestracker.model.Example;

public interface ExampleValidationService {
    void validateExample(Example example) throws InvalidExampleException;
}