package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.client.ClientNatural;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientNaturalRepository extends JpaRepository<ClientNatural,String> {

    List<ClientNatural> findByRegistryState(RegistryStateType registryState);
}
