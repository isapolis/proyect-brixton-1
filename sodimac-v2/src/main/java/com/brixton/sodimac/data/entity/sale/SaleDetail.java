package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id",foreignKey = @ForeignKey(name = "FK_product"))
    private Product product;
    @ManyToOne
    @JoinColumn(name = "proforma_id",foreignKey = @ForeignKey(name = "FK_proforma"))
    private Proforma proforma;
    @ManyToOne
    @JoinColumn(name = "statussale_id",foreignKey = @ForeignKey(name = "FK_statussale_id"))
    private StatusSale statusSale;

    private double salePrice;
    private double quantity;
    
}
