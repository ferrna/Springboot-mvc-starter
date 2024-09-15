package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.Impl.ExampleRepositoryImpl;
import com.expensestracker.expensestracker.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Example> exampleById = exampleRepository.findById(id);
        if(exampleById.isEmpty()) {
            throw new ExampleNotFoundException(id);
        }
        return exampleById.get();
    }

    @Override
    public Example createExample(Example example) {
        // build example dto, body and response
        //InvalidExampleException
        return exampleRepository.save(example);
    }

    @Override
    public int updateExample(Long id, Example example) throws ExampleNotFoundException {
        try {
            int updatedCount = exampleRepository.update(id, example);
            if (updatedCount == 0) {
                throw new ExampleNotFoundException(id);
            }
            return updatedCount;

        } catch (DataAccessException e) {
            // Handle specific exception or rethrow as a custom exception
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
            throw new ExampleNotFoundException(id);
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


//// DTO in Service Layer
//public class UserDTO {
//    private String username;
//    private String email;
//    // getters and setters
//}

//return convertToDTO(userEntity);
//        }
//
//private UserDTO convertToDTO(UserEntity userEntity) {
//        UserDTO dto = new UserDTO();
//        dto.setUsername(userEntity.getUsername());
//        dto.setEmail(userEntity.getEmail());
//        return dto;
//        }