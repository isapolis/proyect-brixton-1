package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class PersonRequestDTO {

    private String address;
    private String phone;
    @Email
    @NotNull(message = "Email no puede ser vacio")
    private String email;

}
