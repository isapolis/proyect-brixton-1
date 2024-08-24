package com.brixton.sodimac.controller;

import com.brixton.sodimac.dto.request.management.CreateProductRequestDTO;
import com.brixton.sodimac.dto.response.management.ProductResponseDTO;
import com.brixton.sodimac.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/management/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody CreateProductRequestDTO inputProduct){
        return ResponseEntity.ok(productService.createProduct(inputProduct));
    }

    @PostMapping("/createwithlist")
    public ResponseEntity<List<ProductResponseDTO>> createWithList(@Valid @RequestBody List<CreateProductRequestDTO> inputProducts){
        return ResponseEntity.ok(productService.createWithList(inputProducts));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@Valid @PathVariable long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDTO>> getProducts(){
        return new ResponseEntity<>(productService.getListProducts(), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@Valid @PathVariable long id, @RequestBody CreateProductRequestDTO productToUpdate){
        return ResponseEntity.ok(productService.updateProduct(id,productToUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@Valid @PathVariable long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/category/{idCat}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable byte idCat){
        return new ResponseEntity<>(productService.getProductsForCategory(idCat), HttpStatus.OK);
    }

    /*
    @GetMapping("/productsForBuy")
    public ResponseEntity<List<Product>> getListProductForBuy(){
        List<Product> listProducts = productService.getListProductForBuy();
        if (listProducts != null) {
            return ResponseEntity.ok(listProducts);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
*/

}
