package com.brixton.sodimac.service;

import com.brixton.sodimac.data.entity.employee.Area;
import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.data.repository.AreaRepository;
import com.brixton.sodimac.data.repository.EmployeeRepository;
import com.brixton.sodimac.dto.request.management.CreateEmployeeRequestDTO;
import com.brixton.sodimac.dto.request.management.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.management.EmployeeResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {

    @Mock
    AreaRepository areaRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeServiceImpl employeeService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createEmployee() {
        //Arrange - Definir el escenario en la prueba
        CreateEmployeeRequestDTO employeeRequestDTO = new CreateEmployeeRequestDTO();
        Employee employee = new Employee();
        employee.setId(1000L);
        Area area = new Area();
        area.setName("AreaX");
        EmployeeResponseDTO expected = new EmployeeResponseDTO();
        expected.setId(1000L);
        expected.setArea("AreaX");

        when(areaRepository.findById(any())).thenReturn(Optional.of(area));
        when(employeeRepository.save(any())).thenReturn(employee);

        //Act - Ejecutar el escenario de mi prueba
        EmployeeResponseDTO temp = employeeService.createEmployee(employeeRequestDTO);

        //Assert - Verificar el resultado del escenario
        assertEquals(expected.getArea(), temp.getArea());



    }

    @Test
    void createWithList() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void getListEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}