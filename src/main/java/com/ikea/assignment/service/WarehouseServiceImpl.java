package com.ikea.assignment.service;

import java.io.*;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.enums.ProductStatus;
import com.ikea.assignment.exception.ServiceException;
import com.ikea.assignment.mapper.InventoryInputMapper;
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

import javax.transaction.Transactional;


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
    @Transactional
    public void readInputFiles() {
        readInventoryFile();
        readProductsFile();
    }

    /**
     * This method reads inventory input file and save in database
     **/
    private void readInventoryFile() {
        LOGGER.info("Starting reading the inventory input file");
        JSONParser jsonParser = new JSONParser();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(inventoryFilePath)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray articleList = (JSONArray) obj.get("inventory");
            articleList.forEach(article -> saveArticle((JSONObject) article, gson));

        } catch (Exception e) {
            throw new ServiceException("Exception occurred while reading the inventory file", e);
        }
    }

    /**
     * This method converts inventory json in to an object and save in database
     *
     * @param inventoryJsonObj - json object from an inventory file
     * @param gson - gson object
     **/
    private void saveArticle(JSONObject inventoryJsonObj, Gson gson) {
        Inventory inputInventoryObj = gson.fromJson(inventoryJsonObj.toJSONString(), Inventory.class);
        articleRepository.save(InventoryInputMapper.map(inputInventoryObj));
    }


    /**
     * This method reads products input file and save in database
     */
    private void readProductsFile() {
        LOGGER.info("Starting reading the products input file");

        JSONParser jsonParser = new JSONParser();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(productsFilePath)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            JSONArray productList = (JSONArray) obj.get("products");

            productList.forEach(product ->
                    saveProduct((JSONObject) product, gson));

        } catch (Exception e) {
            throw new ServiceException("Exception occurred while reading the products file", e);
        }

    }

    /**
     * This method converts product json in to an object and save in database
     *
     * @param productJsonObj - json object from an inventory file
     * @param gson - gson object
     **/
    private void saveProduct(JSONObject productJsonObj, Gson gson) {
        WarehouseProducts inputProductObj = gson.fromJson(productJsonObj.toJSONString(), WarehouseProducts.class);

        Product productToBeSaved = ProductInputMapper.map(inputProductObj);
        productToBeSaved.setStatus(ProductStatus.IN_STOCK);

        Product savedProduct = productRepository.save(productToBeSaved);

        inputProductObj.getContain_articles().forEach(p -> setProductArticle(p, savedProduct.getId()));
    }

    /**
     * This method converts Contain_articles section of product json in to an object and save in database
     *
     * @param inputProductArticle - input ProductArticle
     * @param productId - id of a Product
     **/
    private void setProductArticle(WarehouseProductArticle inputProductArticle, long productId) {
        ProductArticle prodArticle = ProductArticle.builder()
                .article(Article.builder().id(inputProductArticle.getArt_id()).build())
                .articleAmount(inputProductArticle.getAmount_of()).product(Product.builder().id(productId).build())
                .build();
        productArticleRepository.save(prodArticle);
    }

    /**
     * This method fetches all the articles available in database
     *
     * @return articleList
     **/
    @Override
    public List<Article> fetchArticles() {
        return (List<Article>) articleRepository.findAll();
    }

    /**
     * This method fetches all the IN_STOCK products available in database
     *
     * @return articleList
     **/
    @Override
    public List<Product> fetchProductDetails() {
        return productRepository.findByStatus(ProductStatus.IN_STOCK);
    }

    /**
     * This method updates the current status of a product to sold and also,updates the inventory accordingly
     *
     * @param productId - id of a Product
     **/
    @Override
    public void updateProduct(long productId) {
        Optional<Product> savedProduct = productRepository.findById(productId);

        if (savedProduct.isPresent()) {

            //Iterate over productArticles of a product, fetch articles and update them accordingly
            savedProduct.get().getProductArticle().forEach(this::fetchAndUpdateAnArticle);

            //Set Status of a product to SOLD
            savedProduct.get().setStatus(ProductStatus.SOLD);
            productRepository.save(savedProduct.get());
        }
    }

    /**
     * This method fetches the given article and updates its amount after the product is sold
     *
     * @param productArticle object of a product
     **/
    private void fetchAndUpdateAnArticle(ProductArticle productArticle) {
        Optional<Article> existingArticle = articleRepository.findById(productArticle.getArticle().getId());

        if(existingArticle.isPresent()) {
            Article article = Article.builder().id(productArticle.getArticle().getId())
                    .stock(existingArticle.get().getStock() - productArticle.getArticleAmount()).name(productArticle.getArticle().getName()).build();
            articleRepository.save(article);
        }
    }

}
