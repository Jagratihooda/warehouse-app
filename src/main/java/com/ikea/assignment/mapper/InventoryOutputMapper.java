package com.ikea.assignment.mapper;
import com.ikea.assignment.domain.Article;
import com.ikea.assignment.model.Inventory;

public class InventoryOutputMapper {

    public static Inventory map(Article article) {

        return Inventory.builder().art_id(article.getId())
                .stock(article.getStock())
                .name(article.getName()).build();
    }
}
