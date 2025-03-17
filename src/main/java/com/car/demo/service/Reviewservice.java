package com.car.demo.service;

import com.car.demo.model.Product;
import com.car.demo.model.Review;
import com.car.demo.model.User;
import com.car.demo.Repo.ProductRepo;
import com.car.demo.Repo.ReviewRepo;
import com.car.demo.Repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Reviewservice {

   @Autowired 
   private ReviewRepo reviewRepo;
   @Autowired
   private UserRepository userRepo;
   @Autowired
   private ProductRepo productRepo;

    

    public Review createReview( String userId, String productId,  String comment, int rating) {
        // Check if the user and product exist
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create and save the review
        Review review = new Review();
        review.setUserId(user);
        review.setProductId(product);
        review.setComment(comment);
        review.setRating(rating);

        return reviewRepo.save(review);
    }

    // Optionally, you can add methods to retrieve reviews
    public List<Review> getReviewsByProduct(String productId) {
        return reviewRepo.findByProductId(productId);
    }

    public List<Review> getReviewsByUser(String userId) {
        return reviewRepo.findByUserId(userId);
    }
}


