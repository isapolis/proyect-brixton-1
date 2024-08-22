package com.brixton.sodimac.data.entity.logistic;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.compras.OrderBuy;
import com.brixton.sodimac.data.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Income extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "incomestatus_id", foreignKey = @ForeignKey(name = "FK_statuslogistic_income"))
    private StatusLogistic incomeStatus;
    @ManyToOne
    @JoinColumn(name = "orderbuy_id", foreignKey = @ForeignKey(name = "FK_income_order"))
    private OrderBuy orderBuy;
    @ManyToOne
    @JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name = "FK_employee_income"))
    private Employee employee;

}
