package com.brixton.sodimac.model.client;

import com.brixton.sodimac.model.audit.ObjectAudit;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Person extends ObjectAudit {

    private long id;
    private String address;
    private String phone;
    private String email;
    private TypePerson typeClient;
}
