package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRegistryState(RegistryStateType registryState);

}
