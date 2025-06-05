package com.example.stockhexagonal.adapter.out.memory;

import com.example.stockhexagonal.application.port.out.LoadUserPort;
import com.example.stockhexagonal.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * In-memory implementation of the LoadUserPort interface
 */
@Repository
public class InMemoryUserRepository implements LoadUserPort {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    public InMemoryUserRepository() {
        // Initialize with some sample users using streams
        Stream.of(
            new User("1", "John Doe", Arrays.asList("AAPL", "MSFT", "GOOGL")),
            new User("2", "Jane Smith", Arrays.asList("AMZN", "TSLA", "NVDA", "META")),
            new User("3", "Robert Johnson", Arrays.asList("JPM", "BAC", "WFC", "GS"))
        ).forEach(user -> users.put(user.getId(), user));
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