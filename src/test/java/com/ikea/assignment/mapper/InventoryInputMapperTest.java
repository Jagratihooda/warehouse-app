package com.ikea.assignment.mapper;

import static org.junit.Assert.assertEquals;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.model.Inventory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InventoryInputMapperTest {
    @InjectMocks
    private InventoryInputMapper inventoryInputMapper;

    @Test
    public void testMap() {
        Article article = inventoryInputMapper.map(Inventory.builder().art_id(1).name("article1").stock(100).build());

        assertEquals(1, article.getId());
        assertEquals("article1", article.getName());
        assertEquals(100, article.getStock());
    }
}

