package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.employee.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaRepository extends JpaRepository<Area, Byte> {
    Area findByName(String name);

}
