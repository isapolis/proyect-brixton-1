package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientNaturalRequestDTO;
import com.brixton.sodimac.dto.response.management.ClientLegalResponseDTO;
import com.brixton.sodimac.dto.response.management.ClientNaturalResponseDTO;

import java.util.List;

public interface ClientService {
    ClientLegalResponseDTO createClientLegal(ClientLegalRequestDTO clientLegalRequestDTO);

    List<ClientLegalResponseDTO> createWithListLegal(List<ClientLegalRequestDTO> inputClients);

    ClientLegalResponseDTO getClientLegal(String id);

    List<ClientLegalResponseDTO> getListClientLegal();

    ClientLegalResponseDTO updateClientLegal(String id, UpdateClientLegalRequestDTO clientToUpdate);

    void deleteClientLegal(String id);

    //CLIENT NATURAL
    ClientNaturalResponseDTO createClientNatural(ClientNaturalRequestDTO clientNaturalRequestDTO);

    List<ClientNaturalResponseDTO> createWithListNatural(List<ClientNaturalRequestDTO> inputClients);

    ClientNaturalResponseDTO getClientNatural(String id);

    List<ClientNaturalResponseDTO> getListClientNatural();

    ClientNaturalResponseDTO updateClientNatural(String id, UpdateClientNaturalRequestDTO clientToUpdate);

    void deleteClientNatural(String id);


//    LegalPerson getClientByRuc(String rucClient);
//    LegalPerson createClientLegal(LegalPerson legalClient);
//    LegalPerson findClientLegal(long idClient);
//    NaturalPerson findClientNatural(long idClient);
//    NaturalPerson getClientByDocument(String documentNumber);
//    NaturalPerson createClientNatural(NaturalPerson naturalClient);


}
