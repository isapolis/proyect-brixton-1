package com.brixton.sodimac.model.sale.confirmed;

import com.brixton.sodimac.model.audit.ObjectAudit;
import com.brixton.sodimac.model.constants.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyData  extends ObjectAudit {

    public CompanyData(){
        setRazonSocial(Message.RAZON_SOCIAL);
        setRuc(Message.RUC);
        setAddress(Message.ADDRESS);
    }

    private String razonSocial;
    private String ruc;
    private String address;
}
