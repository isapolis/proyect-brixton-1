package com.brixton.sodimac.data.entity.compras;

import com.brixton.sodimac.data.entity.logistic.StatusLogistic;
import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.client.ClientLegal;
import com.brixton.sodimac.data.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderbuy")
@Getter
@Setter
public class OrderBuy extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "statusorder_id", foreignKey = @ForeignKey(name = "FK_statuslogistic"))
    private StatusLogistic statusOrder;
    @ManyToOne
    @JoinColumn(name = "supplier_id",foreignKey = @ForeignKey(name = "FK_legal"))
    private ClientLegal supplier;
    @ManyToOne
    @JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name = "FK_employee_orderbuy"))
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "buy_id",foreignKey = @ForeignKey(name = "FK_buy_order"))
    private ReqBuy reqBuy;

}
