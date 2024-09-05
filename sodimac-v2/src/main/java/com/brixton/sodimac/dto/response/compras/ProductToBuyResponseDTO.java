package com.brixton.sodimac.dto.response.compras;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductToBuyResponseDTO {
    private String nameProduct;
    private double requiredQuantity;
    private double quantityStock;
    private double quantityInTransit;
    private double expectedQuantity;

}
