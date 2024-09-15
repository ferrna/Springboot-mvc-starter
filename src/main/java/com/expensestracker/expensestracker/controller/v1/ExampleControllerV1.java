package com.expensestracker.expensestracker.controller.v1;

import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.service.Impl.ExampleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/examples")
public class ExampleControllerV1 {

    @Autowired
    ExampleServiceImpl exampleService;

    @Operation(summary = "Devuelve un ejemplo de la bbdd", description = "Debe enviar el id del ejemplo a buscar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "500", description = "")
    })
    @GetMapping
    public ResponseEntity<List<Example>> getAllExamples() {
        List<Example> examples = exampleService.getAllExamples();
        return ResponseEntity.ok(examples);
    }

    @Operation(summary = "Devuelve un ejemplo de la bbdd", description = "Debe enviar el id del ejemplo a buscar.", tags = {"pets"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ""),
            @ApiResponse(responseCode = "500", description = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Example> getExampleById(@PathVariable Long id) {
        Example example = exampleService.getExampleById(id);
        return ResponseEntity.ok(example);
    }

    @PostMapping
    public ResponseEntity<Example> createExample(@RequestBody Example example){
        Example createdExample = exampleService.createExample(example);
        return ResponseEntity.ok(createdExample);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Example> updateExample(@PathVariable Long id, @RequestBody Example example){
        Example updatedExample = exampleService.updateExample(id, example);
        return ResponseEntity.ok(updatedExample);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExample(@PathVariable Long id) {
        exampleService.deleteExample(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Example>> getAllExamplesByGenre(@RequestParam String genre) {
        List<Example> examples = exampleService.getAllExamplesByGenre(genre);
        return ResponseEntity.ok(examples);
    }

}
