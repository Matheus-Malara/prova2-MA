package br.edu.uniara.lpi2.rest.controller;

import br.edu.uniara.lpi2.rest.model.Employee;
import br.edu.uniara.lpi2.rest.model.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeControllerTest {

    @Mock
    EmployeeRepository repository;

    @Autowired
    @InjectMocks
    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("testBuscaID")
    void testBuscaID() {
        Employee employee = new Employee("test", "test");
        when(repository.findById(1L)).thenReturn(Optional.of(employee));
        Employee result = controller.one(1L);
        assertEquals(employee, result);
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("testInsert")
    void testInsert() {
        Employee employee = new Employee("teste", "teste");
        when(repository.save(any(Employee.class))).thenReturn(employee);
        ResponseEntity<Employee> response = controller.insert(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
        verify(repository).save(employee);
    }

    @Test
    @DisplayName("testDelete")
    void testDelete() {
        when(repository.existsById(1L)).thenReturn(true);
        ResponseEntity<?> response = controller.delete(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1was removed", response.getBody());
        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
    }

}