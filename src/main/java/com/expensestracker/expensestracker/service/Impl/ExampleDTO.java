package com.expensestracker.expensestracker.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleDTO {
    private Long id;
    private String name;
    private String genre;
    private Double price;
}
