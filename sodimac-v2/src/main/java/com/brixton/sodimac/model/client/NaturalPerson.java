package com.brixton.sodimac.model.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaturalPerson extends Person {

    private String name;
    private String lastName;
    private TypeDocument document;
    private String number;

}
