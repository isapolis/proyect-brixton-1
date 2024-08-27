package com.brixton.sodimac.dto.response.compras;

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
public class ReqBuyResponseDTO extends AuditDTO {
    private long employeeCreatorId;
    private String buyStatus;
    private String motivoStatus;
    private long buyEmpoyeeId;
    private List<ProductToBuyResponseDTO> productToBuys;

}
