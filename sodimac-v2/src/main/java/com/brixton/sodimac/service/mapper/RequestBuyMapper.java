package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.client.ClientNatural;
import com.brixton.sodimac.data.entity.compras.ProductToBuy;
import com.brixton.sodimac.data.entity.compras.RequestBuy;
import com.brixton.sodimac.dto.request.compras.ProductToBuyRequestDTO;
import com.brixton.sodimac.dto.request.compras.ReqBuyRequestDTO;
import com.brixton.sodimac.dto.response.compras.ProductToBuyResponseDTO;
import com.brixton.sodimac.dto.response.compras.ReqBuyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RequestBuyMapper {
    RequestBuyMapper INSTANCE = Mappers.getMapper(RequestBuyMapper.class);
    @Mapping(source = "employeeRequesterId",target = "employeeRequester.id")
    @Mapping(source = "buyStatus", target = "buyStatus.id")
    RequestBuy reqBuyRequestDTOToRequestBuy(ReqBuyRequestDTO reqBuyRequestDTO);

    @Mapping(source = "idProduct", target = "product.id")
    ProductToBuy productToBuyRequestDTOToProductToBuy(ProductToBuyRequestDTO productToBuyRequestDTO);

    @Mapping(source = "employeeRequester.id",target = "employeeRequesterId")
    @Mapping(source = "employeeAssigned.id",target = "employeeAssignedId")
    @Mapping(source = "buyStatus.name", target = "buyStatus")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    ReqBuyResponseDTO requestBuyToRequestBuyResponseDTO(RequestBuy requestBuy);

    @Mapping(source = "product.name", target = "nameProduct")
    ProductToBuyResponseDTO productToBuyToProductToBuyResponseDTO(ProductToBuy productToBuy);

}
