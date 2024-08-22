package com.brixton.sodimac.data.entity.logistic;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "statuslogistic")
@Getter
@Setter
public class StatusLogistic extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String description;
    @Column(name = "statuslogistic",length = 20)
    private String statusLogistic;

}
