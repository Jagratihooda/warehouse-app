package com.ikea.assignment.mapper;

import com.ikea.assignment.domain.Product;
import com.ikea.assignment.model.WarehouseProductArticle;
import com.ikea.assignment.model.WarehouseProducts;
import com.ikea.assignment.domain.ProductArticle;

import java.util.stream.Collectors;

public class ProductOutputMapper {

    public static WarehouseProducts map(Product product) {

        return WarehouseProducts.builder().name(product.getName())
                .contain_articles(product.getProductArticle().stream().map(
                        ProductOutputMapper::setProductArticle).collect(Collectors.toList())).price(product.getPrice()).build();

    }

    private static WarehouseProductArticle setProductArticle(ProductArticle prodArticle) {
        return WarehouseProductArticle.builder().art_id(prodArticle.getArticle().getId())
                .amount_of(prodArticle.getArticleAmount()).build();
    }
}
