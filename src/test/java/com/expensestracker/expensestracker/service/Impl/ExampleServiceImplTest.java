package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.dto.ExampleDTO;
import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.Impl.ExampleRepositoryImpl;
import com.expensestracker.expensestracker.service.ExampleValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExampleServiceImplTest {
    @Mock
    private ExampleRepositoryImpl exampleRepository;
    @Mock
    private ExampleValidationService createExampleValidationService;
    @Mock
    private ExampleValidationService updateExampleValidationService;
    @InjectMocks
    private ExampleServiceImpl exampleService;

    @BeforeEach
    public void setup() {
        Map<String, ExampleValidationService> validationServiceMap = new HashMap<>();
        validationServiceMap.put("createExampleValidationService", createExampleValidationService);
        validationServiceMap.put("updateExampleValidationService", updateExampleValidationService);

        ReflectionTestUtils.setField(exampleService, "validationServiceMap", validationServiceMap);
    }

    @Test
    @DisplayName("Test caso de exito al crear un registro Example")
    public void testCreateExample_Success() {
        // GIVEN
        Example example = new Example();
        example.setName("Example 5");
        example.setGenre("Apocaliptic");
        example.setPrice(23000.00);

        Example savedExample = new Example();
        savedExample.setId(1L);
        savedExample.setName("Example 5");
        savedExample.setGenre("Apocaliptic");
        savedExample.setPrice(23000.00);

        when(exampleRepository.save(any(Example.class))).thenReturn(savedExample);

        doNothing().when(createExampleValidationService).validateExample(any(Example.class));

        // WHEN
        ExampleDTO result = exampleService.createExample(example);

        // THEN
        assertEquals(1L, result.getId());
        assertEquals("Example 5", result.getName());
        verify(exampleRepository, times(1)).save(example);
        verify(createExampleValidationService, times(1)).validateExample(example);

    }

    @Test
    @DisplayName("Caso de excepcion para Example no encontrado")
    public void testExampleById_NotFound(){
        // GIVEN
        when(exampleRepository.findById(1L)).thenReturn(Optional.empty());

        // THEN
        assertThrows(ExampleNotFoundException.class, () -> exampleService.getExampleById(1L));
        verify(exampleRepository, times(1)).findById(1L);
    }
}