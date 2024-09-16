package com.expensestracker.expensestracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataAccessException;

@ControllerAdvice
public class GlobalExceptionHandler {

//  @ExceptionHandler for a specific superclass will handle all exceptions that are
//  instances of that superclass, which includes any subclass.
//  When you define separate @ExceptionHandler methods for specific exceptions, Spring
//  will use the more specific handler if the exception matches exactly.

    @ExceptionHandler(ExampleNotFoundException.class)
    public ResponseEntity<String> handleExampleNotFound(ExampleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidExampleException.class)
    public ResponseEntity<String> handleInvalidExampleException(InvalidExampleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CustomDataAccessException.class)
    public ResponseEntity<String> handleCustomDataAccessException(CustomDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        // Customize the response for DataAccessException
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}
