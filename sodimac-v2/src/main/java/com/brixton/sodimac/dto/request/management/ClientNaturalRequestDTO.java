package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClientNaturalRequestDTO extends PersonRequestDTO {
    @NotNull(message = "Nombre no puede ser vacio")
    private String name;
    @NotNull(message = "Apellido no puede ser vacio")
    private String lastName;
    @NotNull(message = "Documento no puede ser vacio")
    @Min(1)
    @Max(3)
    private String document;
    @NotNull(message = "Número de documento no puede ser vacio")
    private String number;

}
