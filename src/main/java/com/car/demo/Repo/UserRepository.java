package com.car.demo.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.car.demo.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
}

