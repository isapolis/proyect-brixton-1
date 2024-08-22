package com.brixton.sodimac.data.entity.employee;

import com.brixton.sodimac.data.entity.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil")
@Getter
@Setter
public class Perfil extends Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "perfil")
    private Set<EmployeeXPerfil> employeeXPerfils = new HashSet<>();

}
