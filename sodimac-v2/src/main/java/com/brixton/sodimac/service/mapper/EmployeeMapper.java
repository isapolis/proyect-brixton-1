package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.employee.Employee;
import com.brixton.sodimac.dto.request.management.CreateEmployeeRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateEmployeeRequestDTO;
import com.brixton.sodimac.dto.response.management.EmployeeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

    @Mapper
    public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    @Mapping(source = "nombres", target = "name")
    @Mapping(source = "apellidos", target = "lastName")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "area", target = "area.id")
    @Mapping(source = "perfil", target = "profileId")
    Employee createEmployeeRequestDtoToEmployee(CreateEmployeeRequestDTO createEmployeeRequestDTO);

    @Mapping(source = "name", target = "nombres")
    @Mapping(source = "lastName", target = "apellidos")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "area.name", target = "area")
    @Mapping(source = "profileId", target = "perfil")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    EmployeeResponseDTO employeeToEmployeeResponseDto(Employee employee);

    @Mapping(source = "nombres", target = "name")
    @Mapping(source = "apellidos", target = "lastName")
    @Mapping(source = "direccion", target = "address")
    @Mapping(source = "telefono", target = "phone")
    @Mapping(source = "area", target = "area.id")
    @Mapping(source = "perfil", target = "profileId")
    Employee updateEmployeeRequestDtoToEmployee(UpdateEmployeeRequestDTO updateEmployeeRequestDTO);

}
