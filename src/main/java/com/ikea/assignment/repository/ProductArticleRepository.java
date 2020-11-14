package com.ikea.assignment.repository;

import com.ikea.assignment.domain.ProductArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductArticleRepository extends CrudRepository<ProductArticle, Long> {
}

