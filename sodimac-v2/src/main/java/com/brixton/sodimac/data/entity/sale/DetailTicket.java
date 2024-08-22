package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detailticket")
@Getter
@Setter
public class DetailTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "FK_product_detail_client"))
    private Product product;

    private double salePrice;

    private double quantity;

    private double total;

    @ManyToOne
    @JoinColumn(name = "ticket_id",foreignKey = @ForeignKey(name = "FK_client"))
    private Ticket ticket;

}
