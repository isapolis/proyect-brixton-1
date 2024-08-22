package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientLegalRequestDTO extends PersonRequestDTO{
    @NotNull(message = "Razon social no puede ser vacio")
    private String razonSocial;

    private byte supplier;
}
