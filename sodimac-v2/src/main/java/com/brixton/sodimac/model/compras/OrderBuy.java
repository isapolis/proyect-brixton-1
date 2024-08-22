package com.brixton.sodimac.model.compras;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderBuy extends ObjectAudit {
    private long id;
    private long idBuy;
    private long idSupplier;
    private long idEmployee;
    private LocalDateTime dateStatus;
    private StatusOrder orderStatus;

}
