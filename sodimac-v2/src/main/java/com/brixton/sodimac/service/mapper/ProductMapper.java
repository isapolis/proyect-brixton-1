package com.brixton.sodimac.service.mapper;

import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.dto.request.management.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.management.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "category",target = "category.id")
    @Mapping(source = "codeProduct",target = "codeProduct")
    Product createProductRequestDTOToProduct(CreateProductRequestDTO createProductRequestDTO);
    @Mapping(source = "category.name",target = "category")
    @Mapping(source = "createdAt", target = "creadoEn", dateFormat = "yyyy/MM/dd HH:mm")
    @Mapping(source = "createdBy", target = "creadoPor")
    @Mapping(source = "updatedAt", target = "actualizadoEn", dateFormat = "yyyy/MM/dd HH:mm:ss")
    @Mapping(source = "updatedBy", target = "actualizadoPor")
    ProductResponseDTO productToProductResponseDTO(Product product);



}
