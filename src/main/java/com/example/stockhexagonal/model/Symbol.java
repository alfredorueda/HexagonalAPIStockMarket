package com.example.stockhexagonal.model;

import java.util.Objects;

/**
 * Value object representing a stock symbol
 */
public class Symbol {
    private final String value;

    public Symbol(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        this.value = value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symbol symbol = (Symbol) o;
        return Objects.equals(value, symbol.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}