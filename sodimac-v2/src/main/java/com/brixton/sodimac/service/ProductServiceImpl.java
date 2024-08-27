package com.brixton.sodimac.service;

import com.brixton.sodimac.controller.manageexception.GenericNotFoundException;
import com.brixton.sodimac.data.entity.product.Category;
import com.brixton.sodimac.data.entity.product.Product;
import com.brixton.sodimac.data.enums.RegistryStateType;
import com.brixton.sodimac.data.repository.CategoryRepository;
import com.brixton.sodimac.data.repository.ProductRepository;
import com.brixton.sodimac.dto.request.management.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.management.ProductResponseDTO;
import com.brixton.sodimac.service.mapper.ProductMapper;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@ToString
public class ProductServiceImpl implements ProductService{
    private static final String USER_APP = "BRIXTON";
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO){
        Product newProduct = ProductMapper.INSTANCE.createProductRequestDTOToProduct(createProductRequestDTO);
        newProduct.setCreatedAt(LocalDateTime.now());
        newProduct.setCreatedBy(USER_APP);
        newProduct.setRegistryState(RegistryStateType.ACTIVE);
        Category category = categoryRepository.findById(newProduct.getCategory().getId()).orElseThrow(()-> new GenericNotFoundException("Id de category no existente"));
        newProduct.getCategory().setName(category.getName());
        productRepository.save(newProduct);
        ProductResponseDTO productResponseDTO = ProductMapper.INSTANCE.productToProductResponseDTO(newProduct);
        return productResponseDTO;

    }
    @Override
    public List<ProductResponseDTO> createWithList(List<CreateProductRequestDTO> inputProducts){
        List<ProductResponseDTO> products = new ArrayList<>();
        for (CreateProductRequestDTO createProductRequestDTO: inputProducts){
            try {
                products.add(createProduct(createProductRequestDTO));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return products;
    }
    @Override
    public ProductResponseDTO getProduct(long id){
        Product product= productRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Producto con Id no existente"));
        return ProductMapper.INSTANCE.productToProductResponseDTO(product);
    }
    @Override
    public List<ProductResponseDTO> getListProducts(){
        List<ProductResponseDTO> activeProducts = new ArrayList<>();
        List<Product> productsFound = productRepository.findByRegistryState(RegistryStateType.ACTIVE);
        for (Product productActive:productsFound){
            activeProducts.add(ProductMapper.INSTANCE.productToProductResponseDTO(productActive));
        }
        return activeProducts;
    }
    @Override
    public ProductResponseDTO updateProduct(long id, CreateProductRequestDTO productToUpdate){
        Product original = productRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Producto con ID no existente"));
        Product productTemp =ProductMapper.INSTANCE.createProductRequestDTOToProduct(productToUpdate);
        original.setUpdatedBy(USER_APP);
        original.setUpdatedAt(LocalDateTime.now());
        original.setName(productTemp.getName());
        Category category = categoryRepository.findById(productTemp.getCategory().getId()).orElseThrow(()-> new GenericNotFoundException("Id de category no existente"));
        productTemp.getCategory().setName(category.getName());
        original.setCategory(productTemp.getCategory());
        original.setQuantity(productTemp.getQuantity());
        original.setMinQuantity(productTemp.getMinQuantity());
        original.setPriceSale(productTemp.getPriceSale());
        original.setPriceSupplier(productTemp.getPriceSupplier());
        original.setCodeProduct(productTemp.getCodeProduct());
        if(original.getQuantity() <= original.getMinQuantity()){
            //productsForBuys.put(original.getId(), original);
        }
        productRepository.save(original);
        return ProductMapper.INSTANCE.productToProductResponseDTO(original);

    }
    @Override
    public void deleteProduct(long id){
        Product product = productRepository.findById(id).orElseThrow(()-> new GenericNotFoundException("Producto no registrado"));
        product.setRegistryState(RegistryStateType.INACTIVE);
        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy(USER_APP);
        productRepository.save(product);
    }
    @Override
    public List<ProductResponseDTO> getProductsForCategory(byte idCategory){
        List<ProductResponseDTO> products = new ArrayList<>();
        Category category= categoryRepository.findById(idCategory).orElseThrow(() -> new GenericNotFoundException("ID de category no encontrado"));
        List<Product> productsFound = productRepository.findByCategory(category);
        for (Product productInCategory:productsFound){
            products.add(ProductMapper.INSTANCE.productToProductResponseDTO(productInCategory));
        }
        return products;

    }


    /*
    @Override
    public List<Product> getListProductForBuy(){//List de productos por comprar
        List<Product> listProducts = new ArrayList<>();
        for(Product productTemporal: productsForBuys.values()){
            listProducts.add(productTemporal);
        }
        return listProducts;
    }

    @Override
    public Object buyProduct(long id, double quantityPurchased){
        try {
            Product original = products.get(id);
            if(original != null){
                original.setQuantity(original.getQuantity()+quantityPurchased);
                original.setUpdatedAt(LocalDateTime.now());
                products.put(original.getId(), original);
                productsForBuys.remove(original.getId());
                String jsonOutput = objectMapper.writeValueAsString(original);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Object sellProduct(long id, int quantitySold){
        try {
            Product original = products.get(id);
            if(original != null){
                original.setQuantity(original.getQuantity()-quantitySold);
                original.setUpdatedAt(LocalDateTime.now());
                products.put(original.getId(), original);
                String jsonOutput = objectMapper.writeValueAsString(original);
                ProductResponseDTO output = objectMapper.readValue(jsonOutput, ProductResponseDTO.class);
                return output;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Product getProductForBuyBusiness(long idProduct) {
        Product productTemp = productsForBuys.get(idProduct);
        if (productTemp!=null){
            return productTemp;
        }
        return null;
    }
*/

}
