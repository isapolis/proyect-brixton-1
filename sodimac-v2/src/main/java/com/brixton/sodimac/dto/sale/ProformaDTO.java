package com.brixton.sodimac.dto.sale;

import com.brixton.sodimac.dto.generic.AuditDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProformaDTO extends AuditDTO {
    private long id;
    private long employeeId;
    private String statusProforma;
    private double total;
    @JsonIgnore
    private String expirationDate;
    private List<SaleDetailDTO> details;

}
