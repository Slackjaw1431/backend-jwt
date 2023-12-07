package com.youtube.jwt.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.youtube.jwt.entity.ProductCategory;

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
//@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
