package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.compras.OrderBuy;
import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.dto.response.OrderBuyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderBuyMapper {
    OrderBuyMapper INSTANCE = Mappers.getMapper(OrderBuyMapper.class);
    @Mapping(source = "idSupplier", target = "supplier.ruc")
    @Mapping(source = "idRequestBuy", target = "requestBuy.id")
    OrderBuy orderBuyRequestDTOToOrderBuy(OrderBuyRequestDTO orderBuyRequestDTO);
    @Mapping(source = "supplier.ruc", target = "idSupplier")
    @Mapping(source = "requestBuy.id", target = "idRequestBuy")
    @Mapping(source = "employee.id", target = "idEmployee")
    @Mapping(source = "dateStatus", target = "dateStatus", dateFormat = "yyyy/MM/dd")
    @Mapping(source = "statusOrder.description", target = "orderStatus")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    OrderBuyResponseDTO orderBuyToOrderBuyResponseDTO(OrderBuy orderBuy);

}
