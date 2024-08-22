package com.brixton.sodimac.dto.response.management;

import com.brixton.sodimac.dto.response.generic.PersonResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientNaturalResponseDTO extends PersonResponseDTO {
    private String name;
    private String lastName;
    private String document;
    private String number;

}
