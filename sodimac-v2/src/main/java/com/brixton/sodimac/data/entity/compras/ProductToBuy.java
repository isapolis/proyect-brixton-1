package com.brixton.sodimac.data.entity.compras;

import com.brixton.sodimac.data.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "producttobuy")
@Getter
@Setter
public class ProductToBuy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_buy"))
    private Product product;
    @Column(name = "requiredquantity")
    private double requiredQuantity;
    @Column(name = "quantitystock")
    private double quantityStock;
    @Column(name = "quantityintransit")
    private double quantityInTransit;
    @Column(name = "expectedamount")
    private double expectedAmount;
    @ManyToOne
    @JoinColumn(name = "buy_id", foreignKey = @ForeignKey(name = "FK_buy"))
    private ReqBuy reqBuy;
//    @Column(name = "nameproduct", length = 30)
//    private String name;

}
