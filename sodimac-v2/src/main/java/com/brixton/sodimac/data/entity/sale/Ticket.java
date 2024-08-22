package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.client.ClientNatural;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "naturalclient_document", foreignKey = @ForeignKey(name = "FK_naturalclient"))
    private ClientNatural clientNatural;
    @ManyToOne
    @JoinColumn(name = "proforma_id", foreignKey = @ForeignKey(name = "FK_ticket_proforma"))
    private Proforma proforma;

}
