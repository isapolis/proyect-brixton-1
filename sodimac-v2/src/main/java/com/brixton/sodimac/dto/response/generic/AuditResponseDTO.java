package com.brixton.sodimac.dto.response.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AuditResponseDTO {
    private String creadoEn;
    private String creadoPor;
    private String actualizadoEn;
    private String actualizadoPor;
}
