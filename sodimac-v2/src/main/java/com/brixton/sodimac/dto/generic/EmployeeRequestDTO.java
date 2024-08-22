package com.brixton.sodimac.dto.generic;

import com.brixton.sodimac.dto.request.management.PersonRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class EmployeeRequestDTO extends PersonRequestDTO {

    private String name;
    private String lastName;
    private String area;
    private String perfil;

}
