package com.brixton.sodimac.dto.sale;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDetailDTO {
    private long id;
    private long productId;
    private double salePrice;
    private double quantity;
    private double total;
    private String status;
}
