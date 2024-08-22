package com.brixton.sodimac.dto.response.management;

import com.brixton.sodimac.dto.response.generic.AuditResponseDTO;
import com.brixton.sodimac.dto.response.generic.PersonResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO extends PersonResponseDTO {
    private long id;
    private String nombres;
    private String apellidos;
    private String area;
    private String perfil;
}
