package com.brixton.sodimac.model.compras;

public enum StatusOrder {

    REQUESTED(1),
    CANCELED(2),
    RECEIVED(3);

    private Integer statusOrder;

    StatusOrder (Integer statusOrder){
        this.statusOrder=statusOrder;
    }

    public Integer getStatusOrder(){
        return this.statusOrder;
    }

}
