package com.cognizant.microservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.microservice.exception.ProductNotFoundException;
import com.cognizant.microservice.exception.RatingGreaterThan5Exception;
import com.cognizant.microservice.model.Product;
import com.cognizant.microservice.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;
	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Transactional
	public Product searchProductById(int productId) throws ProductNotFoundException {
		Product product = productRepo.findById(productId).orElse(null);
		if (product == null) 
		{
			log.info("prouduct id not found");
			throw new ProductNotFoundException("Product with id [" + productId + "] not found");
		}
		return product;
	}

	@Transactional
	public Product searchProductByName(String productName) throws ProductNotFoundException {
		Product product = productRepo.findByName(productName).orElse(null);
		if (product == null) 
		{
			log.info("prouduct name not found");
			throw new ProductNotFoundException("Product with name [" + productName + "] not found");
		}
		return product;
	}

	@Transactional
	public Product addProductRating(int productId,int rating) throws ProductNotFoundException, RatingGreaterThan5Exception {
		
		Product productExists = productRepo.findById(productId).orElse(null);

		if (rating <= 5 && rating >= 0) {

			if (productExists != null) {
				if (productExists.getRating() != 0) {
					productExists
							.setRating((productExists.getRating() * productExists.getUserRated() + rating)
									/ (productExists.getUserRated() + 1));
					productExists.setUserRated(productExists.getUserRated() + 1);
			
				} else {
					log.info("Rating is adding for the product");
					productExists.setRating(rating);
					productExists.setUserRated(productExists.getUserRated() + 1);
				}
				
				productRepo.save(productExists);
			} 
			else {
				log.info("prouduct id not found");
				throw new ProductNotFoundException("Product with id [" + productId + "] not found");
			}
		} 	
		else if(rating>5 || rating<0) 
		{
			log.info("Rating is not in between 1 to 5");
			throw new RatingGreaterThan5Exception("Rating should be between 1 to 5");
		}
		return productExists;
	}

	@Override
	public List<Product> getAll() {
		return productRepo.findAll();
	}
}
