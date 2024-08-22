package com.brixton.sodimac.model.sale.proforma;

public enum StatusDetail {

    AVAILABLE(1),
    OUT_OF_STOCK(2);
    private Integer type;

    StatusDetail(Integer type) {
        this.type = type;
    }

    Integer getType() {
        return this.type;
    }
}
