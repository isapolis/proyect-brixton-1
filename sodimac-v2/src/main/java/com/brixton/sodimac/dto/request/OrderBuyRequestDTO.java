package com.brixton.sodimac.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderBuyRequestDTO {
    @NotNull(message = "Id de proveedor no puede ser vacio")
    private long idSupplier;
    @NotNull(message = "Id de solicitud de compra no puede ser vacio")
    private long idRequestBuy;
//    @NotNull(message = "Id de Empleado no puede ser vacio")
//    private long idEmployee;

}
