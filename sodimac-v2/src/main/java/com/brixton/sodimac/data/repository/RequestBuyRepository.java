package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.compras.BuyStatus;
import com.brixton.sodimac.data.entity.compras.RequestBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestBuyRepository extends JpaRepository<RequestBuy, Long> {
    @Query(value = "SELECT R FROM RequestBuy R JOIN FETCH  R.productToBuys WHERE R.buyStatus = :buystatus")
    List<RequestBuy> findByBuyStatus(@Param("buystatus") BuyStatus buyStatus);

}
