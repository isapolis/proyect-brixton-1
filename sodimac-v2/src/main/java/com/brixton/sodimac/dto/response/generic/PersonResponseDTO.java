package com.brixton.sodimac.dto.response.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonResponseDTO extends AuditResponseDTO{
    private String address;
    private String phone;
    private String email;

}
