package com.brixton.sodimac.model.sale.proforma;

public enum StatusProforma {

    CONFIRMED(1),
    TEMPORAL(2);
    private Integer type;

    StatusProforma(Integer type) {
        this.type = type;
    }

    Integer getType() {
        return this.type;
    }
}
