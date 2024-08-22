package com.brixton.sodimac.data.repository;

import com.brixton.sodimac.data.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Byte> {

}
