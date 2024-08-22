package com.brixton.sodimac.controller;

import com.brixton.sodimac.dto.request.management.CreateEmployeeRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateEmployeeRequestDTO;
import com.brixton.sodimac.dto.response.management.EmployeeResponseDTO;
import com.brixton.sodimac.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/management/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody CreateEmployeeRequestDTO inputEmployee){
        EmployeeResponseDTO employee = employeeService.createEmployee(inputEmployee);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/createWithList")
    public ResponseEntity<List<EmployeeResponseDTO>> createWithList(@Valid @RequestBody List<CreateEmployeeRequestDTO> inputEmployees){
        List<EmployeeResponseDTO> employees = employeeService.createWithList(inputEmployees);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable long id){
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDTO>> getListEmployee(){
        return new ResponseEntity<>(employeeService.getListEmployee(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@Valid @PathVariable long id, @RequestBody UpdateEmployeeRequestDTO employeeToUpdate){
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@Valid @PathVariable long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
