package com.ikea.assignment.controller;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.service.WarehouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseControllerTest {
    @InjectMocks
    private WarehouseController warehouseController;
    @Mock
    private WarehouseService warehouseService;

    @Test
    public void readWarehouseInputFilesTest() {
        warehouseController.readWarehouseInputFiles();
        Mockito.verify(warehouseService).readInputFiles();
    }

    @Test
    public void fetchInventoryDetailsTest() {
        List<Article> mockArticleList = new ArrayList<>();
        Article article1 = Article.builder().id(1).name("article1").stock(100).build();
        Article article2 = Article.builder().id(2).name("article2").stock(200).build();
        mockArticleList.add(article1);
        mockArticleList.add(article2);
        Mockito.when(warehouseService.fetchArticles()).thenReturn(mockArticleList);
        List<Article> fetchedArticleList = warehouseController.fetchInventoryDetails();
        Mockito.verify(warehouseService).fetchArticles();

        assertEquals(2, fetchedArticleList.size());

        assertEquals(1, fetchedArticleList.get(0).getId());
        assertEquals("article1", fetchedArticleList.get(0).getName());
        assertEquals(100, fetchedArticleList.get(0).getStock());

        assertEquals(2, fetchedArticleList.get(1).getId());
        assertEquals("article2", fetchedArticleList.get(1).getName());
        assertEquals(200, fetchedArticleList.get(1).getStock());
    }

    @Test
    public void fetchProductDetailsTest() {
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = Product.builder().id(1).name("product1").build();
        Product product2 = Product.builder().id(2).name("product2").build();
        mockProductList.add(product1);
        mockProductList.add(product2);
        Mockito.when(warehouseService.fetchProductDetails()).thenReturn(mockProductList);
        List<Product> fetchedProductList = warehouseController.fetchAllProducts();
        Mockito.verify(warehouseService).fetchProductDetails();

        assertEquals(2, fetchedProductList.size());

        assertEquals(1, fetchedProductList.get(0).getId());
        assertEquals("product1", fetchedProductList.get(0).getName());

        assertEquals(2, fetchedProductList.get(1).getId());
        assertEquals("product2", fetchedProductList.get(1).getName());
    }

    @Test
    public void updateProductTest() {
        warehouseController.sellProduct(Mockito.anyLong());
        Mockito.verify(warehouseService).updateProduct(Mockito.anyLong());

    }
}
