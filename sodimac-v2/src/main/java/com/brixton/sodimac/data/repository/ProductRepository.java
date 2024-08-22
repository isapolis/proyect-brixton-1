package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByRegistryEstate(RegistryStateType registryStateType);

}
