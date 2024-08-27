package com.brixton.sodimac.dto.request.compras;

import com.brixton.sodimac.dto.generic.AuditDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class ReqBuyRequestDTO extends AuditDTO {
    @NotNull(message = "ID empleado no puede ser vacio")
    private long employeeCreatorId;
    @NotNull(message = "Estado de compra no puede ser vacio")
    @Min(1)
    @Max(4)
    private String buyStatus;
    @NotNull(message = "Lista de productos por comprar no puede ser vacio")
    private List<ProductToBuyRequestDTO> productToBuys;

    private String motivoStatus;
    private long buyEmpoyeeId;

    //private String dateStatus;

}
