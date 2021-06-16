package com.cognizant.microservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cognizant.microservice.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
 class ProductControllerTest {

    @Autowired
    private MockMvc mock;

    @Test
     void testSearchProductById() throws Exception {
        int productId = 1;
        MvcResult mvcResult = mock.perform(get("/product/productById/" + productId)).andReturn();
        String productName = "Headphone";
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(productName));
    }

    @Test()
     void testSearchProductByIdProductNotFoundException() throws Exception {
        int productId = 101;
        MvcResult mvcResult = mock.perform(get("/product/productById/" + productId)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals(true,
                mvcResult.getResponse().getContentAsString().contains("Product with id [" + productId + "] not found"));
    }

    @Test
     void testSearchProductByName() throws Exception {
        String productName = "Mobile";
        MvcResult mvcResult = mock.perform(get("/product/productByName/" + productName)).andReturn();
        assertEquals(true, mvcResult.getResponse().getContentAsString().contains(productName));
    }

    @Test
     void testSearchProductByNameProductNotFoundException() throws Exception {
        String productName = "XYZ";
        MvcResult mvcResult = mock.perform(get("/product/productByName/" + productName)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals(true, mvcResult.getResponse().getContentAsString()
                .contains("Product with name [" + productName + "] not found"));
    }

    @Test
     void testAddProductRating() throws Exception {
        Product product = new Product(3, "Tablet", 22000, "imageName", "abc_image", 3, 0);
        String jsonProduct = this.mapToJson(product);
        MvcResult mvcResult = mock
                .perform(post("/product/addRating/1/3").contentType("application/json").content(jsonProduct)).andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
     void testAddProductRatingProductNotFoundException() throws Exception {
        Product product = new Product(110, "XYZ", 22000, "imageName", "abc_image", 4, 0);
        String jsonProduct = this.mapToJson(product);
        MvcResult mvcResult = mock
                .perform(post("/product/addRating/110/3").contentType("application/json").content(jsonProduct)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals(true, mvcResult.getResponse().getContentAsString()
                .contains("Product with id [" + product.getId() + "] not found"));
    }

    @Test
     void testAddProductRatingGreaterThan5Exception() throws Exception {
        Product product = new Product(3, "Tablet", 22000, "imageName", "abc_image", 10, 0);
        String jsonProduct = this.mapToJson(product);
        MvcResult mvcResult = mock
                .perform(post("/product/addRating/1/10").contentType("application/json").content(jsonProduct)).andReturn();
        assertEquals(409, mvcResult.getResponse().getStatus());
        //assertEquals(true, mvcResult.getResponse().getContentAsString().contains("Rating should be between 1 to 5"));
    }

    // JsonMapper
     String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}