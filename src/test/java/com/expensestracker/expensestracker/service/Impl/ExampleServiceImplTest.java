package com.expensestracker.expensestracker.service.Impl;

import com.expensestracker.expensestracker.exception.ExampleNotFoundException;
import com.expensestracker.expensestracker.model.Example;
import com.expensestracker.expensestracker.repository.Impl.ExampleRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class ExampleServiceImplTest {
    @Mock
    private ExampleRepositoryImpl exampleRepository;
    @InjectMocks
    private ExampleServiceImpl exampleService;

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

        // WHEN
        Example result = exampleService.createExample(example);

        // THEN
        assertEquals(1L, result.getId());
        assertEquals("Example 5", result.getName());
        verify(exampleRepository, times(1)).save(example);
    }

    @Test
    @DisplayName("Caso de excepcion para Example no encontrado")
    public void testExampleById_NotFound(){
        // GIVEN
        when(exampleRepository.findById(1L)).thenReturn(null);

        // THEN
        assertThrows(ExampleNotFoundException.class, () -> exampleService.getExampleById(1L));
        verify(exampleRepository, times(1)).findById(1L);
    }
}