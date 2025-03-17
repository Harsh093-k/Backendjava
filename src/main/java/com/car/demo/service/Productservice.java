package com.car.demo.service;

import com.car.demo.model.Product;
import com.car.demo.model.Review;
import com.car.demo.model.User;
import com.car.demo.Repo.ProductRepo;
import com.car.demo.Repo.ReviewRepo;
import com.car.demo.Repo.UserRepository;
import com.car.demo.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Productservice {

    @Autowired
    private ProductRepo repo;
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserRepository userRepo;

    public Product getProductById(String id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

   

    public Product createProductForUser(String userId, Product product) {
        
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    
      
        product.setUser(user);
    
        
        if (product.getReleaseDate() == null) {
            product.setReleaseDate(new Date()); 
        }
    
       
        Product savedProduct = repo.save(product);
    
        
        userRepo.save(user);
    
        return savedProduct;
    }
    


    public List<Product> getAllProducts() {
        return repo.findAll();
    }

     public Product addReviewToProduct(String productId, Review review) {
        Optional<Product> productOptional = repo.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            review.setProductId(product);
            reviewRepo.save(review);  
            product.getReviews().add(review);  
            return repo.save(product);  
        }

        throw new RuntimeException("Product not found");
    }

    public void deleteProductById(String productId) {
        // Check if the product exists
        Product product = repo.findById(productId)
                              .orElseThrow(() -> new RuntimeException("Product not found"));
    
        // Remove the product from the associated user’s product list
        User user = product.getUser();
        if (user != null && user.getProducts() != null) {
            user.getProducts().removeIf(p -> p.getId().equals(productId));
            userRepo.save(user); // Update the user in the database
        }
    
        // Delete the product from the database
        repo.deleteById(productId);
    }
    
  
}


