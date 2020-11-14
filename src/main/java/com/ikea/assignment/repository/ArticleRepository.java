package com.ikea.assignment.repository;

import com.ikea.assignment.domain.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository <Article, Long>{

}

