package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.client.ClientNatural;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientNaturalRequestDTO;
import com.brixton.sodimac.dto.response.management.ClientNaturalResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientNaturalMapper {
    ClientNaturalMapper INSTANCE = Mappers.getMapper(ClientNaturalMapper.class);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "document", target = "typeDocument.id")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    ClientNatural clientNaturalRequestDTOToClientNatural(ClientNaturalRequestDTO clientNaturalRequestDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "typeDocument.nameDocument", target = "document")
    @Mapping(source = "number", target = "number")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    ClientNaturalResponseDTO clientNaturalToClientNaturalResponseDTO(ClientNatural clientNatural);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "document", target = "typeDocument.id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    ClientNatural updateClientNaturalRequestDTOToClientNatural(UpdateClientNaturalRequestDTO updateClientNaturalRequestDTO);

}
