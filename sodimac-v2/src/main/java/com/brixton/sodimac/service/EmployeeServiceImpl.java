package com.brixton.sodimac.service;

import com.brixton.sodimac.controller.manageexception.GenericNotFoundException;
import com.brixton.sodimac.data.entity.employee.Area;
import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.data.enums.RegistryStateType;
import com.brixton.sodimac.data.repository.AreaRepository;
import com.brixton.sodimac.data.repository.EmployeeRepository;
import com.brixton.sodimac.dto.request.management.CreateEmployeeRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateEmployeeRequestDTO;
import com.brixton.sodimac.dto.response.management.EmployeeResponseDTO;
import com.brixton.sodimac.service.mapper.EmployeeMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ToString
public class EmployeeServiceImpl implements EmployeeService{

    private static final String USER_APP = "BRIXTON";

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AreaRepository areaRepository;

    @Override
    public EmployeeResponseDTO createEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO) {
        Employee employee = EmployeeMapper.INSTANCE.createEmployeeRequestDtoToEmployee(createEmployeeRequestDTO);
        employee.setCreatedAt(LocalDateTime.now());
        employee.setCreatedBy(USER_APP);
        employee.setRegistryState(RegistryStateType.ACTIVE);
        Area area = areaRepository.findById(employee.getArea().getId()).orElseThrow(() -> new GenericNotFoundException(("Id de Area no existente")));
        employee.getArea().setName(area.getName());
        log.info("Employee::: {}", employee);
        employeeRepository.save(employee);
        EmployeeResponseDTO employeeResponseDTO = EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employee);
        log.info("EmployeeResponseDTO: {}", employeeResponseDTO);
        return employeeResponseDTO;
    }

    @Override
    public List<EmployeeResponseDTO> createWithList(List<CreateEmployeeRequestDTO> inputEmployees){
        List<EmployeeResponseDTO> outputEmployees = new ArrayList<>();
        for(CreateEmployeeRequestDTO employeeRequestDTO: inputEmployees){
            try {
                outputEmployees.add(createEmployee(employeeRequestDTO));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return outputEmployees;
    }

    @Override
    public EmployeeResponseDTO getEmployee(long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException(("Empleado con Id no existente")));
        return  EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDTO> getListEmployee(){
        List<EmployeeResponseDTO> activeEmployees = new ArrayList<>();
        List<Employee> employeeFounds = employeeRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for (Employee employeActive : employeeFounds){
            activeEmployees.add(EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employeActive));
        }
        return activeEmployees;

    }

    @Override
    public EmployeeResponseDTO updateEmployee(long id, UpdateEmployeeRequestDTO employeeToUpdate){
        Employee employeeOriginal = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Empleado con Id no existente"));
        Employee employeeTemp =EmployeeMapper.INSTANCE.updateEmployeeRequestDtoToEmployee(employeeToUpdate);
        employeeOriginal.setName(employeeTemp.getName());
        employeeOriginal.setLastName(employeeTemp.getLastName());
        employeeOriginal.setAddress(employeeTemp.getAddress());
        employeeOriginal.setProfileId(employeeTemp.getProfileId());
        employeeOriginal.setPhone(employeeTemp.getPhone());
        employeeOriginal.setUpdatedAt(LocalDateTime.now());
        employeeOriginal.setUpdatedBy(USER_APP);
        Area area =areaRepository.findById(employeeTemp.getArea().getId()).orElseThrow(() -> new GenericNotFoundException("Área con Id no existente"));
        employeeTemp.getArea().setName(area.getName());
        employeeOriginal.setArea(employeeTemp.getArea());
        employeeRepository.save(employeeOriginal);
        return EmployeeMapper.INSTANCE.employeeToEmployeeResponseDto(employeeOriginal);
    }

    @Override
    public void deleteEmployee(long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Empleado con Id no encontrado"));
        employee.setRegistryState(RegistryStateType.INACTIVE);
        employee.setUpdatedAt(LocalDateTime.now());
        employee.setUpdatedBy(USER_APP);
        employeeRepository.save(employee);

    }


    /*

    //métodos del negocio
    @Override
    public Employee getEmployeeLogistic(long idEmployee){
        Employee employee=employees.get(idEmployee);
        if (employee!=null){
            if(employee.getStatus() == TypeStatusForAudit.INACTIVE){
                return null;
            }
            if (idEmployeeForLogistics.contains(employee.getId())){
                return employee;
            }
            return null;
        }
        return null;
    }

    @Override
    public Employee getEmployeeBuy(long idEmployee) {
        Employee employee=employees.get(idEmployee);
        if (employee!=null){
            if(employee.getStatus() == TypeStatusForAudit.INACTIVE){
                return null;
            }
            if (idEmployeeForBuys.contains(employee.getId())){
                return employee;
            }
            return null;
        }
        return null;
    }

    @Override
    public Employee getEmployeeSale(long idEmployee) {
        Employee employee=employees.get(idEmployee);
        if (employee!=null){
            if(employee.getStatus() == TypeStatusForAudit.INACTIVE){
                return null;
            }
            if (idEmployeeForSales.contains(employee.getId())){
                return employee;
            }
            return null;
        }
        return null;
    }

    @Override
    public Employee choseEmployeeBuyAleatorio() {

        List<Long> temp = new ArrayList<>(idEmployeeForBuys);
        if (temp.isEmpty()){
            return null;
        }
        Random random =new Random();
        int randomIndex = random.nextInt(temp.size());
        Long randomIdEmployee = temp.get(randomIndex);
        Employee employee = employees.get(randomIdEmployee);
        if (employee!=null && !employee.getStatus().equals(TypeStatusForAudit.INACTIVE)){
            return employee;
        }
        return null;

    }

    @Override
    public Employee findEmployeeByArea(long idEmployee) {

        return null;
    }
*/

}
