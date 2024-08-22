package com.brixton.sodimac.data.entity.compras;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buystatus")
@Getter
@Setter
public class BuyStatus extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "buystatus",length = 20)
    private String buyStatus;

}
