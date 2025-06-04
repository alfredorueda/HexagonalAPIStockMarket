package com.example.stockhexagonal.adapter.out.memory;

import com.example.stockhexagonal.application.port.out.LoadUserPort;
import com.example.stockhexagonal.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of the LoadUserPort interface
 */
@Repository
public class InMemoryUserRepository implements LoadUserPort {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public InMemoryUserRepository() {
        // Initialize with some sample users
        List<String> appleInvestor = Arrays.asList("AAPL", "MSFT", "GOOGL");
        List<String> techInvestor = Arrays.asList("AMZN", "TSLA", "NVDA", "META");
        List<String> financeInvestor = Arrays.asList("JPM", "BAC", "WFC", "GS");
        
        users.put("1", new User("1", "John Doe", appleInvestor));
        users.put("2", new User("2", "Jane Smith", techInvestor));
        users.put("3", new User("3", "Robert Johnson", financeInvestor));
    }

    @Override
    public Optional<User> loadUserById(String id) {
        return Optional.ofNullable(users.get(id));
    }

    /**
     * Adds a new user to the repository
     * @param user The user to add
     * @return The added user
     */
    public User saveUser(User user) {
        users.put(user.getId(), user);
        return user;
    }

    /**
     * Removes a user from the repository
     * @param userId The ID of the user to remove
     * @return true if the user was removed, false if not found
     */
    public boolean deleteUser(String userId) {
        return users.remove(userId) != null;
    }
}