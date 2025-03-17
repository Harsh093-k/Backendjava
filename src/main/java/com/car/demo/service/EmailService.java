package com.car.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOrderConfirmation(String email, String productName, int quantity) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Order Confirmation");
        message.setText("Your order for " + quantity + " units of " + productName + " has been successfully booked.");

        mailSender.send(message);
    }
    
    public void sendSignupCongratulation(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome to Our Platform!");
        message.setText("Congratulations! You have successfully signed up. We are excited to have you on board.");

        mailSender.send(message);
    }
}

