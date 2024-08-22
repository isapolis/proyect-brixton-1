package com.brixton.sodimac.data.entity;

import com.brixton.sodimac.data.enums.RegistryStateType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class Audit {
    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "updatedat")
    private LocalDateTime updatedAt;

    @Column(name = "createdby", length = 20)
    private String createdBy;

    @Column(name = "updatedby", length = 20)
    private String updatedBy;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "registry_state")
    private RegistryStateType registryState;

}
