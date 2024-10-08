package com.expensestracker.expensestracker.controller.v1;

import com.expensestracker.expensestracker.dto.ExampleResponseWrapper;
import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.service.ExampleService;
import com.expensestracker.expensestracker.dto.ExampleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// With RestController Spring understand that it has to return the response object serialized into JSON or XML format
// RestController is a combination of Controller and ResponseBody, with Controller only Spring
// expects the return value to be a view name, and it will attempt to resolve and render a corresponding view
@RestController
@RequestMapping("/api/v1/examples")
public class ExampleControllerV1 {

    private final ExampleService exampleService;

    @Autowired
    public ExampleControllerV1(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
    @Operation(summary = "Returns all examples from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examples returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> getAllExamples() {
        List<Example> examples = exampleService.getAllExamples();
        return ResponseEntity.ok(new ExampleResponseWrapper(examples));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns an example from db", description = "Must send ID of example to find", tags = {"Finds"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Example found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> getExampleById(@Parameter(description = "ID of example") @PathVariable Long id) {
        ExampleDTO example = exampleService.getExampleById(id);
        return ResponseEntity.ok(new ExampleResponseWrapper(example));
    }

    @PostMapping
    @Operation(summary = "Creates register Example in db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Example created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> createExample(@RequestBody Example example){
        ExampleDTO createdExample = exampleService.createExample(example);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ExampleResponseWrapper(createdExample));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Updates a register Example in db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Example updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> updateExample(@PathVariable Long id, @RequestBody Example example){
        exampleService.updateExample(id, example);
        return ResponseEntity.ok(new ExampleResponseWrapper("Example updated"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a register Example from db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Example deleted"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> deleteExample(@PathVariable Long id) {
        exampleService.deleteExample(id);
        return ResponseEntity.ok(new ExampleResponseWrapper("Example deleted"));
    }

    @GetMapping("/filter")
    @Operation(summary = "Filters Example registers by Genre from db" , description = "Must send Genre String", tags = {"Finds"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Examples returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExampleResponseWrapper> getAllExamplesByGenre(@RequestParam String genre) {
        List<Example> examples = exampleService.getAllExamplesByGenre(genre);
        return ResponseEntity.ok(new ExampleResponseWrapper(examples));
    }


    // Handle ExampleNotFoundException
    @ExceptionHandler(ExampleNotFoundException.class)
    public ResponseEntity<ExampleResponseWrapper> handleExampleNotFound(ExampleNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExampleResponseWrapper(ex.getMessage()));
    }

}

//    return ResponseEntity.ok(example);
//    return new ResponseEntity<>(HttpStatus.OK);
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found: " + id);
//    return ResponseEntity.noContent().build();
//    return ResponseEntity.ok()
//            .header("Custom-Header", "CustomHeaderValue")
//                             .body(users);
//    return ResponseEntity.ok("User found: " + id);