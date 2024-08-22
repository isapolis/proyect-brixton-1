package com.brixton.sodimac.data.enums;

public enum RegistryStateType {

   INACTIVE(0),
    ACTIVE(1);

    private final int valor;
    RegistryStateType(int value){
        this.valor=value;
    }

    public int getValor(){
        return valor;
    }

    public static RegistryStateType fromValue(int value) {
        for (RegistryStateType state : values()) {
            if (state.getValor() == value) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid value for RegistryStateType: " + value);
    }
}
