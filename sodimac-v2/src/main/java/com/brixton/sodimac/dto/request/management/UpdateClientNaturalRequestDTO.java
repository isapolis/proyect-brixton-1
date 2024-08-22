package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientNaturalRequestDTO extends PersonRequestDTO{
    @NotNull(message = "Nombre no puede ser vacio")
    private String name;
    @NotNull(message = "Apellido no puede ser vacio")
    private String lastName;
    @NotNull(message = "Documento no puede ser vacio")
    @Min(1)
    @Max(20)
    private String document;

}
