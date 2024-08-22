package com.brixton.sodimac.service;

import com.brixton.sodimac.dto.request.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO);

//    Object createProduct(ProductRequestDTO inputProduct);
//    List<Object> createWithList(List<ProductRequestDTO> inputProducts);
//    Object updateProduct(long id, ProductResponseDTO product );
//    Object getProduct(long id);
//    Product getProductBusiness(long id);
//    Product updateProduct(long id, Product product );
//    List<Object> getActiveProducts();
//    List<Object> getListProduct(TypeCategory category);
//    Object deleteProduct(long id);
//    //métodos del negocio
//    Object buyProduct(long id, double quantityPurchased);
//    Object sellProduct(long id, int quantitySold);
//    List<Product> getListProductForBuy();
//    Product getProductForBuyBusiness(long idProduct);


}
