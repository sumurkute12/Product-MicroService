package com.cognizant.microservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.microservice.exception.ProductNotFoundException;
import com.cognizant.microservice.exception.RatingGreaterThan5Exception;
import com.cognizant.microservice.model.Product;
import com.cognizant.microservice.repo.ProductRepo;

@SpringBootTest
class ProductServiceImplTest {

	@Autowired
	ProductServiceImpl productService;

	@MockBean
	ProductRepo productRepo;

	@Test
	void testSearchProductById() throws Exception {
		Product product = new Product(1, "Headphone", 2000, "imageName",
				"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
				0, 0);
		when(productRepo.findById(1)).thenReturn(Optional.of(product));
		assertEquals(productService.searchProductById(1).getName(), product.getName());
	}

	@Test
	void testSearchProductByIdProductNotFoundException() throws Exception {
		when(productRepo.findById(101)).thenReturn(Optional.empty());
		Exception exception = assertThrows(ProductNotFoundException.class, () -> productService.searchProductById(101));
		assertEquals("Product with id [101] not found", exception.getMessage());
	}

	@Test
	void testSearchProductByName() throws Exception {
		Product product = new Product(1, "Headphone", 2000, "imageName",
				"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
				0, 0);
		when(productRepo.findByName("Headphone")).thenReturn(Optional.of(product));
		assertEquals(productService.searchProductByName("Headphone").getId(), product.getId());
	}

	@Test
	void testSearchProductByNameProductNotFoundException() throws Exception {
		when(productRepo.findByName("XYZ")).thenReturn(Optional.empty());
		Exception exception = assertThrows(ProductNotFoundException.class,
				() -> productService.searchProductByName("XYZ"));
		assertEquals("Product with name [XYZ] not found", exception.getMessage());
	}

	@Test
	void testAddProductRating() throws Exception {
		Product product = new Product(1, "Headphone", 2000, "imageName",
				"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
				0, 0);
		when(productRepo.findById(1)).thenReturn(Optional.of(product));
		productService.addProductRating(1, 3);
		assertEquals(3, productService.searchProductById(1).getRating());
	}

	@Test
	void testAddProductRatingProductNotFoundException() throws Exception {
		when(productRepo.findById(101)).thenReturn(Optional.empty());
		Exception exception = assertThrows(ProductNotFoundException.class,
				() -> productService.addProductRating(101, 5));
		assertEquals("Product with id [101] not found", exception.getMessage());
	}

	@Test
	void testAddProductRatingGreaterThan5Exception() throws Exception {
		when(productRepo.findById(101)).thenReturn(Optional.empty());
		Exception exception = assertThrows(RatingGreaterThan5Exception.class,
				() -> productService.addProductRating(101, 7));
		assertEquals("Rating should be between 1 to 5", exception.getMessage());
	}

	@Test
	void testGetAll() {
		List<Product> products = Stream.of(new Product(1, "Headphone", 2000, "imageName",
				"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
				0, 0),
				new Product(2, "Mobile", 2000, "imageName",
						"https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80",
						0, 0))
				.collect(Collectors.toList());
		when(productRepo.findAll()).thenReturn((List<Product>) products);
		assertEquals(productService.getAll().get(0).getName(), ((List<Product>) products).get(0).getName());
		// 111

	}

}
