package com.brixton.sodimac.model.sale.confirmed;



import com.brixton.sodimac.model.client.NaturalPerson;
import com.brixton.sodimac.model.sale.proforma.SaleDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Ticket extends CompanyData{

    private long idTicket;
    private NaturalPerson client;
    private List<SaleDetail> detailSales;
    private double total;

}
