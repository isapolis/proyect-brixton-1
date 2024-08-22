package com.brixton.sodimac.model.client;

public enum TypePerson {
    NATURAL_PERSON(1),
    LEGAL_PERSON(2);

    private Integer type;

    TypePerson(Integer type) {
        this.type = type;
    }

    Integer getType() {
        return this.type;
    }
}
