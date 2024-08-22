package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeRequestDTO{

    @NotNull(message = "Nombres no puede ser vacio")
    private String nombres;
    @NotNull(message = "Apellidos no puede ser vacio")
    private String apellidos;
    private String direccion;
    private String telefono;

    @NotNull(message = "Debe pertenecer a un área")
    @Min(1)
    @Max(100)
    private String area;

    @NotNull(message = "Debe asociarse a un perfil")
    @Min(1)
    @Max(100)
    private String perfil;

}
