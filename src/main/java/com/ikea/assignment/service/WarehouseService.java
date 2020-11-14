package com.ikea.assignment.service;

import com.ikea.assignment.domain.Article;
import com.ikea.assignment.domain.Product;
import com.ikea.assignment.model.Inventory;
import com.ikea.assignment.model.WarehouseProducts;

import java.util.List;

/**
 * @author Jagrati
 * Incident Statistics Generator Service
 */
public interface WarehouseService {
	/**
	 * This method generates Incident Statistics report
	 * @return fileName
	 */
	void readInputFiles();

	List<Article> fetchArticles();

	List<Product> fetchProductDetails();

	WarehouseProducts updateProduct(long productId);

}
