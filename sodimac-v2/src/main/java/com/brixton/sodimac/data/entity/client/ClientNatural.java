package com.brixton.sodimac.data.entity.client;

import com.brixton.sodimac.data.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "natural_client")
@Getter
@Setter
@ToString(callSuper = true)
public class ClientNatural extends Person {

    @Column(length = 50)
    private String name;

    @Column(name = "lastname", length = 50)
    private String lastName;

    //@Transient
    @ManyToOne
    @JoinColumn(name = "typedocument_id", foreignKey = @ForeignKey(name = "FK_typedocument"))
    private TypeDocument typeDocument;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "documentnumber", length = 9)
    private String number;
}
