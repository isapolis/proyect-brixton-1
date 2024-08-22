package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.client.ClientLegal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bill extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "legalclient_ruc", foreignKey = @ForeignKey(name = "FK_bill"))
    private ClientLegal clientLegal;
    @ManyToOne
    @JoinColumn(name = "proforma_id",foreignKey = @ForeignKey(name = "FK_bill_proforma"))
    private Proforma proforma;
    @Column(name = "subtotal")
    private double subTotal;
    private double total;
    private double igv;

}
