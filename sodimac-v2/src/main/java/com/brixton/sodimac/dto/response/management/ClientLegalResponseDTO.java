package com.brixton.sodimac.dto.response.management;

import com.brixton.sodimac.dto.response.generic.AuditResponseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLegalResponseDTO extends AuditResponseDTO {

    private String ruc;
    private String razonSocial;
    private byte supplier;
    private String address;
    private String phone;
    private String email;
}
