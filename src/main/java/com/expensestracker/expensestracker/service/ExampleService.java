package com.expensestracker.expensestracker.service;

import com.expensestracker.expensestracker.model.Example;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ExampleService {
    List<Example> getAllExamples();
    Example getExampleById(@PathVariable Long id);
    Example createExample(@RequestBody Example example);
    Example updateExample(@PathVariable Long id, @RequestBody Example example);
    void deleteExample(@PathVariable Long id);
    List<Example> getAllExamplesByGenre(@RequestParam String genre);
}