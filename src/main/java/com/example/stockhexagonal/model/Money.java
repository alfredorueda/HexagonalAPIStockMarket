package com.example.stockhexagonal.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Value object representing a monetary amount with currency
 */
public class Money {
    private final BigDecimal amount;
    private final String currency;

    public Money(BigDecimal amount, String currency) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        
        // Round to 2 decimal places using proper BigDecimal methods
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency.toUpperCase();
    }

    public Money(double amount, String currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    /**
     * Creates a new Money object with USD currency
     * 
     * @param amount The monetary amount
     * @return A new Money object
     */
    public static Money dollars(double amount) {
        return new Money(amount, "USD");
    }

    /**
     * Creates a new Money object with EUR currency
     * 
     * @param amount The monetary amount
     * @return A new Money object
     */
    public static Money euros(double amount) {
        return new Money(amount, "EUR");
    }

    /**
     * Adds another Money object to this one
     * 
     * @param other The Money object to add
     * @return A new Money object with the summed amount
     * @throws IllegalArgumentException if currencies don't match
     */
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add amounts with different currencies");
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Subtracts another Money object from this one
     * 
     * @param other The Money object to subtract
     * @return A new Money object with the subtracted amount
     * @throws IllegalArgumentException if currencies don't match
     */
    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract amounts with different currencies");
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }

    /**
     * Multiplies this Money object by a factor
     * 
     * @param factor The multiplication factor
     * @return A new Money object with the multiplied amount
     */
    public Money multiply(double factor) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(factor)), this.currency);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && 
               Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString() {
        return amount.toString() + " " + currency;
    }
}