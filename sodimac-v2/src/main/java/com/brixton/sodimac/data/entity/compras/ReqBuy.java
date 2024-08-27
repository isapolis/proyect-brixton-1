package com.brixton.sodimac.data.entity.compras;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.entity.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "buy")
@Getter
@Setter
public class ReqBuy extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "buystatus_id", foreignKey = @ForeignKey(name = "FK_buystatus"))
    private BuyStatus buyStatus;
    @ManyToOne
    @JoinColumn(name = "buyemployee_id",foreignKey = @ForeignKey(name = "FK_buyemployee"))
    private Employee buyEmployeeId;
    @ManyToOne
    @JoinColumn(name = "creatoremployee_id",foreignKey = @ForeignKey(name = "FK_creatoremployee"))
    private Employee logisticEmployeeId;
    @Column(length = 50, name = "motivostatus")
    private String motivoStatus;
//    @Transient
//    private long employeeId;
    //private List<ProductToBuy> productToBuys;
    //private long employeeLogisticId;
}
