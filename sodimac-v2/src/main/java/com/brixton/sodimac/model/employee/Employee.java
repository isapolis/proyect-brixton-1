package com.brixton.sodimac.model.employee;

import com.brixton.sodimac.model.client.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Employee extends Person {

    private String name;
    private String lastName;
    private Area area;
    private Perfil perfil;

}
