package com.brixton.sodimac.service;

import com.brixton.sodimac.controller.manageexception.GenericNotFoundException;
import com.brixton.sodimac.data.entity.client.ClientLegal;
import com.brixton.sodimac.data.entity.client.ClientNatural;
import com.brixton.sodimac.data.entity.client.TypeDocument;
import com.brixton.sodimac.data.enums.RegistryStateType;
import com.brixton.sodimac.data.repository.ClientLegalRepository;
import com.brixton.sodimac.data.repository.ClientNaturalRepository;
import com.brixton.sodimac.data.repository.TypeDocumentRepository;
import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientNaturalRequestDTO;
import com.brixton.sodimac.dto.response.management.ClientLegalResponseDTO;
import com.brixton.sodimac.dto.response.management.ClientNaturalResponseDTO;
import com.brixton.sodimac.service.mapper.ClientLegalMapper;
import com.brixton.sodimac.service.mapper.ClientNaturalMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@ToString
public class ClientServiceImpl implements ClientService{
    private static final String USER_APP = "BRIXTON";

    @Autowired
    private ClientLegalRepository clientLegalRepository;
    @Autowired
    private ClientNaturalRepository clientNaturalRepository;
    @Autowired
    private TypeDocumentRepository typeDocumentRepository;

    @Override
    public ClientLegalResponseDTO createClientLegal(ClientLegalRequestDTO clientLegalRequestDTO){
        ClientLegal clientLegal = ClientLegalMapper.INSTANCE.clientLegalRequestDTOToClientLegal(clientLegalRequestDTO);
        clientLegal.setCreatedAt(LocalDateTime.now());
        clientLegal.setCreatedBy(USER_APP);
        clientLegal.setRegistryState(RegistryStateType.ACTIVE);
        clientLegalRepository.save(clientLegal);
        ClientLegalResponseDTO clientLegalResponseDTO = ClientLegalMapper.INSTANCE.clientLegalToClientLegalResponseDTO(clientLegal);
        log.info("ClientLegalResponseDTO: {}", clientLegalResponseDTO);
        return clientLegalResponseDTO;
    }

