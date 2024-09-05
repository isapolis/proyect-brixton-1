package com.brixton.sodimac.service;

import com.brixton.sodimac.data.entity.employee.Area;
import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.dto.request.management.CreateEmployeeRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateEmployeeRequestDTO;
import com.brixton.sodimac.dto.response.management.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO inputEmployee);

    List<EmployeeResponseDTO> createWithList(List<CreateEmployeeRequestDTO> inputEmployees);

    EmployeeResponseDTO getEmployee(long id);

    List<EmployeeResponseDTO> getListEmployee();

    EmployeeResponseDTO updateEmployee(long id, UpdateEmployeeRequestDTO employeeToUpdate);

    void deleteEmployee(long id);

    Employee getEmployeeFromData(long id);

    Employee findEmployeeByArea(Long id, Area area);

    List<Employee> employeesByArea(Area area);

    //Employee employeeAleatoryByBuy(Area area);

    Employee findRandomEmployeeByArea(String nameArea);

//    List<Object> createWithList(List<EmployeeDTO> inputEmployees);
//    Object getEmployee(long id);
//    List<Object> getListEmployees();
//    Object updateEmployee(long id, EmployeeDTO employee);
//    Object deleteEmployee(long id);
//    //procesos del negocio
//    Employee getEmployeeLogistic(long idEmployee);
//    Employee getEmployeeBuy(long idEmployee);
//    Employee getEmployeeSale(long idEmployee);
//    Employee choseEmployeeBuyAleatorio();
//    Employee findEmployeeByArea(long idEmployee);


//    Object createEmployee(EmployeeDTO inputEmployee);
//    List<Object> createWithList(List<EmployeeDTO> inputEmployees);
//    Object getEmployee(long id);
//    List<Object> getListEmployees();
//    Object updateEmployee(long id, EmployeeDTO employee);
//    Object deleteEmployee(long id);
//    //procesos del negocio
//    Employee getEmployeeLogistic(long idEmployee);
//    Employee getEmployeeBuy(long idEmployee);
//    Employee getEmployeeSale(long idEmployee);
//    Employee choseEmployeeBuyAleatorio();
//    Employee findEmployeeByArea(long idEmployee);


}
