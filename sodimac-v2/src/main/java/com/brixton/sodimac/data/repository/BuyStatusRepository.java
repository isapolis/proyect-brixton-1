package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.compras.BuyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyStatusRepository extends JpaRepository<BuyStatus, Byte> {

    Optional<BuyStatus> findByName(String name);


}
