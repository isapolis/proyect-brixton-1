package com.brixton.sodimac.dto.response;

import com.brixton.sodimac.dto.generic.AuditDTO;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import com.brixton.sodimac.dto.sale.SaleDetailDTO;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
@Getter
@Setter
public class TicketResponseDTO extends AuditDTO {
    private long idTicket;
    private String razonSocial;
    private String ruc;
    private String address;
    private ClientNaturalRequestDTO client;
    private List<SaleDetailDTO> detailSales;
    private double total;
}
