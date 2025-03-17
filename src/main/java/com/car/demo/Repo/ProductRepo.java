package com.car.demo.Repo;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.car.demo.model.Product;


@Repository
public interface ProductRepo extends MongoRepository<Product, String> {

    Optional<Product> findById(String id);

  
}
