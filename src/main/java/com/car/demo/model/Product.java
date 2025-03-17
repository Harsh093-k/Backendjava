package com.car.demo.model;

import java.math.BigDecimal;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import jakarta.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products") 
public class Product {
  
    @Id
    private String id;
    @NotNull(message = "name cannot be empty")
    private String name;
    @NotNull(message = "Image cannot be empty")
    private String img;
    @NotNull(message = "Describsion cannot be empty")
    private String desc;
    @NotNull(message = "Brand cannot be empty")
    private String brand;
    @NotNull(message = "price cannot be empty")
    private BigDecimal price;
    @NotNull(message = "Category cannot be empty")
    private String category;
    @NotNull(message = "releaseDate  cannot be empty")
    private Date releaseDate;
    @NotNull(message = "Available cannot be empty")
    
    private boolean available;
    @NotNull(message = "Email cannot be empty")
    private int quantity;
  
    @DBRef
    private List<Review> reviews;
    @DBRef
    private User user; 

  
    public Product( String id) {
        this.id = id;
    }

}

