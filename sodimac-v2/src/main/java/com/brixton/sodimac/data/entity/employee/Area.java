package com.brixton.sodimac.data.entity.employee;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "area")
@Getter
@Setter
public class Area extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(length = 20)
    private String name;

}
