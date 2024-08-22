package com.brixton.sodimac.dto.request;

import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import lombok.Getter;


@Getter
public class BillRequestDTO{

    private long id;
    private ClientLegalRequestDTO client;
    private long proformaId;

}
