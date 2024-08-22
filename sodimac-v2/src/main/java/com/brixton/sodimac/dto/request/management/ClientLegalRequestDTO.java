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
public class ClientLegalRequestDTO extends PersonRequestDTO {
    @NotNull(message = "Ruc no puede ser vacio")
    private String ruc;
    @NotNull(message = "Razon social no puede ser vacio")
    private String razonSocial;
    @Min(1)
    @Max(2)
    private byte supplier;

}
