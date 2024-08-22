package com.brixton.sodimac.model.client;

public enum TypeDocument {
    CARNET_DE_EXTRANJERIA(1),
    DNI(2),
    PASSAPORTE(3);


    private Integer typeDocument;

    TypeDocument(Integer typeDocument) {
        this.typeDocument = typeDocument;
    }

    Integer getTypeDocument() {
        return this.typeDocument;
    }
}
