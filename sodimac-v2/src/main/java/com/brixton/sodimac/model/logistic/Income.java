package com.brixton.sodimac.model.logistic;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Income extends ObjectAudit {
    private long id;
    private long idEmployee;
    private long idOrderBuy;
    private IncomeStatus incomeStatus;

}
