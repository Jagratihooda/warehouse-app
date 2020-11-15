package com.ikea.assignment.mapper;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.model.Inventory;


/**
 * @author Jagrati
 * This mapper is for mapping input Inventory data to Article domain object.
 */
public class InventoryInputMapper {

    public static Article map(Inventory inventoryInput) {

        return Article.builder().id(inventoryInput.getArt_id())
                .stock(inventoryInput.getStock())
                .name(inventoryInput.getName()).build();
    }
}
