package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detailbill")
@Getter
@Setter
public class DetailBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_detail"))
    private Product product;
    @ManyToOne
    @JoinColumn(name = "bill_id",foreignKey = @ForeignKey(name = "FK_bill_detail"))
    private Bill bill;

    private double salePrice;

    private double quantity;

    private double total;

}