    @Override
    public List<ClientLegalResponseDTO> createWithListLegal(List<ClientLegalRequestDTO> inputClients){
        List<ClientLegalResponseDTO> clients = new ArrayList<>();
        for (ClientLegalRequestDTO clientLegalRequestDTO:inputClients){
            try {
                clients.add(createClientLegal(clientLegalRequestDTO));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return clients;
    }
    @Override
    public ClientLegalResponseDTO getClientLegal(String id){
        ClientLegal clientLegal = clientLegalRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Cliente Jurídico no registrado"));
        return ClientLegalMapper.INSTANCE.clientLegalToClientLegalResponseDTO(clientLegal);
    }
    @Override
    public List<ClientLegalResponseDTO> getListClientLegal(){
        List<ClientLegalResponseDTO> activeLegalClients = new ArrayList<>();
        List<ClientLegal> clientsFound = clientLegalRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for (ClientLegal client:clientsFound){
            activeLegalClients.add(ClientLegalMapper.INSTANCE.clientLegalToClientLegalResponseDTO(client));
        }
        return activeLegalClients;
    }
    @Override
    public ClientLegalResponseDTO updateClientLegal(String id, UpdateClientLegalRequestDTO clientToUpdate){
        ClientLegal original= clientLegalRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Cliente Jurídico no registrado"));
        ClientLegal clientTemp=ClientLegalMapper.INSTANCE.UpdateClientLegalRequestDTOToClientLegal(clientToUpdate);
        original.setUpdatedAt(LocalDateTime.now());
        original.setUpdatedBy(USER_APP);
        original.setRazonSocial(clientTemp.getRazonSocial());
        original.setSupplier(clientTemp.getSupplier());
        original.setAddress(clientTemp.getAddress());
        original.setEmail(clientTemp.getEmail());
        original.setPhone(clientTemp.getPhone());
        clientLegalRepository.save(original);
        return ClientLegalMapper.INSTANCE.clientLegalToClientLegalResponseDTO(original);

    }
    @Override
    public void deleteClientLegal(String id){
        ClientLegal clientLegal = clientLegalRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Cliente Jurídico no registrado"));
        clientLegal.setRegistryState(RegistryStateType.INACTIVE);
        clientLegal.setUpdatedAt(LocalDateTime.now());
        clientLegal.setUpdatedBy(USER_APP);
        clientLegalRepository.save(clientLegal);

    }
    //CLIENT NATURAL
    @Override
    public ClientNaturalResponseDTO createClientNatural(ClientNaturalRequestDTO clientNaturalRequestDTO){
        ClientNatural clientNatural = ClientNaturalMapper.INSTANCE.clientNaturalRequestDTOToClientNatural(clientNaturalRequestDTO);
        clientNatural.setCreatedAt(LocalDateTime.now());
        clientNatural.setCreatedBy(USER_APP);
        clientNatural.setRegistryState(RegistryStateType.ACTIVE);
        log.info(clientNatural.toString());
        TypeDocument typeDocument= typeDocumentRepository.findById(clientNatural.getTypeDocument().getId()).orElseThrow(() -> new GenericNotFoundException("Tipo de documento con ID inexistente"));
        log.info(typeDocument.toString());
        clientNatural.getTypeDocument().setNameDocument(typeDocument.getNameDocument());
        clientNatural.setTypeDocument(typeDocument);
        log.info("Client::: {}", clientNatural);
        clientNaturalRepository.save(clientNatural);
        ClientNaturalResponseDTO clientNaturalResponseDTO = ClientNaturalMapper.INSTANCE.clientNaturalToClientNaturalResponseDTO(clientNatural);
        log.info("ClientNaturalResponseDTO: {}", clientNaturalResponseDTO);
        return clientNaturalResponseDTO;
    }
    @Override
    public List<ClientNaturalResponseDTO> createWithListNatural(List<ClientNaturalRequestDTO> inputClients){
        List<ClientNaturalResponseDTO> clients = new ArrayList<>();
        for (ClientNaturalRequestDTO clientNaturalRequestDTO:inputClients){
            try {
                clients.add(createClientNatural(clientNaturalRequestDTO));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return clients;
    }
    @Override
    public ClientNaturalResponseDTO getClientNatural(String id){
        ClientNatural clientNatural = clientNaturalRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Cliente Natural no registrado"));
        return ClientNaturalMapper.INSTANCE.clientNaturalToClientNaturalResponseDTO(clientNatural);
    }
    @Override
    public List<ClientNaturalResponseDTO> getListClientNatural(){
        List<ClientNaturalResponseDTO> activeNaturalClients = new ArrayList<>();
        List<ClientNatural> clientsFound = clientNaturalRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for (ClientNatural client : clientsFound){
            activeNaturalClients.add(ClientNaturalMapper.INSTANCE.clientNaturalToClientNaturalResponseDTO(client));
        }
        return activeNaturalClients;
    }
    @Override
    public ClientNaturalResponseDTO updateClientNatural(String id, UpdateClientNaturalRequestDTO clientToUpdate){
        ClientNatural original= clientNaturalRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Cliente Natural no registrado"));
        ClientNatural clientTemp=ClientNaturalMapper.INSTANCE.updateClientNaturalRequestDTOToClientNatural(clientToUpdate);
        original.setUpdatedAt(LocalDateTime.now());
        original.setUpdatedBy(USER_APP);
        original.setName(clientTemp.getName());
        original.setLastName(clientTemp.getLastName());
        TypeDocument typeDocument= typeDocumentRepository.findById(clientTemp.getTypeDocument().getId()).orElseThrow(() -> new GenericNotFoundException("Tipo de documento con ID inexistente"));
        clientTemp.getTypeDocument().setNameDocument(typeDocument.getNameDocument());
        original.setTypeDocument(clientTemp.getTypeDocument());
        original.setAddress(clientTemp.getAddress());
        original.setEmail(clientTemp.getEmail());
        original.setPhone(clientTemp.getPhone());
        clientNaturalRepository.save(original);
        return ClientNaturalMapper.INSTANCE.clientNaturalToClientNaturalResponseDTO(original);
    }
    @Override
    public void deleteClientNatural(String id){
        ClientNatural clientNatural = clientNaturalRepository.findById(id).orElseThrow(() -> new GenericNotFoundException("Cliente Natural no registrado"));
        clientNatural.setRegistryState(RegistryStateType.INACTIVE);
        clientNatural.setUpdatedAt(LocalDateTime.now());
        clientNatural.setUpdatedBy(USER_APP);
        clientNaturalRepository.save(clientNatural);
    }

/*
    @Override
    public LegalPerson getClientByRuc(String rucClient) {
        for (LegalPerson client : legalPersons.values()) {
            if (client != null ) {
                if (client.getRuc().equals(rucClient)) {
                    return client;
                }
            }
        }
        return null;
    }


    @Override
    public LegalPerson findClientLegal(long idClient) {
        return legalPersons.get(idClient);

    }

    @Override
    public NaturalPerson findClientNatural(long idClient) {
        return naturalPersons.get(idClient);
    }

    @Override
    public NaturalPerson getClientByDocument(String documentNumber) {
        for (NaturalPerson client : naturalPersons.values()){
            if(client != null && client.getStatus() == TypeStatusForAudit.ACTIVE ){
                if(client.getNumber().equals(documentNumber)){
                    return client;
                }
            }
        }
        return null;
    }

    @Override
    public NaturalPerson createClientNatural(NaturalPerson naturalClient) {
        naturalClient.setId(UUID.randomUUID().getMostSignificantBits());
        naturalClient.setTypeClient(TypePerson.NATURAL_PERSON);
        naturalClient.setCreatedAt(LocalDateTime.now());
        naturalClient.setCreatedBy(USER_APP);
        naturalClient.setStatus(TypeStatusForAudit.ACTIVE);
        naturalPersons.put(naturalClient.getId(), naturalClient);
        return naturalClient;
    }

*/

}
