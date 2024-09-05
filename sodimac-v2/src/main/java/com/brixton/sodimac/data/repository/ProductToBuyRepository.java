package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.compras.ProductToBuy;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductToBuyRepository extends JpaRepository<ProductToBuy,Long> {
    //List<ProductToBuy> findByRegistryState(RegistryStateType registryStateType);


}
