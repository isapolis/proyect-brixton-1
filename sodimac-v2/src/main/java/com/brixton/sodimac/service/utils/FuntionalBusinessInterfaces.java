package com.brixton.sodimac.service.utils;

import com.brixton.sodimac.data.entity.Audit;
import com.brixton.sodimac.data.enums.RegistryStateType;

import java.time.LocalDateTime;
import java.util.function.Consumer;

public class FuntionalBusinessInterfaces {
    public static Consumer<Audit> auditCreation = input->{
        input.setCreatedAt(LocalDateTime.now());
        input.setCreatedBy(Constants.USER_APP);
        input.setRegistryState(RegistryStateType.ACTIVE);
    };
    public static Consumer<Audit> auditUpdate = input ->{
        input.setUpdatedBy(Constants.USER_APP);
        input.setUpdatedAt(LocalDateTime.now());
    };


//    public static Consumer<DataBusinessDTO> business = input->{
//        input.setRazonSocialBusiness(Constantes.RAZON_SOCIAL);
//        input.setAddressBusiness(Constantes.ADDRESS);
//        input.setRucBusiness(Constantes.RUC);
//    };
}
