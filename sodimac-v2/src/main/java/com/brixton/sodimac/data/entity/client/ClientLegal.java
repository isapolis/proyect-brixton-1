package com.brixton.sodimac.data.entity.client;

import com.brixton.sodimac.data.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "legal_client")
@Getter
@Setter
public class ClientLegal extends Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 11)
    private String ruc;

    @Column(length = 50)
    private String razonSocial;

    private byte supplier;


}
