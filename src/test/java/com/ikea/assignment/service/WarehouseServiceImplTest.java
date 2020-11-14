package com.ikea.assignment.service;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.domain.ProductArticle;
import com.ikea.assignment.enums.ProductStatus;
import com.ikea.assignment.repository.ArticleRepository;
import com.ikea.assignment.repository.ProductArticleRepository;
import com.ikea.assignment.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class WarehouseServiceImplTest {

    @InjectMocks
    private WarehouseServiceImpl service;
    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductArticleRepository productArticleRepository;

    @Captor
    private ArgumentCaptor<Article> articleCapture;

    @Captor
    private ArgumentCaptor<Product> productCapture;

    @Captor
    private ArgumentCaptor<ProductArticle> productArticleCapture;


    @Before
    public void initMocks() {
        ReflectionTestUtils.setField(service, "inventoryFilePath", "src/test/resources/inventory.json");
        ReflectionTestUtils.setField(service, "productsFilePath", "src/test/resources/products.json");
    }

    @Test
    public void readInputFilesTest() {
        Mockito.when(productRepository.save(Mockito.any())).thenReturn(Product.builder().id(1).build());
        Mockito.when(articleRepository.save(Mockito.any())).thenReturn(Article.builder().id(1).build());

        service.readInputFiles();

        Mockito.verify(articleRepository, times(4)).save(articleCapture.capture());

        assertEquals(1, articleCapture.getAllValues().get(0).getId());
        assertEquals("leg", articleCapture.getAllValues().get(0).getName());
        assertEquals(12, articleCapture.getAllValues().get(0).getStock());

        assertEquals(2, articleCapture.getAllValues().get(1).getId());
        assertEquals("screw", articleCapture.getAllValues().get(1).getName());
        assertEquals(17, articleCapture.getAllValues().get(1).getStock());


        Mockito.verify(productRepository, times(2)).save(productCapture.capture());

        assertEquals("Dining Chair", productCapture.getAllValues().get(0).getName());
        assertEquals("Dinning Table", productCapture.getAllValues().get(1).getName());


        Mockito.verify(productArticleRepository, times(6)).save(productArticleCapture.capture());

        assertEquals(1, productArticleCapture.getAllValues().get(0).getArticle().getId());
        assertEquals(1, productArticleCapture.getAllValues().get(0).getProduct().getId());
        assertEquals(4, productArticleCapture.getAllValues().get(0).getArticleAmount());

        assertEquals(2, productArticleCapture.getAllValues().get(1).getArticle().getId());
        assertEquals(1, productArticleCapture.getAllValues().get(1).getProduct().getId());
        assertEquals(8, productArticleCapture.getAllValues().get(1).getArticleAmount());

        assertEquals(3, productArticleCapture.getAllValues().get(2).getArticle().getId());
        assertEquals(1, productArticleCapture.getAllValues().get(2).getProduct().getId());
        assertEquals(1, productArticleCapture.getAllValues().get(2).getArticleAmount());
    }

    @Test
    public void fetchArticlesTest() {
        List<Article> mockArticleList = new ArrayList<>();
        Article article1 = Article.builder().id(1).name("article1").stock(100).build();
        Article article2 = Article.builder().id(2).name("article2").stock(200).build();
        mockArticleList.add(article1);
        mockArticleList.add(article2);
        Mockito.when(articleRepository.findAll()).thenReturn(mockArticleList);

        List<Article> articleList = service.fetchArticles();

        assertEquals(2, articleList.size());

        assertEquals(1, articleList.get(0).getId());
        assertEquals("article1", articleList.get(0).getName());
        assertEquals(100, articleList.get(0).getStock());

        assertEquals(2, articleList.get(1).getId());
        assertEquals("article2", articleList.get(1).getName());
        assertEquals(200, articleList.get(1).getStock());
    }

    @Test
    public void fetchProductDetailsTest() {
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = Product.builder().id(1).name("product1").build();
        Product product2 = Product.builder().id(2).name("product2").build();
        mockProductList.add(product1);
        mockProductList.add(product2);
        Mockito.when(productRepository.findByStatus(ProductStatus.IN_STOCK)).thenReturn(mockProductList);

        List<Product> productList = service.fetchProductDetails();

        assertEquals(2, productList.size());

        assertEquals(1, productList.get(0).getId());
        assertEquals("product1", productList.get(0).getName());

        assertEquals(2, productList.get(1).getId());
        assertEquals("product2", productList.get(1).getName());
    }

    @Test
    public void updateProductTest() {
        Article mockArticle = Article.builder().id(1).name("article1").stock(100).build();
        Product mockProduct = Product.builder().id(1).name("product1").productArticle(Arrays.asList(mockProductArticle())).build();

        Mockito.when(productRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct));
        Mockito.when(articleRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockArticle));

        service.updateProduct(Mockito.anyLong());

        Mockito.verify(articleRepository).save(articleCapture.capture());

        assertEquals(1, articleCapture.getValue().getId());

        // Total stock of mockArticle is 100 and mockProduct with amount 20 is sold
        // so the actual amount will be 80
        assertEquals(80, articleCapture.getValue().getStock());


        Mockito.verify(productRepository).save(productCapture.capture());

        assertEquals(1, productCapture.getValue().getId());
        assertEquals("product1", productCapture.getValue().getName());
        assertEquals(ProductStatus.SOLD, productCapture.getValue().getStatus());
    }

    private ProductArticle mockProductArticle() {
        ProductArticle mockProductArticle = ProductArticle.builder()
                .article(Article.builder().id(1).build()).product(Product.builder().id(1).build()).articleAmount(20).build();
        return mockProductArticle;
    }
}

