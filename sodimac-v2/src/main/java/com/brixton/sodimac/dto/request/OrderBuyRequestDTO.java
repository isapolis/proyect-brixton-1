package com.brixton.sodimac.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderBuyRequestDTO {

    private long idBuy;
    private long idSupplier;
    private long idEmployee;

}
