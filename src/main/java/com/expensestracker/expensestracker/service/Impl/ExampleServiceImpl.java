package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.dto.ExampleDTO;
import com.expensestracker.expensestracker.exception.CustomDataAccessException;
import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.exception.InvalidExampleException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.ExampleRepository;
import com.expensestracker.expensestracker.service.ExampleService;
import com.expensestracker.expensestracker.service.ExampleValidationService;
import com.expensestracker.expensestracker.service.utils.ExampleServiceImplUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExampleServiceImpl implements ExampleService {

    private final ExampleRepository exampleRepository;
    private final Map<String, ExampleValidationService> validationServiceMap;

    @Autowired
    public ExampleServiceImpl(ExampleRepository exampleRepository,
                              Map<String, ExampleValidationService> validationServiceMap) {
        this.exampleRepository = exampleRepository;
        this.validationServiceMap = validationServiceMap;
    }

    @Override
    public List<Example> getAllExamples() {
        return exampleRepository.findAll();
    }

    @Override
    public ExampleDTO getExampleById(Long id) {
        Optional<Example> exampleById = exampleRepository.findById(id);
        if(exampleById.isEmpty()) {
            throw new ExampleNotFoundException(id);
        }
        return ExampleServiceImplUtils.convertEntityToDTO(exampleById.get());
    }

    @Override
    public ExampleDTO createExample(Example example) throws InvalidExampleException, CustomDataAccessException {
        try {
            ExampleValidationService createValidationService = validationServiceMap.get("createExampleValidationService");
            createValidationService.validateExample(example);
            Example savedExample = exampleRepository.save(example);
            return ExampleServiceImplUtils.convertEntityToDTO(savedExample);
        } catch (InvalidExampleException e) {
            throw new InvalidExampleException("Invalid Example provided: " + e.getMessage());
        } catch (DataAccessException e) {
            // jdbcTemplate.update() exception
            throw new CustomDataAccessException("Could not create register in database");
        }
    }

    @Override
    public int updateExample(Long id, Example example) throws ExampleNotFoundException {
        try {
            // validate Example, convert to DTO, or validate in Repository
            ExampleValidationService updateValidationService = validationServiceMap.get("updateExampleValidationService");
            updateValidationService.validateExample(example);
            int updatedCount = exampleRepository.update(id, example);
            if (updatedCount == 0) {
                throw new ExampleNotFoundException(id);
            }
            return updatedCount;

        } catch (DataAccessException e) {
            throw new ExampleNotFoundException(id);
        }
    }

    @Override
    public void deleteExample(Long id) throws ExampleNotFoundException {
        try {
            int deletedCount = exampleRepository.delete(id);
            if (deletedCount == 0) {
                throw new ExampleNotFoundException(id);
            }
        } catch (DataAccessException e) {
            // Handle specific exception or rethrow as a custom exception
            throw new CustomDataAccessException(
                    "Could not delete register from database, "
                            + e.getMessage() + ", id: " + id);
        }
    }

    @Override
    public List<Example> getAllExamplesByGenre(String genre) {
        return exampleRepository.findAll()
                .stream()
                .filter(example -> example.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

}
