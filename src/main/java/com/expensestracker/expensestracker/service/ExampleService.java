package com.expensestracker.expensestracker.service;

import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.dto.ExampleDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ExampleService {
    List<Example> getAllExamples();
    ExampleDTO getExampleById(@PathVariable Long id);
    ExampleDTO createExample(@RequestBody Example example);
    int updateExample(@PathVariable Long id, @RequestBody Example example);
    void deleteExample(@PathVariable Long id);
    List<Example> getAllExamplesByGenre(@RequestParam String genre);
}