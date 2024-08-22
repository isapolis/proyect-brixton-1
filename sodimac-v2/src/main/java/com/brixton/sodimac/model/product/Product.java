package com.brixton.sodimac.model.product;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Product extends ObjectAudit {
    private long id;
    private String name;
    private CategoryProduct category;
    private double quantity;
    private double minQuantity = 50;
    private double priceSupplier;
    private double priceSale;
    private String codeProduct;
}
