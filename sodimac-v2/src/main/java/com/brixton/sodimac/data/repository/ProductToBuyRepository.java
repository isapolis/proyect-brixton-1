package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.compras.ProductToBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductToBuyRepository extends JpaRepository<ProductToBuy,Long> {

    // Método para actualizar la cantidad en tránsito
    @Modifying
    @Query("UPDATE ProductToBuy p SET p.quantityInTransit = :quantity WHERE p.idProduct = :idProduct")
    void updateQuantityInTransit(@Param("idProduct") Long idProduct, @Param("quantity") double quantity);


}
