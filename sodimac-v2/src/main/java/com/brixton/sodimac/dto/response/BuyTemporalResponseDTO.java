package com.brixton.sodimac.dto.response;

import com.brixton.sodimac.dto.request.compras.ProductToBuyRequestDTO;
import com.brixton.sodimac.dto.generic.AuditDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BuyTemporalResponseDTO extends AuditDTO {

    private long employeeId;
    private String buyStatus;
    private String dateStatus;
    private String motivoStatus;
    private List<ProductToBuyRequestDTO> productToBuys;

}
