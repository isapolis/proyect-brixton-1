package com.brixton.sodimac.data.entity.employee;

import com.brixton.sodimac.data.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50,name = "lastname")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "area_id", foreignKey = @ForeignKey(name = "FK_area"))
    private Area area;

//    @OneToMany(mappedBy = "employee")
//    private Set<EmployeeXPerfil> employeeXPerfils = new HashSet<>();

    @Transient
    private int profileId;

}
