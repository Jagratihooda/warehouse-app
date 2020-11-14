package com.ikea.assignment.service;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.enums.ProductStatus;
import com.ikea.assignment.exception.ServiceException;
import com.ikea.assignment.mapper.InventoryInputMapper;
import com.ikea.assignment.mapper.InventoryOutputMapper;
import com.ikea.assignment.mapper.ProductOutputMapper;
import com.ikea.assignment.model.WarehouseProductArticle;
import com.ikea.assignment.model.WarehouseProducts;
import com.ikea.assignment.repository.ProductArticleRepository;
import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.ProductArticle;
import com.ikea.assignment.mapper.ProductInputMapper;
import com.ikea.assignment.model.Inventory;
import com.ikea.assignment.repository.ArticleRepository;
import com.ikea.assignment.repository.ProductRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * @author Jagrati
 * Warehouse Software Service Implementation
 */
@Component
public class WarehouseServiceImpl implements WarehouseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);


    @Value("${inventory.file.path}")
    private String inventoryFilePath;

    @Value("${products.file.path}")
    private String productsFilePath;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    @Override
    public void readInputFiles() {
        LOGGER.info("Started reading the input files");
        readInventoryFile();
        readProductsFile();
    }

    public List<Article> fetchArticles() {

        List<Article> articleList = (List<Article>) articleRepository.findAll();

       // return articleList.stream().map(InventoryOutputMapper::map).collect(Collectors.toList());
        return articleList;
    }

    public List<Product> fetchProductDetails() {
        List<Product> productDetails = (List<Product>) productRepository.findAll();

       /* return productDetails.stream().filter(p -> ProductStatus.IN_STOCK
                .equals(p.getStatus()))
        .map(ProductOutputMapper::map).collect(Collectors.toList());*/
        List<Product> products = productDetails.stream().filter(p -> ProductStatus.IN_STOCK
                .equals(p.getStatus())).collect(Collectors.toList());
        return products;
    }

    /**
     * This method downloads Incident Statistics report
     *
     * @return resource
     */
    private void readProductsFile() {
        JSONParser jsonParser = new JSONParser();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(productsFilePath)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            JSONArray productList = (JSONArray) obj.get("products");

            productList.forEach(product ->
                    saveProduct((JSONObject) product, gson));


        } catch (Exception e) {
            LOGGER.error("Exception occurred while reading the products file and the exception is " + e);
            throw new ServiceException("Exception occurred while reading the products file", e);
        }

    }

    private void saveProduct(JSONObject productJsonObj, Gson gson){
        WarehouseProducts product = gson.fromJson(productJsonObj.toJSONString(), WarehouseProducts.class);
        Product productToBeSaved = ProductInputMapper.map(product);
        productToBeSaved.setStatus(ProductStatus.IN_STOCK);
        Product savedProduct = productRepository.save(productToBeSaved);

        product.getContain_articles().stream().forEach(p -> setProductArticle(p, savedProduct.getId()));
    }

      private void setProductArticle(WarehouseProductArticle prdctArtcl, long productId){
       ProductArticle prodArticle = ProductArticle.builder()
               .article(Article.builder().id(prdctArtcl.getArt_id()).build())
               .articleAmount(prdctArtcl.getAmount_of()).product(Product.builder().id(productId).build())
               .build();
          productArticleRepository.save(prodArticle);
}
    /**
     * This method downloads Incident Statistics report
     *
     * @return resource
     */
    private void readInventoryFile() {
        JSONParser jsonParser = new JSONParser();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(inventoryFilePath)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray articleList = (JSONArray) obj.get("inventory");
            articleList.forEach(article -> saveArticle((JSONObject) article, gson));

        } catch (Exception e) {
            LOGGER.error("Exception occurred while reading the inventory file and the exception is " + e);
            throw new ServiceException("Exception occurred while reading the inventory file", e);
        }

    }

    private void saveArticle(JSONObject inventoryJsonObj, Gson gson) {
        Inventory inventory = gson.fromJson(inventoryJsonObj.toJSONString(), Inventory.class);
        articleRepository.save(InventoryInputMapper.map(inventory));
    }

    public WarehouseProducts updateProduct(long productId) {
        // product.getId(); //check whether this exists
        Optional<Product> savedProduct = productRepository.findById(productId);
        Product existingProduct = savedProduct.get();
        existingProduct.getProductArticle().forEach( p ->{
            Optional<Article> existingArticle = articleRepository.findById(p.getArticle().getId());
            Article article = Article.builder().id(p.getArticle().getId())
                    .stock(existingArticle.get().getStock() - p.getArticleAmount()).name(p.getArticle().getName()).build();
            articleRepository.save(article);
        });
        existingProduct.setStatus(ProductStatus.SOLD);
        productRepository.save(existingProduct);
        return null;
    }

}
