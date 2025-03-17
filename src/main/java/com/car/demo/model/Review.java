package com.car.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")  
public class Review {

    @Id
    private String id;

    @DBRef
    private User userId;

  @DBRef
    private Product productId;

    private int rating; 
    private String comment;
    private Date createdAt = new Date(); 
}


