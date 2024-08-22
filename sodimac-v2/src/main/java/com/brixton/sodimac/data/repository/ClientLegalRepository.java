package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.client.ClientLegal;
import com.brixton.sodimac.data.enums.RegistryStateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientLegalRepository extends JpaRepository<ClientLegal, String> {

    List<ClientLegal> findByRegistryState(RegistryStateType registryState);

}
