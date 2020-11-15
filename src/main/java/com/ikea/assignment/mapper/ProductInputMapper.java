package com.ikea.assignment.mapper;

import com.ikea.assignment.domain.Product;
import com.ikea.assignment.domain.ProductArticle;
import com.ikea.assignment.model.WarehouseProductArticle;
import com.ikea.assignment.model.WarehouseProducts;

import java.util.stream.Collectors;

/**
 * @author Jagrati
 * This mapper is for mapping input Product data to Article domain object.
 */
public class ProductInputMapper {

    public static Product map(WarehouseProducts product) {

        return Product.builder().name(product.getName())
                .productArticle(product.getContain_articles().stream().map(ProductInputMapper::setProductArticle).collect(Collectors.toList()))
                .price(product.getPrice()).build();

    }

    private static ProductArticle setProductArticle(WarehouseProductArticle inputProductArticle) {
        return ProductArticle.builder().id(inputProductArticle.getArt_id())
                .articleAmount(inputProductArticle.getAmount_of()).build();
    }
}
