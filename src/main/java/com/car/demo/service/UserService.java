
package com.car.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.car.demo.model.Product;
import com.car.demo.model.User;
import com.car.demo.ProductNotFoundException;
import com.car.demo.Repo.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
 

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
       
    }

    public Map<String, Object> registerUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered!");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);

       
        User savedUser = userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", savedUser);

        return response;
    }

    
    public Map<String, Object> loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password!");
        }

        User user = userOptional.get();

        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password!");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
    
        return response;
    }

       public User getUserById(String id) {
        return userRepository.findById(id)
                   .orElseThrow(() -> new ProductNotFoundException("User with ID " + id + " not found"));
    }

    public User updatePassword(String email, String Password) {
  
        Optional<User> optionalUser = userRepository.findByEmail(email);
    
       

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found with email: " + email);
        }
    
       
        User user = optionalUser.get();
    
     
        String encodedPassword = passwordEncoder.encode(Password);
        user.setPassword(encodedPassword);
    
        
        return userRepository.save(user);
    }
    
}






