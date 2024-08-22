package com.brixton.sodimac.model.compras;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductToBuy {
    private long idProduct;
    private String name;
    private double quantityStock;
    private double quantityInTransit;
    private double requiredQuantity;
    private double expectedAmount;

}
