package com.expensestracker.expensestracker.controller.v1;

import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.service.Impl.ExampleDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseWrapper {
    private Example example;
    private List<Example> examples;
    private String message;
    private ExampleDTO exampleData;

    public ResponseWrapper(Example example) {
        this.example = example;
    }

    public ResponseWrapper(List<Example> examples) {
        this.examples = examples;
    }

    public ResponseWrapper(String message) {
        this.message = message;
    }

    public ResponseWrapper(ExampleDTO exampleData) {
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
