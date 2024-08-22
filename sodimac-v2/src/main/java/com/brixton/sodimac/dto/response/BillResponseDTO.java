package com.brixton.sodimac.dto.response;

import com.brixton.sodimac.dto.generic.AuditDTO;
import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import com.brixton.sodimac.dto.sale.SaleDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BillResponseDTO extends AuditDTO{
    private long idBill;
    private String razonSocial;
    private String ruc;
    private String address;
    private ClientLegalRequestDTO client;
    private List<SaleDetailDTO> detailSales;
    private double total;
    private double subTotal;
    private double igv;
}
