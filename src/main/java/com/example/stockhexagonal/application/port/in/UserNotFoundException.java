package com.example.stockhexagonal.application.port.in;

/**
 * Exception thrown when a requested user cannot be found
 */
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String userId) {
        super("User not found with ID: " + userId);
    }
}