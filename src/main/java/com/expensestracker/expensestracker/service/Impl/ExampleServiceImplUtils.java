package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.exception.InvalidExampleException;
import com.expensestracker.expensestracker.model.Example;

public class ExampleServiceImplUtils {

    public static ExampleDTO convertEntityToDTO(Example example) {
        ExampleDTO dto = new ExampleDTO();
        dto.setId(example.getId());
        dto.setName(example.getName());
        dto.setGenre(example.getGenre());
        dto.setPrice(example.getPrice());
        return dto;
    }

    public static void validExampleProvided(Example example) throws InvalidExampleException {
        if (example.getName().length() < 3 || example.getName().length() > 60 ){
            throw new InvalidExampleException("Name must be between 3 and 60 characters long");
        }
    }
}
