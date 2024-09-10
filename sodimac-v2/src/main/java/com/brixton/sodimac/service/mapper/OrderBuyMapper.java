package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.client.ClientNatural;
import com.brixton.sodimac.data.entity.compras.OrderBuy;
import com.brixton.sodimac.dto.request.OrderBuyRequestDTO;
import com.brixton.sodimac.dto.request.management.ClientNaturalRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrederBuyMapper {
    OrederBuyMapper INSTANCE = Mappers.getMapper(OrederBuyMapper.class);
    @Mapping(source = "idSupplier", target = "supplier.id")
    @Mapping(source = "idRequestBuy", target = "requestBuy.id")
    OrderBuy orderBuyRequestDTOTOOrderBuy(OrderBuyRequestDTO orderBuyRequestDTO);


}
