package com.cognizant.microservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.microservice.exception.ProductNotFoundException;
import com.cognizant.microservice.exception.RatingGreaterThan5Exception;
import com.cognizant.microservice.model.Product;
import com.cognizant.microservice.service.ProductServiceImpl;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Rishav,Amit,Siddhi,Swapnil
 *
 */
@RestController
@RequestMapping("/product")
//@ApiModel(value="Product Controller")
public class ProductController {
	@Autowired
	ProductServiceImpl productService;
	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	

	/**
	 * 
	 * @param id
	 * @return product
	 * @throws ProductNotFoundException
	 */
	@GetMapping("/productById/{id}")
//	@ApiOperation(notes = "Returns the product by Id", value = "Find product")
	public Product searchProductById(@PathVariable String id) throws ProductNotFoundException
	{
		log.info("Product is searching by id");
		return productService.searchProductById(Integer.parseInt(id));
		
	}

	
	
	/**
	 * 
	 * @param name
	 * @return product
	 * @throws ProductNotFoundException
	 */
	@GetMapping("/productByName/{name}")
//	@ApiOperation(notes = "Returns the product by Name", value = "Find product")
	public Product searchProductByName(@PathVariable String name) throws ProductNotFoundException 
	{
		log.info("Product is searching by name");
		return productService.searchProductByName(name);
	}

	
	
	/**
	 * 
	 * @param productId
	 * @param rating
	 * @return product
	 * @throws ProductNotFoundException
	 * @throws RatingGreaterThan5Exception
	 */
	@PostMapping("/addRating/{productId}/{rating}")
//	@ApiOperation(notes = "Add rating to the product", value = "add Rating")
	public Product addProductRating(@PathVariable int productId, @PathVariable int rating)
			throws ProductNotFoundException, RatingGreaterThan5Exception 
	{
		log.info("Rating is adding to the prouduct with id");
		productService.addProductRating(productId, rating);
		Product product = productService.searchProductById(productId);
		log.info("addProductRating inside ProductController ended");
		return product;
	}
	
	
	
	
	/**
	 * 
	 * @return list of products
	 */
	@GetMapping("/getAll")
//	@ApiOperation(notes = "Returns all products", value = "Find all products")
	public List<Product> getAll() 
	{
		log.info("fetching all the products");
		return productService.getAll();
	}
}
