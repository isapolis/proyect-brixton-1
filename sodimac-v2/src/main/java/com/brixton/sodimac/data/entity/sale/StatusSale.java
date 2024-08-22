package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "statussale")
@Getter
@Setter
public class StatusSale extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    @Column(length = 20)
    private String description;
    @Column(length = 20,name = "statussale")
    private String statusSale;

}
