package com.car.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.car.demo.model.Product;
import com.car.demo.model.User;
import com.car.demo.service.UserService;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User user) {
        Map<String, Object> response = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }
    // Login a user
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody User user) {
        Map<String, Object> response = userService.loginUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestBody User user) {
        userService.updatePassword(user.getEmail(), user.getPassword());
        return ResponseEntity.ok("Password updated successfully!");
    }


}




