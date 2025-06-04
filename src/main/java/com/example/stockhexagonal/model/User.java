package com.example.stockhexagonal.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Domain model class representing a user with investments
 */
public class User {
    private final String id;
    private final String name;
    private final List<String> companySymbols;

    /**
     * Creates a new User with the given ID, name, and company symbols
     *
     * @param id User's unique identifier
     * @param name User's name
     * @param companySymbols List of stock symbols the user has invested in
     */
    public User(String id, String name, List<String> companySymbols) {
        this.id = Objects.requireNonNull(id, "User ID cannot be null");
        this.name = Objects.requireNonNull(name, "User name cannot be null");
        
        if (companySymbols == null) {
            this.companySymbols = new ArrayList<>();
        } else {
            this.companySymbols = new ArrayList<>(companySymbols);
        }
    }

    /**
     * @return User's unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * @return User's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Unmodifiable list of company symbols the user has invested in
     */
    public List<String> getCompanySymbols() {
        return Collections.unmodifiableList(companySymbols);
    }

    /**
     * Adds a new company symbol to the user's investments
     *
     * @param symbol The stock symbol to add
     * @return true if the symbol was added, false if it was already present
     * @throws IllegalArgumentException if the symbol is null or empty
     */
    public boolean addCompanySymbol(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        
        String normalizedSymbol = symbol.toUpperCase();
        if (!companySymbols.contains(normalizedSymbol)) {
            return companySymbols.add(normalizedSymbol);
        }
        return false;
    }

    /**
     * Removes a company symbol from the user's investments
     *
     * @param symbol The stock symbol to remove
     * @return true if the symbol was removed, false if it wasn't present
     */
    public boolean removeCompanySymbol(String symbol) {
        if (symbol == null || symbol.trim().isEmpty()) {
            return false;
        }
        
        return companySymbols.remove(symbol.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}