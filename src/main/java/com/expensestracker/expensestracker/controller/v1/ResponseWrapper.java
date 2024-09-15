package com.expensestracker.expensestracker.controller.v1;

import com.expensestracker.expensestracker.model.Example;

import java.util.List;
import java.util.Optional;

public class ResponseWrapper {
    private Example example;
    private List<Example> examples;
    private String message;

    public ResponseWrapper(Example example) {
        this.example = example;
    }

    public ResponseWrapper(List<Example> examples) {
        this.examples = examples;
    }

    public ResponseWrapper(String message) {
        this.message = message;
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
}
