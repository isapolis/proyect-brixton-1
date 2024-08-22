package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.client.ClientLegal;
import com.brixton.sodimac.dto.request.management.ClientLegalRequestDTO;
import com.brixton.sodimac.dto.request.management.UpdateClientLegalRequestDTO;
import com.brixton.sodimac.dto.response.management.ClientLegalResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientLegalMapper {
    ClientLegalMapper INSTANCE = Mappers.getMapper(ClientLegalMapper.class);
    @Mapping(source = "ruc", target = "ruc")
    @Mapping(source = "razonSocial", target = "razonSocial")
    @Mapping(source = "supplier", target = "supplier")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    ClientLegal clientLegalRequestDTOToClientLegal(ClientLegalRequestDTO clientLegalRequestDTO);

    @Mapping(source = "ruc", target = "ruc")
    @Mapping(source = "razonSocial", target = "razonSocial")
    @Mapping(source = "supplier", target = "supplier")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    ClientLegalResponseDTO clientLegalToClientLegalResponseDTO(ClientLegal clientLegal);

    @Mapping(source = "razonSocial", target = "razonSocial")
    @Mapping(source = "supplier", target = "supplier")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "email", target = "email")
    ClientLegal UpdateClientLegalRequestDTOToClientLegal(UpdateClientLegalRequestDTO updateClientLegalRequestDTO);

}
