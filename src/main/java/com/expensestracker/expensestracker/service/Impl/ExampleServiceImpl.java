package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.Impl.ExampleRepositoryImpl;
import com.expensestracker.expensestracker.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    ExampleRepositoryImpl exampleRepository;

    @Override
    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    @Override
    public Example getExampleById(Long id) {
        if(exampleRepository.findById(id) == null){
            throw new ExampleNotFoundException(id);
        }
        return exampleRepository.findById(id);
    }

    @Override
    public Example createExample(Example example) {
        return exampleRepository.save(example);
    }

    @Override
    public Example updateExample(Long id, Example example) {
        if(exampleRepository.findById(id) == null) {
            throw new ExampleNotFoundException(id);
        }
        exampleRepository.update(id, example);
        return example;
    }

    @Override
    public void deleteExample(Long id) {
        if(exampleRepository.findById(id) == null) {
            throw new ExampleNotFoundException(id);
        }
        exampleRepository.delete(id);
    }

    @Override
    public List<Example> getAllExamplesByGenre(String genre) {
        return exampleRepository.findAll()
                .stream()
                .filter(example -> example.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
