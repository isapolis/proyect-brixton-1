package com.brixton.sodimac.dto.response.compras;

import com.brixton.sodimac.dto.generic.AuditDTO;
import com.brixton.sodimac.dto.response.generic.AuditResponseDTO;
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
public class ReqBuyResponseDTO extends AuditResponseDTO {
    private long id;
    private long employeeRequesterId;
    private String buyStatus;
    private String reasonStatus;
    private long employeeAssignedId;
    private List<ProductToBuyResponseDTO> productToBuys;

}
