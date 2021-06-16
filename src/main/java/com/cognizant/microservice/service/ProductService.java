package com.cognizant.microservice.service;

import java.util.List;

import com.cognizant.microservice.exception.ProductNotFoundException;
import com.cognizant.microservice.exception.RatingGreaterThan5Exception;
import com.cognizant.microservice.model.Product;
/**
 * 
 * @author Swapnil,Rishav,Amit,Siddhi
 *
 */
public interface ProductService {
	/**
	 * 
	 * @param productId
	 * @return Product
	 * @throws ProductNotFoundException
	 */
	public Product searchProductById(int productId) throws ProductNotFoundException;

	/**
	 * 
	 * @param productName
	 * @return Product
	 * @throws ProductNotFoundException
	 */
	public Product searchProductByName(String productName) throws ProductNotFoundException;
	/**
	 * 
	 * @param productId
	 * @param rating
	 * @return ResponseEntity<String>
	 * @throws ProductNotFoundException
	 * @throws RatingGreaterThan5Exception
	 */
	public Product addProductRating(int productId, int rating)
			throws ProductNotFoundException, RatingGreaterThan5Exception;
	/**
	 * 
	 * @return List<Product>
	 */
	public List<Product> getAll();
}
