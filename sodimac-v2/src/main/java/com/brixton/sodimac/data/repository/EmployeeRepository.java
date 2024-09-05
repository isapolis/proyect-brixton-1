package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.employee.Area;
import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByRegistryState(RegistryStateType registryState);
    Optional<List<Employee>> findByArea(Area area);
    Optional<Employee> findByIdAndArea(Long id, Area area);

    @Query(value = "SELECT E.* FROM Employee E  INNER JOIN AREA A ON E.AREA_ID=A.ID WHERE A.name = :area ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Employee findRandomEmployeeByArea(@Param("area") String area);


}
