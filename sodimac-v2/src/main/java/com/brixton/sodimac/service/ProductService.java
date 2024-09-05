package com.brixton.sodimac.service;

import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.dto.request.management.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.management.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO);
    List<ProductResponseDTO> createWithList(List<CreateProductRequestDTO> inputProducts);
    ProductResponseDTO getProduct(long id);
    List<ProductResponseDTO> getListProducts();
    ProductResponseDTO updateProduct(long id, CreateProductRequestDTO productToUpdate);
    void deleteProduct(long id);
    List<ProductResponseDTO> getProductsForCategory(byte idCategory);

    //Métodos del negocio
    Product getProductFromData(long id);


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
