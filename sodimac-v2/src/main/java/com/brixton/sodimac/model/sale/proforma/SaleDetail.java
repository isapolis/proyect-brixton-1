package com.brixton.sodimac.model.sale.proforma;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaleDetail {
    private long id;
    private long productId;
    private double salePrice;
    private double quantity;
    private double total;
    private StatusDetail status;

}
