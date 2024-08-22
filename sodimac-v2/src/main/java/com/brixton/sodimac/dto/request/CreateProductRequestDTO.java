package com.brixton.sodimac.dto.request;


import com.brixton.sodimac.dto.generic.CategoryProductDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class CreateProductRequestDTO {
    @NotNull(message = "Nombre no puede ser vacio")
    private String name;
    @NotNull(message = "Categoria no puede ser vacia")
    private String category;
    @NotNull(message = "Cantidad no debe ser vacia")
    private int quantity;

    @NotNull(message = "Precio de venta no puede ser vacio")
    private double priceSale;
    @NotNull(message = "Precio de proveedor no puede ser vacio")
    private double priceSupplier;

    //@NotNull(message = "Código de producto debe tener el formato correcto")
    @Pattern(regexp = "^[A-Z]{3}\\d{2}\\d{3}$")
    private String codeProduct;

//    private String codeProduct;
//    @NotNull(message = "Registra la cantidad mínima del producto")
    private int minQuantity;

}
