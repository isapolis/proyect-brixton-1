package com.brixton.sodimac.dto.request.management;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateProductRequestDTO {
    @NotNull(message = "Nombre no puede ser vacio")
    private String name;
    @NotNull(message = "Categoria no puede ser vacia")
    private String category;
    @NotNull(message = "Cantidad no debe ser vacia")
    private int quantity;
    @NotNull(message = "Cantidad mínima del producto no puede ser vacio")
    private int minQuantity;

    @NotNull(message = "Precio de venta no puede ser vacio")
    private double priceSale;
    @NotNull(message = "Precio de proveedor no puede ser vacio")
    private double priceSupplier;

    @NotNull(message = "Código de producto debe tener el formato correcto")
    //@Pattern(regexp = "^[A-Z]{3}\\d{2}\\d{3}$")
    private String codeProduct;


}
