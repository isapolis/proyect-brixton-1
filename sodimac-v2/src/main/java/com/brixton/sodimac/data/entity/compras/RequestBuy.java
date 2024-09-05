package com.brixton.sodimac.data.entity.compras;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "buy")
@Getter
@Setter
public class RequestBuy extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "buystatus_id", foreignKey = @ForeignKey(name = "FK_buystatus"))
    private BuyStatus buyStatus;
    @ManyToOne
    @JoinColumn(name = "employee_assigned_id",foreignKey = @ForeignKey(name = "FK_employee_assigned_id"))
    private Employee employeeAssigned;
    @ManyToOne
    @JoinColumn(name = "employee_requester_id",foreignKey = @ForeignKey(name = "FK_employee_requester_id"))
    private Employee employeeRequester;
    @Column(length = 100, name = "reason_status")
    private String reasonStatus;
    @OneToMany(mappedBy = "requestBuy")
    private List<ProductToBuy> productToBuys = new ArrayList<>();

}
