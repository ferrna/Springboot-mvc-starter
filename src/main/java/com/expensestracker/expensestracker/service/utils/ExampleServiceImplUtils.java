package com.expensestracker.expensestracker.service.utils;

import com.expensestracker.expensestracker.dto.ExampleDTO;
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

    private Example convertToEntity(ExampleDTO exampleDTO) {
        Example example = new Example();
        example.setName(exampleDTO.getName());
        example.setGenre(exampleDTO.getGenre());
        example.setPrice(exampleDTO.getPrice());
        return example;
    }
}
