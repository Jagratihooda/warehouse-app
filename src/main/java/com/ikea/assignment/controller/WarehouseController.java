package com.ikea.assignment.controller;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.service.WarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jagrati
 * This rest controller is for Warehouse Software.
 */

@RestController
@RequestMapping("/warehouse/")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseController.class);


    @GetMapping("read-warehouse-files")
    public String readWarehouseInputFiles() {
        LOGGER.info("Reading warehouse file endpoint called");
        warehouseService.readInputFiles();
        return "Files are uploaded successfully";
    }

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

    @PostMapping("update-product")
    public void updateProduct(@RequestParam(name = "id") long id) {
         warehouseService.updateProduct(id);
    }

}
