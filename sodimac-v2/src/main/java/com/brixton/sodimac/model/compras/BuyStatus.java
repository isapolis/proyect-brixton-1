package com.brixton.sodimac.model.compras;

public enum BuyStatus {
    TEMPORARY(1),
    CONFIRMED(2),
    APPROVED(3),
    REJECTED(4);

    private Integer statusBuy;
    BuyStatus(Integer statusBuy){
        this.statusBuy = statusBuy;
    }

    public Integer getStatusBuy() {
        return this.statusBuy;
    }
}
