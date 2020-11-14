package com.ikea.assignment.mapper;

import com.ikea.assignment.domain.Product;
import com.ikea.assignment.model.WarehouseProductArticle;
import com.ikea.assignment.model.WarehouseProducts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProductInputMapperTest {
    @InjectMocks
    private ProductInputMapper productInputMapper;

    @Test
    public void testMap() {
        Product product = productInputMapper.map(WarehouseProducts.builder().name("product1")
                .contain_articles(Arrays.asList(mockProductArticle())).build());

        assertEquals("product1", product.getName());
        assertEquals(1, product.getProductArticle().get(0).getId());
        assertEquals(100, product.getProductArticle().get(0).getArticleAmount());

    }

    private WarehouseProductArticle mockProductArticle() {
        WarehouseProductArticle mockProductArticle = WarehouseProductArticle.builder()
                .art_id(1).amount_of(100).build();
        return mockProductArticle;
    }
}

