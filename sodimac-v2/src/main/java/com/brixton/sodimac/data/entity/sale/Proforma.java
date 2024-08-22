package com.brixton.sodimac.data.entity.sale;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Proforma extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_employee_proforma"))
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "statusproforma_id",foreignKey = @ForeignKey(name = "FK_statussale"))
    private StatusSale statusProforma;

}
