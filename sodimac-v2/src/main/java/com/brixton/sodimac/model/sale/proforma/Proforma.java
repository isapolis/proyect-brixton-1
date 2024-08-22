package com.brixton.sodimac.model.sale.proforma;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Proforma extends ObjectAudit {
    private long id;
    private long employeeId;
    private StatusProforma statusProforma;
    private double total;
    private LocalDateTime expirationDate;
    private List<SaleDetail> details;



}
