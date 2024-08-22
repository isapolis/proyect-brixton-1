package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
