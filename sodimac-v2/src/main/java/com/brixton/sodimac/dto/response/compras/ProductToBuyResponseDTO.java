package com.brixton.sodimac.dto.response.compras;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductToBuyResponseDTO {
    private long id;
    private String name;
    private double quantityStock;
    private double quantityInTransit;
    private double requiredQuantity;
    private double expectedAmount;

}
