package com.brixton.sodimac.dto.request.compras;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductToBuyRequestDTO {
    @NotNull(message = "Id de Producto no puede ser vacio")
    private long idProduct;
    @NotNull(message = "Cantidad requerida no puede ser vacio")
    private double requiredQuantity;

    private double quantityStock;
    private double quantityInTransit;
    private double expectedAmount;
    private String reqBuyId;

}
