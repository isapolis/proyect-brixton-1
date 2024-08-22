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
public class RequestBuy extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //private long employeeId;
    @ManyToOne
    @JoinColumn(name = "buystatus_id", foreignKey = @ForeignKey(name = "FK_buystatus"))
    private BuyStatus buyStatus;

    //private LocalDate dateStatus;
    @Column(length = 50, name = "motivostatus")
    private String motivoStatus;

    @ManyToOne
    @JoinColumn(name = "buyemployee_id",foreignKey = @ForeignKey(name = "FK_buyemployee"))
    private Employee buyEmployeeId;

    //private List<ProductToBuy> productToBuys;
}
