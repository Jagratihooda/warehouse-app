package com.ikea.assignment.controller;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.model.Inventory;
import com.ikea.assignment.model.WarehouseProducts;
import com.ikea.assignment.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jagrati
 * This rest controller is for Warehouse Software.
 */

@RestController
@RequestMapping("/warehouse/")
public class WarehouseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);
    @Autowired
    private WarehouseService warehouseService;


    @GetMapping("articles")
    public List<Article> fetchInventoryDetails() {
        LOGGER.info("Fetching Inventory details");
        return warehouseService.fetchArticles();

    }

    @GetMapping("products")
    public List<Product> fetchAllProducts() {
        LOGGER.info("Fetching Product details");
        return warehouseService.fetchProductDetails();

    }

    @GetMapping("read-warehouse-files")
    public String readWarehouseInputFiles() {
        LOGGER.info("Reading warehouse file endpoint called");
        warehouseService.readInputFiles();
        return "Files are uploaded successfully";
    }


    //@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public ResponseEntity<WarehouseProducts> updateProduct(@PathVariable(value = "id") long id){

/*
                                                         //  //,@RequestBody WarehouseProducts product) {
*/

        return new ResponseEntity<>(warehouseService.updateProduct(id), HttpStatus.OK);
    }


}
