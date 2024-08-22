package com.brixton.sodimac.model.sale.confirmed;


import com.brixton.sodimac.model.client.LegalPerson;

import com.brixton.sodimac.model.sale.proforma.SaleDetail;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
public class Bill extends CompanyData{
    private long idBill;
    private LegalPerson client;
    private List<SaleDetail> detailSales;
    private double total;
    private double igv;
    private double subTotal;
}
