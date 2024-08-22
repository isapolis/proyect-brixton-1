package com.brixton.sodimac.dto.request;

import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import lombok.Getter;

@Getter
public class TicketRequestDTO  {
    private long id;
    private ClientNaturalRequestDTO client;
    private long proformaId;
}
