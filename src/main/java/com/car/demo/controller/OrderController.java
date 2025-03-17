package com.car.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.car.demo.service.EmailService;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendOrderEmail(@RequestBody Map<String, Object> request) {
        String email = (String) request.get("email");
        String productName = (String) request.get("productName");
        int quantity = Integer.parseInt(request.get("quantity").toString());

        emailService.sendOrderConfirmation(email, productName, quantity);

        return ResponseEntity.ok("Email sent successfully");
    }
    @PostMapping("/User-email")
    public ResponseEntity<String> sendSignupCongratulation(@RequestBody String email) {
        

        emailService.sendSignupCongratulation(email);

        return ResponseEntity.ok("Email sent successfully");
    }
}

