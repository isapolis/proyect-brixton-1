package com.brixton.sodimac.dto.compras;

import com.brixton.sodimac.dto.generic.AuditDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class BuyDTO extends AuditDTO {

    private long id;
    private long employeeId;
    private String buyStatus;
    private String dateStatus;
    private String motivoStatus;
    private long buyEmpoyeeId;
    private List<ProductToBuyDTO> productToBuys;


}
