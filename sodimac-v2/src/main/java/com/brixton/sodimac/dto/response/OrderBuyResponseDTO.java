package com.brixton.sodimac.dto.response;

import com.brixton.sodimac.dto.generic.AuditDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderBuyResponseDTO extends AuditDTO {
    private long id;
    private long idBuy;
    private long idSupplier;
    private long idEmployee;
    private String dateStatus;
    private String orderStatus;

}
