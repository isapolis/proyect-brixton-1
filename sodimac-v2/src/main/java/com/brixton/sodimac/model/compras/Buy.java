package com.brixton.sodimac.model.compras;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
public class Buy extends ObjectAudit {

    private long id;
    private long employeeId;
    private BuyStatus buyStatus;
    private LocalDate dateStatus;
    private String motivoStatus;
    private long buyEmployeeId;
    private List<ProductToBuy> productToBuys;


}
