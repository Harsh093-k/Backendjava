package com.car.demo.model;

import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users") 
public class User {
    
    @Id
    private String id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "password cannot be empty")
    private String password;

    @DBRef
    private List<Product> products; 
    
    private boolean verified;
    
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.verified = false;  
    }

    
    public User(String id) {
        this.id = id;
    }
}



