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
	 * This method reads inventory and product input json files
	 */
	void readInputFiles();

	/**
	 * This method fetches Articles from the dataBase
	 @return list of Articles
	 */
	List<Article> fetchArticles();

	/**
	 * This method fetches Products from the dataBase
	 @return list of Products
	 */
	List<Product> fetchProductDetails();

	/**
	 * This method updates a given Product
	 @param  productId
	 @return list of Products
	 */
	void updateProduct(long productId);

}
