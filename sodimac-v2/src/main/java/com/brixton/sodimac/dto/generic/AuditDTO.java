package com.brixton.sodimac.dto.generic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditDTO {

    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
    private String status;

}
