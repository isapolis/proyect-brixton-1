package com.brixton.sodimac.data.entity.client;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "typedocument")
@Getter
@Setter
@ToString
public class TypeDocument extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(length = 20,name = "document")
    private String nameDocument;

}
