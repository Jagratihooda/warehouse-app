package com.ikea.assignment.repository;

import com.ikea.assignment.domain.Product;
import com.ikea.assignment.enums.ProductStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByProductStatus(ProductStatus productStatus);
}

