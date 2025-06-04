package com.example.stockhexagonal.application.port.out;

import com.example.stockhexagonal.model.User;
import java.util.Optional;

/**
 * Secondary port (output) for loading user data
 */
public interface LoadUserPort {
    
    /**
     * Loads a user by their unique identifier
     * 
     * @param id The unique identifier of the user
     * @return An Optional containing the user if found, or empty if not found
     */
    Optional<User> loadUserById(String id);
}