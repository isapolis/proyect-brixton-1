package com.brixton.sodimac.controller;

import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientNaturalRequestDTO;
import com.brixton.sodimac.dto.response.management.ClientLegalResponseDTO;
import com.brixton.sodimac.dto.response.management.ClientNaturalResponseDTO;
import com.brixton.sodimac.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/management/clients")
@Slf4j
public class ClientController {

    @Autowired
    private ClientService clientService;
    @PostMapping("/legal")
    public ResponseEntity<ClientLegalResponseDTO> createClientLegal(@Valid @RequestBody ClientLegalRequestDTO clientLegalRequestDTO){
        ClientLegalResponseDTO clientLegal = clientService.createClientLegal(clientLegalRequestDTO);
        return ResponseEntity.ok(clientLegal);
    }

    @PostMapping("/listLegal")
    public ResponseEntity<List<ClientLegalResponseDTO>> createWithListLegal(@Valid @RequestBody List<ClientLegalRequestDTO> inputClients){
        List<ClientLegalResponseDTO> inputs = clientService.createWithListLegal(inputClients);
        return ResponseEntity.ok(inputs);
    }

    @GetMapping("/legal/{id}")
    public ResponseEntity<ClientLegalResponseDTO> getClientLegal(@PathVariable String id){
        return ResponseEntity.ok(clientService.getClientLegal(id));
    }

    @GetMapping("/listLegal")
    public ResponseEntity<List<ClientLegalResponseDTO>> listClientLegal(){
        return new ResponseEntity<>(clientService.getListClientLegal(), HttpStatus.OK);
    }

    @PutMapping("/legal/{id}")
    public ResponseEntity<ClientLegalResponseDTO> updateClientLegal(@Valid @PathVariable String id, @RequestBody UpdateClientLegalRequestDTO clientToUpdate){
        return ResponseEntity.ok(clientService.updateClientLegal(id,clientToUpdate));
    }

    @DeleteMapping("/legal/delete/{id}")
    public ResponseEntity<?> deleteClientLegal(@Valid @PathVariable String id){
        clientService.deleteClientLegal(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //CLIENT NATURAL
    @PostMapping("/natural")
    public ResponseEntity<ClientNaturalResponseDTO> createClientNatural(@Valid @RequestBody ClientNaturalRequestDTO clientNaturalRequestDTO){
        ClientNaturalResponseDTO clientNatural = clientService.createClientNatural(clientNaturalRequestDTO);
        return ResponseEntity.ok(clientNatural);
    }

    @PostMapping("/listnatural")
    public ResponseEntity<List<ClientNaturalResponseDTO>> createWithListNatural(@Valid @RequestBody List<ClientNaturalRequestDTO> inputClients){
        List<ClientNaturalResponseDTO> inputs = clientService.createWithListNatural(inputClients);
        return ResponseEntity.ok(inputs);
    }

    @GetMapping("/natural/{id}")
    public ResponseEntity<ClientNaturalResponseDTO> getClientNatural(@PathVariable String id){
        return ResponseEntity.ok(clientService.getClientNatural(id));
    }

    @GetMapping("/listNatural")
    public ResponseEntity<List<ClientNaturalResponseDTO>> listClientNatural(){
        return new ResponseEntity<>(clientService.getListClientNatural(), HttpStatus.OK);
    }

    @PutMapping("/natural/{id}")
    public ResponseEntity<ClientNaturalResponseDTO> updateClientNatural(@Valid @PathVariable String id, @RequestBody UpdateClientNaturalRequestDTO clientToUpdate){
        return ResponseEntity.ok(clientService.updateClientNatural(id,clientToUpdate));
    }

    @DeleteMapping("/natural/delete/{id}")
    public ResponseEntity<?> deleteClientNatural(@Valid @PathVariable String id){
        clientService.deleteClientNatural(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
