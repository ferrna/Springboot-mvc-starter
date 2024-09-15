package com.expensestracker.expensestracker.repository;

import com.expensestracker.expensestracker.model.Example;

import java.util.List;
import java.util.Optional;

public interface ExampleRepository {
    List<Example> findAll();
    Optional<Example> findById(Long id);
    Example save(Example example);
    int update(Long id, Example example);
    int delete(Long id);
}