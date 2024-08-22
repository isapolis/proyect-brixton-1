package com.brixton.sodimac.dto.response.management;

import com.brixton.sodimac.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponseDTO extends AuditResponseDTO {
    private long id;
    private String name;
    private String category;
    private int quantity;
    private int minQuantity;
    private double priceSupplier;
    private double priceSale;
    private String codeProduct;

}
