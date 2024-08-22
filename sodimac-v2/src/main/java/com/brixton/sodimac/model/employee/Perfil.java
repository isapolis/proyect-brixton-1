package com.brixton.sodimac.model.employee;

public enum Perfil {
    ADMIN(1),
    SUPERVISOR(2),
    OPERATIONAL(3);


    private Integer rol;

    Perfil(Integer rol) {
        this.rol = rol;
    }

   public Integer getRol() {
        return this.rol;
    }

}
