package com.car.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.demo.model.Product;
import com.car.demo.model.Review;
import com.car.demo.service.Productservice;
@RestController
@CrossOrigin
@RequestMapping("/api")
public class product {
   
        @Autowired
    private Productservice service;

    // // Test route to check if the API is working
    @PostMapping("/{userId}/add")
    public Product save(@PathVariable String userId,@RequestBody Product product) {
        return service.createProductForUser(userId,product);
    }

  
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts(); 
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return service.getProductById(id);
    }

    
     @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
        service.deleteProductById(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
   

    // Add a review to a product
    @PostMapping("/{productId}/reviews")
    public Product addReviewToProduct(@PathVariable String productId, @RequestBody Review review) {
        return service.addReviewToProduct(productId, review);
    }
}

    


