package com.brixton.sodimac.dto.response.compras;

import com.brixton.sodimac.dto.generic.AuditDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IncomeDTO extends AuditDTO {
    private long id;
    private long idEmployee;
    private long idOrderBuy;
    private String incomeStatus;

}
