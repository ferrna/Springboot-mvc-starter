package com.expensestracker.expensestracker.dto;

import com.expensestracker.expensestracker.model.Example;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExampleResponseWrapper {
    private Example example;
    private List<Example> examples;
    private String message;
    private ExampleDTO exampleData;

    public ExampleResponseWrapper(Example example) {
        this.example = example;
    }

    public ExampleResponseWrapper(List<Example> examples) {
        this.examples = examples;
    }

    public ExampleResponseWrapper(String message) {
        this.message = message;
    }

    public ExampleResponseWrapper(ExampleDTO exampleData) {
        this.exampleData = exampleData;
    }

    public Example getExample() {
        return example;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public String getMessage() {
        return message;
    }

    public ExampleDTO getExampleData() {
        return exampleData;
    }

}
