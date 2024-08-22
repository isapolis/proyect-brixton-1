package com.brixton.sodimac.data.entity.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employeexperfil")
@Getter
@Setter
public class EmployeeXPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @ManyToOne
    @JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name = "FK_EMPLOYEE"))
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "perfil_id",foreignKey = @ForeignKey(name = "FK_PERFIL"))
    private Perfil perfil;
}
