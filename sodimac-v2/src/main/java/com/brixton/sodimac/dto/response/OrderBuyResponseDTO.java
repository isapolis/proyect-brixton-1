package com.brixton.sodimac.dto.response;

import com.brixton.sodimac.dto.generic.AuditDTO;
import com.brixton.sodimac.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrderBuyResponseDTO extends AuditResponseDTO {
    private long id;
    private long idRequestBuy;
    private long idSupplier;
    private long idEmployee;
    private String dateStatus;
    private String orderStatus;

}
