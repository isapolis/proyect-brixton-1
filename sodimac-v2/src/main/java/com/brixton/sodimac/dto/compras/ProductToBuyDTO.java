package com.brixton.sodimac.dto.compras;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductToBuyDTO {
    private long idProduct;
    private String name;
    private int quantityStock;
    private int quantityInTransit;
    private int requiredQuantity;
    private int expectedAmount;

}
