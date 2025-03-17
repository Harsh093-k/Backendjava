package com.car.demo.controller;





import com.car.demo.model.Review;

import com.car.demo.service.Reviewservice;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin
public class ReviewController {

  

    private final Reviewservice reviewService;

    @Autowired
    public ReviewController(Reviewservice reviewService) {
        this.reviewService = reviewService;
    }

    

    @PostMapping("/create")
public ResponseEntity<?> createReview(@RequestBody @Valid Review review) {
    try {
        // Rename this to avoid variable conflict
        Review createdReview = reviewService.createReview(review.getUserId().getId(), review.getProductId().getId(), review.getComment(), review.getRating());
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }  catch (RuntimeException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
}


    // Optionally, you can create endpoints to retrieve reviews
    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable String productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable String userId) {
        return reviewService.getReviewsByUser(userId);
    }
}




